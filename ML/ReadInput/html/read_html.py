# pip install beautifulsoup4
from bs4 import BeautifulSoup
def read_html(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        html_content = file.read()
    soup = BeautifulSoup(html_content,'lxml')
    
    title = soup.title.string
    print(f'Title: {title}')
    
    header = soup.h1.string
    print(f'Header: {header}')
    
    p1 = soup.find('p', class_='intro').string
    p2 = soup.find('p', id='demo').string
    # if there exists nested tags like <b> or <strong> inside <p>
    p3 = soup.find('p', id='get').get_text()
    print(f'Paragraph 1: {p1}')
    print(f'Paragraph 2: {p2}')
    print(f'Paragraph 3: {p3}')
    
    list_items = soup.find_all('li')
    
    for item in list_items:
        print(f'List Item: {item.string}')
        
file_path = './html/example.html'

read_html(file_path)