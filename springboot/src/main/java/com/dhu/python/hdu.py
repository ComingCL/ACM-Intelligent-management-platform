import re
import pandas as pd
import requests
import csv
from bs4 import BeautifulSoup

url = 'https://acm.hdu.edu.cn/listproblem.php'

hdu_special = 'vol'

header = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) "
                  "Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.47 "
}
response = requests.get(url, headers=header)

soup = BeautifulSoup(response.text, features="lxml")

# txt = soup.prettify()
# with open('hdu.txt', "w", encoding='utf-8') as f:
#     for i in txt:
#         f.write(i)
table = soup.find('table', {'class': {'table_text'}})

FootList = soup.find('p', {'class': {'footer_link'}})
FootList = str(FootList)

pattern = re.compile(r'<a href=.*?>.*?</a>')


result = re.findall(pattern=pattern, string=FootList)

total = len(result)

file = open('excel/hdu.csv', 'a')
# 文件定位到开头
file.seek(0)
file.truncate()
file.write('None1,Number,None2,Name,Accepted,Submission\n')
for i in range(1, total + 1):
    new_url = url + '?' + hdu_special + '=' + str(i)
    new_response = requests.get(new_url, headers=header)
    # 在网页控制台通过document.charset获得
    new_response.encoding = 'utf-8'
    new_soup = BeautifulSoup(new_response.text, features='lxml')
    ProblemList = new_soup.find('table', {'class': {'table_text'}})
    ProblemList = str(ProblemList)
    new_pattern = re.compile(r'\(.*?;')
    new_result = re.findall(pattern=new_pattern, string=ProblemList)
    for item in new_result:
        if item[1] != 'A':
            new_List = item[1:len(item) - 2]
            file.write(new_List)
            file.write('\n')
            # writer.writerow((new_List[0], new_List[1], new_List[2], new_List[3], new_List[4]))


file.close()

