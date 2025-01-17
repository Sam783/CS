from keras.models import Sequential
from keras.layers import Dense
import numpy as np

x = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])
y = np.array([[0], [1], [1], [0]])

model = Sequential()
model.add(Dense(2, activation='sigmoid', input_shape=(2,), name='hidden_layer', use_bias=True))
model.add(Dense(1, activation='sigmoid', name='output_layer', use_bias=True))

model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])
model.summary()

model.fit(x, y, epochs=10000, verbose=0)

loss, accuracy = model.evaluate(x, y)
print(f"Accuracy: {accuracy * 100}%")
print(f"Loss: {loss}")

predictions = model.predict(x)
print("Predictions:")
print(np.round(predictions))
