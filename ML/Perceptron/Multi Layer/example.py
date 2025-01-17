import numpy as np
import matplotlib.pyplot as plt
X = np.array([[1,2,3,4]])
y = np.array([[1]])  

Wxh = np.array([[0.1, 0.5],[0.2, 0.6],[0.3, 0.7],[0.4, 0.8]])
Why = np.array([[0.9],[1.0]])

bh = np.array([[0.1, 0.2]])
by = np.array([[0.3]])

alpha = 0.2           
epochs = 500   

def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def sigmoid_derivative(x):
    return sigmoid(x) * (1 - sigmoid(x))

def forward_prop(X, Wxh, bh, Why, by):
    z1 = np.dot(X, Wxh) + bh
    a1 = sigmoid(z1)
    z2 = np.dot(a1, Why) + by
    yo = sigmoid(z2)
    return yo, z1, a1, z2

def backward_prop(X, y, yo, z1, a1, z2):
    delta2 = np.multiply(-(y - yo), sigmoid_derivative(z2))
    dJ_dWhy = np.dot(a1.T, delta2)
    dJ_dby = np.sum(delta2, axis=0, keepdims=True)
    
    delta1 = np.dot(delta2, Why.T) * sigmoid_derivative(z1)
    dJ_dWxh = np.dot(X.T, delta1)
    dJ_dbh = np.sum(delta1, axis=0, keepdims=True)
    
    return dJ_dWxh, dJ_dWhy, dJ_dbh, dJ_dby

cost = []
for epoch in range(epochs):
    yo, z1, a1, z2 = forward_prop(X, Wxh, bh, Why, by)
    dJ_dWxh, dJ_dWhy, dJ_dbh, dJ_dby = backward_prop(X, y, yo, z1, a1, z2)

    Wxh -= alpha * dJ_dWxh
    Why -= alpha * dJ_dWhy
    bh -= alpha * dJ_dbh
    by -= alpha * dJ_dby

    loss = 0.5 * np.sum((y - yo) ** 2)
    cost.append(loss)
    print(f"Epoch {epoch + 1}, Loss: {loss:.6f}")

print("\nFinal Results:")
print("Output (yo):", yo[0][0])

plt.plot(range(epochs),cost)
plt.title('Cost Function')
plt.xlabel('Epochs')
plt.ylabel('Cost')
plt.grid()
plt.show()