import numpy as np
import matplotlib.pyplot as plt

def step(x):
    return 1 if x >=0 else 0

x = np.linspace(-4, 4, 100)
y = list(map(step,x))

plt.plot(x,y)
plt.title('Step Activation Function')
plt.xlabel('input')
plt.ylabel('output')
plt.show()
