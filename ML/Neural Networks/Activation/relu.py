import numpy as np
import matplotlib.pyplot as plt

def relu(x):
    return np.maximum(0,x)

x = np.linspace(-4, 4, 100)
y = list(map(relu,x))

plt.plot(x,y)
plt.title('Rectified Linear Unit (ReLU) Activation Function')
plt.xlabel('input')
plt.ylabel('output')
plt.show()
