x = [5, 7, 8, 7, 2, 17, 2, 9, 4, 11, 12, 9, 6]
y = [99, 86, 87, 88, 111, 86, 103, 87, 94, 78, 77, 85, 86]

x_mean = sum(x)/len(x)
y_mean = sum(y)/len(x)

num = 0
den = 0
for i in range(len(x)):
    num += (x[i] - x_mean) * (y[i] - y_mean)
    den += (x[i] - x_mean)**2

m = num/den
c = y_mean - m * x_mean

print("Slope: ", m)
print("Intercept: ", c)


# Using Numpy to calculate slope and intercept

# import numpy as np
# x_mean = np.mean(x)
# y_mean = np.mean(y)

# num = np.sum((x - x_mean) * (y - y_mean))
# den = np.sum((x - x_mean) ** 2)

# m = num/den
# c = y_mean - m * x_mean

# print("Slope: ", m)
# print("Intercept: ", c)