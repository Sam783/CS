import numpy as np
import matplotlib.pyplot as plt

def soft_max(x):
    return np.exp(x)/np.sum(np.exp(x))
    
# z = [2.0, 1.0, 0.1]
# print(soft_max(z))

x = np.linspace(-4, 4, 100)
y = soft_max(x)

plt.plot(x,y)
plt.title('SoftMax Activation Function')
plt.xlabel('input')
plt.ylabel('output')
plt.show()
