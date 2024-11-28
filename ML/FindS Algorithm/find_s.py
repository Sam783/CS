def find_s(training_data):
    hypothesis = ['0'] * len(attributes)
    # print(hypothesis)
    for example in training_data:
        label = example[-1]
        if(label == 'Positive'):
            for i in range(len(attributes)):
                if(hypothesis[i] == '0'):
                    hypothesis[i] = example[i]
                elif(hypothesis[i] != example[i]):
                    hypothesis[i] = '?'

    return hypothesis

attributes = ['color','shape','size']
training_data = [
    ['Red','Large','Round','Positive'],
    ['Red','Small','Round','Negative'],
    ['Red','Large','Square','Positive'],
    ['Blue','Large','Square','Positive'],
]
final_hypothesis = find_s(training_data)
print("Final_hypothesis",final_hypothesis)