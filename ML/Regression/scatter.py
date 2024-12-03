import matplotlib.pyplot as plt
import numpy as np

# x = [5,7,8,7,2,17,2,9,4,11,12,9,6]
# y = [99,86,87,88,111,86,103,87,94,78,77,85,86]

x = np.random.normal(5,1,100)
y = np.random.normal(10,2,100)
plt.scatter(x,y)
plt.show()