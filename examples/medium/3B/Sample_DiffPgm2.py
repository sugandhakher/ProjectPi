_author_ = "Kavya Vasudevaraju"

# Program to find BM25
# Source: https://en.wikipedia.org/wiki/Okapi_BM25
# Relevance information is not considered in the below program

import os
import re
from collections import Counter
import math
import operator

# CONSTANTS
ri = 0
R = 0
k1 = 1.2
k2 = 100
b = 0.75
N = 1000
counter = 0
qCount = 0

# PATH OF FILES TO READ FROM
corpusPath = os.getcwd() + '/Corpus'
noOfDocsContainingTermPath = os.getcwd() + '/docfreq.txt'
noOfDocsInCollection = os.getcwd() + '/termCount.txt'
queryfilepath = os.getcwd() + '/query.txt'
uniqueLst = []

# PATH OF FILE TO WRITE TO
bmscoresFile = os.getcwd() + '/BMScoring.txt'
fileObjectBM25 = open(bmscoresFile, "w")


# THIS METHOD READS THE QUERIES FROM THE TEXT FILE
def get_queries(queryfilepath):
	with open (queryfilepath, 'r') as f1:
		for line in f1.readlines():
			query = line.split("=")[1]
			global qCount
			qCount = qCount + 1
			get_docs_for_queryterm(query, qCount)


# THIS METHOD GETS ALL DOCS FOR EACH TERM IN QUERY
def get_docs_for_queryterm(query, qCount):
	qList = query.split()
	for q in qList:
		#regex to fetch the query term documents from docfreq file
		patternAfterTermFound = "(?<=\('" + q + r"', {')(.*?)':"
		strFile = open(noOfDocsContainingTermPath).read()
		allDocsForTerm = re.findall(patternAfterTermFound, strFile)
		docs = allDocsForTerm[0].split()
		for doc in docs:
			uniqueLst.append(doc)
		list(set(uniqueLst))
		print ("length of list: ")
		print (len(uniqueLst))
		counter = 0
		for doc in uniqueLst:
			counter = counter + 1
			print (counter)
			calc_bm_doc(doc, query)
		sortedDict = sorted(dict3.items(), key=operator.itemgetter(1), reverse = True)
		#print ("sorted dictionary...")
		#print (sortedDict)
		rank = 0
		for key, val in sortedDict:
			rank = rank + 1
			fileObjectBM25.write(str(qCount) + " Q0 " + str(key) +  " " + str(rank) + " " + str(val) + " BM25\n")
		#input()

dict1 = dict()
dict2 = dict()
dict3 = dict()


def bm25_scoring(k1, b, dl, avdl, ri, R, ni, N, fi, K, k2, qfi):
	val1 = ((ri + 0.5) / (R - ri + 0.5)) / ((ni - ri + 0.5) / (N - ni - R + ri + 0.5))
	val2 = ((k1 + 1) * fi) / (K + fi)
	val3 = ((k2 + 1) * qfi) / (k2 + qfi)
	rank_docforquery = (math.log (val1,2)) * val2 * val3
	return rank_docforquery


def calc_bm_doc(doc, query):
	# file1 = open(corpusPath + '/' + doc +'.txt').read()
    dl = len(file1.split())
	avdl =  dl / N
	qList = query.split()
	dict1 = Counter(file1.split())
	dict2 = Counter(query.split())
	K = calcK(k1, b, dl, avdl)
	bm_val = 0
	for q in qList:
		patternAfterTermFound1 = "(?<=" + q + r"', {)(.*?)}\)"
		strFile= open(noOfDocsContainingTermPath).read()
		allDocsForTerm1 = re.findall(patternAfterTermFound1, strFile)
		ni = int(allDocsForTerm1[0].split(':')[1].strip())
		qfi = dict2[q]
		bm_val += bm25_scoring(k1, b, dl, avdl, ri, R, ni, N, dict1[q], K, k2, qfi)
	#print (bm_val)
	dict3[doc] = bm_val


def calcK(k1, b, dl, avdl):
	res = k1 * ((1 - b) + (b * (dl / avdl)))
	return res

get_queries(queryfilepath)
fileObjectBM25.close()
#calc_bm_doc('Globalwarming', 'global warming potential')
