_author_ = "Kavya Vasudevaraju"

#imports
import math
import operator
import os

PRdict = dict() #stores (doc,pagerank) as a (key-val) pair
PRdict_new = dict() #store (doc,NEW pagerank) as a (key-val) pair
M = dict() #M is the set of pages where value of each page is a set of pages that link to it (without duplicates)
L = dict() #L is the set of pages with their corresponding outlink count (without duplicates)
d = 0.85 #PageRank damping/teleportation factor
P = [] #P is the set of all pages; |P| = N
perpLst = [] #keeps track of consecutive perplexity values
S = []
sumPR = 0 #entropy
itrCount = 0 #count of number of iterations
outlinkCount = 0 #default outlink count for all pages
N = 0

urlFile = open(os.getcwd() + "/Task2_G1_Perplexity.txt", "w")
urlFilePR = open(os.getcwd() + "/Task2_G1_Top50Pages.txt", "w")
urlStats = open(os.getcwd() + "/Stats_G1.txt", "w")

# read graph from wt2g_inlinks
#with open ("/Users/kavyaraju/Dropbox/NEU/FirstSem/Courses/IR/HW2/Final/wt2g_inlinks.txt") as f:
with open (os.getcwd() + "/G1.txt") as f:
    graph = f.readlines()

def initialize():
    # extract pages from graph to P
    for i in graph:
        i = i.strip()
        allElements = i.split(" ")
        # get page and add to P
        page = allElements[0]
        P.append(page)

        # add page and its corresponding linking pages to M
        if (len(allElements) > 1):
            M[page] = [allElements[1]] #first element
            lst = list(set(allElements[2:]))
            for e in lst:
                M[page].append(e)

def initializePR():
    #initialize PageRank of each page to => 1/N
    global N
    N = len(P)
    initialPR = (1 / N)
    for pg in P:
        PRdict[pg] = initialPR
        L[pg] = outlinkCount

def addOutlinkCount():
    for page in P:
        # add page and its corresponding outlink count
        if M.get(page):
            for outlink in M.get(page):
                outlinkCount = L.get(outlink)
                outlinkCount = outlinkCount + 1
                L[outlink] = outlinkCount

# load S with sink nodes
def getSinkNodes():
    # add set of sink nodes to S
    for page, outlinkCnt in L.items(): #CHANGEDTHIS
        if (outlinkCnt == 0):
            S.append(page)

def calcLog2PR(pageRankOfPage, page):
    return (math.log(pageRankOfPage,2))

# calculate Entropy
def calcEntropy():
    sumPR = 0
    for pg in P:
        #global sumPR
        sumPR -= (PRdict[pg] * calcLog2PR(PRdict[pg] , pg))
    return sumPR

# calculate perplexity
def calcPerplexity():
    perplexity = math.pow(2, calcEntropy())
    perpLst.append(perplexity)
    return perplexity

# Check for convergence
# returns a Bool: false if converged
def isConverged():
    prplex = calcPerplexity()
    global itrCount
    urlFile.write(str(itrCount) + "\t" + str(prplex) + "\n")
    if (itrCount >= 4):
        last4PerpLst = perpLst[-4:]

        if(((last4PerpLst[3]-last4PerpLst[2]) < 1) and
        ((last4PerpLst[2]-last4PerpLst[1]) < 1) and
        ((last4PerpLst[1]-last4PerpLst[0]) < 1)):
            return True
        else:
            return False
    else:
        return False

def normalize(inputDict):
    sumOfPR = sum(list(inputDict.values()))
    for pr in inputDict:
        inputDict[pr] /= sumOfPR

def calcPageRank():

    while (not(isConverged())):
        global itrCount
        itrCount +=1
        print ("iteration count: " + str(itrCount))
        sinkPR = 0

        #calculate total sink Page Rank
        for p in S:
            sinkPR += PRdict.get(p)

        #print ("value of N: " +  str(N))
        PRdict_new = dict()
        for pindex in P:
            PRdict_new[pindex] = (1 - d) / N #teleportation
            PRdict_new[pindex] += d * sinkPR / N #spread remaining sink PR evenly
            if (M.get(pindex) != None):
                for qindex in M[pindex]:
                    PRdict_new[pindex] += d * PRdict[qindex] / L[qindex] #add share of page ranks from in-links
        normalize(PRdict_new)
        for pge in P:
            PRdict[pge] = PRdict_new[pge]
        PRdict_new = dict()

def buildStatistics(gname):
        urlStats.write(gname + ":\n")
        noInLinks = len(L)/len(PRdict)
        noOutLinks = len(S)/len(PRdict)
        urlStats.write("Proportion of pages with no out-links: " + str(noOutLinks) + "\n")
        urlStats.write("Proportion of pages with no in-links: " + str(noInLinks))

#caller
initialize()
initializePR() #initializePR for each page
addOutlinkCount() #add outlinks to L
getSinkNodes() #get sink nodes
calcPageRank() #calculate new page rank
#print ("final page rank: ")
sorted_PR = sorted(PRdict.items(), key=operator.itemgetter(1), reverse = True)
for i in sorted_PR:
    urlFilePR.write(str(i) + "\n")
buildStatistics("G2")
urlFile.close()
urlFilePR.close()
