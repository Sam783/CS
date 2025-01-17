from keras.models import Sequential
from keras.layers import Dense

# Define the model
model = Sequential()

# Add hidden layer with 2 neurons, sigmoid activation, and input shape of 4
model.add(Dense(units=2, activation='sigmoid', input_shape=(4,), name='hidden_layer'))

# Add output layer with 1 neuron and sigmoid activation
model.add(Dense(1, activation='sigmoid', name='output_layer'))

# Compile the model
model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

# Display the model summary
model.summary()
