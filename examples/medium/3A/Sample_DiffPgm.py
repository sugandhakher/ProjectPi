# a web crawler program which crawls 1000 unique URLs to depth 5

# 1. one second between requests
# 2. Implement DFS
# 3. Links leading only to links with prefix: "https://en.wikipedia.org/wiki"
# 4. Filter out: Links contains ":", "#", non-english artciles, external links,
#    main wiki page, nav & marginal links
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

# urlFile = open("URL.txt", "w")
# urlCount = 0
# depth = 1


stack = []
# currentUrl = []
listObj = []
urlCount = 0


def webcrawler_dfs(url, depth):
    print ("entered function")
    lists = []
    request = urllib.request
    ssl_protocol = ssl.PROTOCOL_SSLv23 # HTTPS request

    metadata = request.urlopen(seed, context=ssl.SSLContext(ssl_protocol)).info()
    value = re.search("Content-language:\s(.*)", str(metadata))
    
    if value.group(1) == "en":
        
        # time.sleep(1)
        html_response = request.urlopen(
            url,
            context=ssl.SSLContext(ssl_protocol)).read()
        print ("got response")
        array = url.split("/")
        file_name = array[len(array)-1] + ".txt"
        # print ("fileName: "+ file_name)
        file_object = open(file_name, "w")
        
        soup = BeautifulSoup(html_response, "html.parser")
        html_body = soup.body

        for head in htmlBody.find_all('h1', id='firstHeading'):
            file_object.write(head.get_text())
            file_object.write("\n")  # add to txt file
        
        body_content = htmlBody.find('div', id='body_content')
        file_object.write(body_content.get_text()) # add to txt file

        # definitions in a different page
        for aTag in body_content.find_all('a'):
            href = aTag['href']
            content = aTag.contents
            if ("solar".casefold() in str(href).casefold()) and ("solar".casefold() in str(content).casefold()):
                if (href.startswith('#')
                    or (href == "/wiki/Main_Page")
                    or (":" in href)
                    or ("//" in href)
                    or ("&action=edit" in href)
                        or (href.startswith("//"))):
                    pass

                else:
                    url = "https://en.wikipedia.org"+href
                    if url not in lists:
                        lists.append(url)
            else:
                pass
                    
    if depth < 5:
        # print ("depth inside if: " + str(depth))
        for index in lists:
            print ("index :" + str(index))
            global urlCount
            if urlCount == 1000:
                exit()
            webcrawler_dfs (index, depth+1)
            print ("index :" + str(index))
            urlFile.write(index + "\n")
            urlCount += 1
            print ("urlCount :"+ str(urlCount))
                

# main
urlFile = open("URL_DFS.txt", "w")            
seed = "https://en.wikipedia.org/wiki/Sustainable_energy"
depth = 2
webcrawler_dfs(seed, depth)
