# pip install PyPDF2
import PyPDF2
def read_pdf(file_path):
    with open(file_path,'rb') as file:
        pdf_reader = PyPDF2.PdfReader(file)
        num_pages = len(pdf_reader.pages)
        print(f'Total Pages: {num_pages}')
        for page_num in range(num_pages):
            page = pdf_reader.pages[page_num]
            text = page.extract_text()
            print(f'Page {page_num + 1}:\n{text}\n')
            
file_path = './pdf/example.pdf'
read_pdf(file_path)