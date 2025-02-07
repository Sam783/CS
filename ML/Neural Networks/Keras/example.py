import numpy as np
from keras.models import Sequential
from keras.layers import Dense

x = np.array([[1, 2, 3, 4]])
y = np.array([[0.5]])

model = Sequential()
model.add(Dense(2, activation='sigmoid', input_shape=(4,), name='hidden_layer',use_bias=True))
model.add(Dense(1, name='output_layer',use_bias=True)) # No activation for output layer for regression

Wxh = np.array([[0.1, 0.5],[0.2, 0.6],[0.3, 0.7],[0.4, 0.8]])
Why = np.array([[0.9],[1.0]])

bh = np.array([0.1, 0.2])
by = np.array([0.3])

model.layers[0].set_weights([Wxh,bh])
model.layers[1].set_weights([Why,by])


model.compile(optimizer='adam', loss='mean_squared_error')
model.fit(x, y, epochs=1000, verbose=1)

output = model.predict(x)
print("yo:", output)
