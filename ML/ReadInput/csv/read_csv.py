import csv
def read_csv(file_path):
    with open(file_path,'r') as file:
        csv_reader = csv.reader(file)
        header = next(csv_reader)
        print(f'Header: {header}')
        for row in csv_reader:
            if row: 
                print(f'row: {row}')
                
file_path = './csv/example.csv'
read_csv(file_path)

# import pandas as pd
# pd.read_csv('./example.csv')