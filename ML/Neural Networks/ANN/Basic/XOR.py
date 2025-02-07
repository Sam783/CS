import numpy as np
import matplotlib.pyplot as plt

X = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])
y = np.array([[0], [1], [1], [0]])

Wxh = np.random.rand(2, 2)
bh = np.zeros((1, 2))
Why = np.random.rand(2, 1)
by = np.zeros((1, 1))

alpha = 1
epochs = 1000

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
    delta2 = (yo - y) * sigmoid_derivative(z2)
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
    bh -= alpha * dJ_dbh
    Why -= alpha * dJ_dWhy
    by -= alpha * dJ_dby

    loss = 0.5 * np.sum((y - yo) ** 2)
    cost.append(loss)

    if (epoch+1) % 100 == 0:
        print(f"Epoch {epoch + 1}, Loss: {loss:.6f}")
        # print(f"Predictions: {(yo.T[0])}")

print("\nFinal Predictions after Training:")
print(yo)

plt.plot(range(epochs),cost)
plt.title('Cost Function')
plt.xlabel('Epochs')
plt.ylabel('Cost')
plt.grid()
plt.show()