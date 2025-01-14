import numpy as np

X = np.array([[0,0],[0,1],[1,0],[1,1]])
y = np.array([[0],[0],[0],[1]])

Wxh = np.random.rand(2,2)
bh = np.zeros((1,2))
Why = np.random.rand(2,1)
by = np.zeros((1, 1))

alpha = 0.2
epochs = 2000

def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def sigmoid_derivative(x):
    return sigmoid(x) * (1 - sigmoid(x))

def forward_prop(X, Wxh, Why, bh, by):
    zi = np.dot(X,Wxh) + bh
    ai = sigmoid(zi)
    zo = np.dot(ai,Why) + by
    yo = sigmoid(zo)
    return zi,ai,zo,yo

def backward_prop(X, y, zi, ai, zo, yo):
    delta2 = (yo - y) * sigmoid_derivative(zo)
    dJ_dWhy = np.dot(ai.T,delta2)
    dJ_dby = np.sum(delta2,axis=0,keepdims=True)

    delta1 = np.dot(delta2,Why.T) * sigmoid_derivative(zi)
    dJ_dWxh = np.dot(X.T, delta1)
    dJ_dbh = np.sum(delta1,axis=0,keepdims=True)
    return dJ_dWxh,dJ_dWhy,dJ_dbh,dJ_dby

for epoch in range(epochs):
    zi,ai,zo,yo = forward_prop(X, Wxh, Why, bh, by)
    dJ_dWxh,dJ_dWhy,dJ_dbh,dJ_dby = backward_prop(X, y, zi, ai, zo, yo)

    Wxh -= alpha * dJ_dWxh
    Why -= alpha * dJ_dWhy
    bh -= alpha * dJ_dbh
    by -= alpha * dJ_dby

    loss = 0.5 * np.sum((y - yo) ** 2)

    if (epoch+1)%100 == 0:
        print(f"Epoch {epoch + 1}, Loss: {loss:.3f}")

print(np.round(yo))