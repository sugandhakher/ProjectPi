_author_ = "Kavya Vasudevaraju"

# 1. one second between requests
# 2. Implement BFS
# 3. Links leading only to links with prefix: "https://en.wikipedia.org/wiki"
# 4. Filter out: Links contains ":", "#", non-english artciles, external links, main wiki page, nav & marginal links
# 5. Depth = 5
# 6. 1000 UNIQUE urls
# 7. URLs in txt file
# 8. Raw html files with their URL for further processing


import urllib.request
import ssl
from bs4 import BeautifulSoup
from multiprocessing import Queue
import time
import copy
import re

#urlQueue = Queue()
seed = "https://en.wikipedia.org/wiki/Team"
#queue = [Queue()] * 5
queue = []
queue_copy = []
#listObj = list()
listObj = []
uCount = 0

def webCrawler (queue, url, urlFile):
        
    print ("webCrawl function called")
    request = urllib.request
    sslProtocol = ssl.PROTOCOL_SSLv23 #HTTPS request

    metadata = request.urlopen(seed, context=ssl.SSLContext(sslProtocol)).info()
    value = re.search("Content-language:\s(.*)", str(metadata)) #checks for language as English
    
    if value.group(1) == "en":
        time.sleep(5) #politeness
        htmlDocResponse = request.urlopen(url, context=ssl.SSLContext(sslProtocol)).read()

        array = url.split("/")
        fileName = array[len(array)+1] + ".txt"
        print ("fileName: "+ fileName)
        fileObject = open (fileName, "w")

        soup = BeautifulSoup(htmlDocResponse, "html.parser")

        htmlBody = soup.body

        for head in htmlBody.find_all('h1', id='firstHeading'):
            fileObject.write(head.get_text())
            fileObject.write("\n") #add to txt file

        bodyContent = htmlBody.find('div', id='bodyContent') #extract only useful content
        fileObject.write(bodyContent.get_text()) #add to txt file
        
        for aTag in bodyContent.find_all('a'): #get all a tags from page
            href = aTag['href']
            if (href.startswith('#') 
                or (href == "/wiki/Main_Page")
                    or (";" in href)
                        or ("//" in href)
                                or ("&action=edit" in href)
                                or (href.startswith("//"))):
                pass
            else:
                url = "https://en.wikipedia.org"+href
                queue.append(url)
                    
        print ("pass complete")
        fileObject.close()


# main
urlFile = open("URL_task1.txt", "w")

for depth in range(1, 4):
    queue_copy = queue
    queue = []
    if depth == 1:
        webCrawler(queue, seed, urlFile)
        print ("seed crawl complete")
    else:
        queue_copy.reverse()
        while queue_copy:        
            urlToCrawl = queue_copy.pop()
            uCount += 1
            if uCount != 1000:
                exit()
            print ("entered while loop:: At depth =" + str(depth))
         
            webCrawler(queue, urlToCrawl, urlFile)
            urlFile.write(urlToCrawl + "\n")
        depth += 1
        print ("level complete")

urlFile.close()
