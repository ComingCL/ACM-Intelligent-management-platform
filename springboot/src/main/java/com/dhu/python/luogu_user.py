import io
import sys

import requests
import re

from fake_useragent import FakeUserAgent
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
from sys import argv

MAX_NUM = 10000
# 设置编码, 注意
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
url = 'https://www.luogu.com.cn/user/'
practice = "#practice"
header = {
    'User-Agent': FakeUserAgent().random
}

userID = argv[1]
url = url + userID + practice
chrome_options = webdriver.ChromeOptions()
# linux下需要加这句话
# chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--headless')
chrome_options.add_argument('--disable-gpu')

driver = webdriver.Chrome(options=chrome_options)
driver.get(url)
name = None
try:
    name = driver.find_element(By.XPATH, '/html/body/div/div[2]/main/div/div[1]/div[1]/div[1]/div[1]/span').text
except NoSuchElementException:
    print('无此用户')
print(name)

problem = []
if name is not None:
    for i in range(1, MAX_NUM + 1):
        try:
            one = driver.find_element(By.XPATH, '/html/body/div/div[2]/main/div/div[2]/section[2]/div[2]/div[1]/span['
                                      + str(i) + ']/a').text
            problem.append(one)
        except NoSuchElementException:
            print('未发现下一条数据')
            break
problem.sort()
for i in problem:
    print(i)

