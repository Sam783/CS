import matplotlib.pyplot as plt
import numpy as np
from scipy import stats

x = [5, 7, 8, 7, 2, 17, 2, 9, 4, 11, 12, 9, 6]
y = [99, 86, 87, 88, 111, 86, 103, 87, 94, 78, 77, 85, 86]

# res =  stats.linregress(x, y)
# print("Slope:", res.slope)
# print("Intercept:", res.intercept)
# print("R-value:", res.rvalue)
# print("P-value:", res.pvalue)
# print("Standard Error:", res.stderr)


slope, intercept, r, p, std_err = stats.linregress(x, y)

def func(x):
    return slope * x + intercept

print("Slope: ", slope)
print("Intercept: ", intercept)

line = list(map(func, x))

plt.scatter(x, y)
plt.plot(x, line, color='blue')
plt.title("Scatter Plot of Data Points")
plt.xlabel("X-axis")
plt.ylabel("Y-axis")
plt.show()
