import numpy as np

x = np.array([[0.1],[0.2],[0.3],[0.4]])

Wxh = np.random.rand(1,2)
Why = np.random.rand(2,2)
bh = np.zeros((1,2))
by = np.zeros((1,2))

def soft_max(x):
    return np.exp(x) / np.sum(np.exp(x), axis=1, keepdims=True)

def ReLU(x):
    return np.maximum(0,x)

def forward_prop(x,Wxh,Why,bh,by):
    zi = np.dot(x,Wxh) + bh
    ai = ReLU(zi)
    zo = np.dot(ai,Why) + by
    yo = soft_max(zo)
    return zi,ai,zo,yo

zi,ai,zo,yo = forward_prop(x,Wxh,Why,bh,by)

print(np.max(yo, axis= 1))

