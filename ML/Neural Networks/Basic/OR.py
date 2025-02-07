import numpy as np
import matplotlib.pyplot as plt

def OR_perceptron(inputs, weights, bias):
    outputs = np.zeros(len(inputs))
    for i, x in enumerate(inputs):
        af = np.dot(weights, x) + bias
        if af >= 0:
            outputs[i] = 1
        else:
            outputs[i] = 0
    return outputs

inputs = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])
weights = np.array([1, 1])
bias = -0.5

outputs = OR_perceptron(inputs, weights, bias)

for i, x in enumerate(inputs):
    print(f"Input: {x}, Output: {outputs[i]}")

x = np.linspace(-2, 2, 100)
y = -(weights[0] / weights[1]) * x - bias / weights[1]

plt.plot(x, y, color='black', label='Boundary Line')
plt.scatter(inputs[:, 0], inputs[:, 1],color = 'blue')
plt.xlabel('x1')
plt.ylabel('x2')
plt.title('OR Perceptron With Bias')
plt.grid()
plt.show()
