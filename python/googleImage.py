import requests
import simplejson as json
from bs4 import BeautifulSoup

Images = []

headers_Get = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0',
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
        'Accept-Language': 'en-US,en;q=0.5',
        'Accept-Encoding': 'gzip, deflate',
        'DNT': '1',
        'Connection': 'keep-alive',
        'Upgrade-Insecure-Requests': '1'
    }

def appendImg(link, type):
    global images
    Images.append((link,type))


def google(q):
    s = requests.Session()
    q = '+'.join(q.split())
    url = 'https://www.google.com/search?q=' + q + '&source=lnms&tbm=isch'
    r = s.get(url, headers=headers_Get)

    soup = BeautifulSoup(r.text, "html.parser")

    for a in soup.find_all("div","rg_meta"):
        link , Type =json.loads(a.get_text())["ou"]  ,json.loads(a.get_text())["ity"]
        appendImg(link, Type)

    # for searchWrapper in soup.find_all('h3', {'class':'r'}): #this line may change in future based on google's web page structure
    #     url = searchWrapper.find('a')["href"]
    #     text = searchWrapper.find('a').text.strip()
    #     result = {'text': text, 'url': url}
    #     output.append(result)

    return Images

print(google("370z"))
