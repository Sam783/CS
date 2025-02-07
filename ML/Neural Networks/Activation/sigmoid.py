import numpy as np
import matplotlib.pyplot as plt

def sigmoid(x):
    return 1/(1+np.exp(-x))

x = np.linspace(-4, 4, 100)
y = list(map(sigmoid,x))

plt.plot(x,y)
plt.title('Sigmoid Activation Function')
plt.xlabel('input')
plt.ylabel('output')
plt.show()
