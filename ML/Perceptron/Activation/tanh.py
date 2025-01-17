import numpy as np
import matplotlib.pyplot as plt

def tan_h(x):
    return np.tanh(x)

x = np.linspace(-4, 4, 100)
y = list(map(tan_h,x))

plt.plot(x,y)
plt.title('Hyperbolic tangent Activation Function')
plt.xlabel('input')
plt.ylabel('output')
plt.show()
