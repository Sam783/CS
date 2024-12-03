import numpy as np

# Generates 2 random numbers between 0 to 1
print(np.random.rand(2))

# Generates 2x3 matrix of random numbers between 0 to 1
print(np.random.rand(2,3)) 

# Generate a random integer between 100 to 999
print(np.random.randint(100, 1000))

# Generate a 30 random integers between 100 to 999
random_numbers = np.random.randint(100,1000,size=30)
print(random_numbers)
