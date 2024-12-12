from flask import Flask, request, jsonify
import numpy as np
import tensorflow as tf

app = Flask(__name__)

# Load Model
try:
    s_quality = tf.keras.models.load_model('sleepquality.keras')
    s_duration = tf.keras.models.load_model('sleepduration.keras')
except FileNotFoundError:
    s_quality = None
    s_duration = None
    print("Model not found")

# Endpoint Default
@app.route('/')
def home():
    return jsonify({"message": "Welcome"})

# Endpoint Sleep Quality
@app.route('/quality', methods=['POST'])
def predict_quality():
    # Get data from request
    data = request.json
    gender = data.get('gender')
    age = data.get('age')
    physical_activity = data.get('physical_activity')
    stress_level = 10 - float(data.get('stress_level'))
    sleep_duration = float(data.get('sleep_duration')) * 30
    
    # Check if all fields are filled
    if gender is None or age is None or physical_activity is None or stress_level is None or sleep_duration is None:
        return jsonify({"error": "All input fields are required"}), 400

    input_features = [[gender, age, physical_activity, stress_level, sleep_duration]]
    input_features = np.array(input_features, dtype='float32')
    prediction = s_quality.predict(input_features)
    prediction = float(prediction)
    prediction = prediction / 100
    return jsonify({"prediction": prediction}), 200

# Endpoint Sleep Duration
@app.route('/duration', methods=['POST'])
def predict_duration():
    # Get data from request
    data = request.json
    gender = data.get('gender')
    age = data.get('age')
    physical_activity = data.get('physical_activity')
    stress_level = 10 - float(data.get('stress_level'))

    # Check if all fields are filled
    if gender is None or age is None or physical_activity is None or stress_level is None:
        return jsonify({"error": "All input fields are required"}), 400
    
    input_features = [[gender, age, physical_activity, stress_level]]
    input_features = np.array(input_features, dtype='float32')
    prediction = s_duration.predict(input_features)
    prediction = float(prediction)
    prediction = prediction / 10
    return jsonify({"prediction": prediction}), 200

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080, debug=True)