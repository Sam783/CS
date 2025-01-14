import numpy as np
import matplotlib.pyplot as plt

def func(x):
    return x**2

def grad_func(x):
    return 2*x

def cost_function(alpha,epochs,x):
    cost = []
    for i in range(epochs):
        x = x - alpha * grad_func(x)
        cost.append(x)
    
    return x,cost

x,cost = cost_function(0.1,100,10)

x_val = np.linspace(-10,10,100)
y_val = func(x_val)

print(cost)
plt.plot(x_val,y_val)
plt.scatter(cost,list(map(func, cost)),color='red')
plt.xlabel('x')
plt.ylabel('y')
plt.title('Gradient Descent on y = x^2')
plt.show()
