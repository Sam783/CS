import xml.etree.ElementTree as ET
def read_xml(file_path):
    tree = ET.parse('./xml/example.xml')
    root = tree.getroot()

    name = root.find('name').text
    age = root.find('age').text
    city = root.find('city').text

    print(f'Name: {name}')
    print(f'Age: {age}')
    print(f'City: {city}')

file_path = './xml/example.xml'
read_xml(file_path)