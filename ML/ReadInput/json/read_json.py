import json
def read_json(file_path):
    with open(file_path,'r') as file:
        data = json.load(file)
    print(data)
file_path = './json/example.json'
read_json(file_path)

# person = '{"name": "John", "age": 30, "city": "New York"}'
# print(json.loads(person))