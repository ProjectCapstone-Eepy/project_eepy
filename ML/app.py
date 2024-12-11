from flask import Flask, request, jsonify
from flask_cors import CORS
import joblib
from sklearn.preprocessing import MinMaxScaler

app = Flask(__name__)
CORS(app) # Enable CORS

scaler = MinMaxScaler() # Normalisation scaler

# Load Model
try:
    s_quality = joblib.load('sleep_quality.pkl')
    s_duration = joblib.load('sleep_duration.pkl')
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
    stress_level = data.get('stress_level')
    avg_sleep_duration = data.get('sleep_duration')
    
    # Ensure all data is integer
    try:
        gender = int
        age = int
        physical_activity = int
        stress_level = int
        avg_sleep_duration = int
    except ValueError:
        return jsonify({"error": "All input fields must be integers"}), 400
    
    # Check if all fields are filled
    if gender is None or age is None or physical_activity is None or stress_level is None or avg_sleep_duration is None:
        return jsonify({"error": "All input fields are required"}), 400

    input_features = [[gender, age, physical_activity, stress_level, avg_sleep_duration]]
    input_features = scaler.transform(input_features) # Normalise input features
    prediction = s_quality.predict(input_features)
    return jsonify({"prediction": prediction[0]}), 200

# Endpoint Sleep Duration
@app.route('/duration', methods=['POST'])
def predict_duration():
    # Get data from request
    data = request.json
    gender = data.get('gender')
    age = data.get('age')
    physical_activity = data.get('physical_activity')
    stress_level = data.get('stress_level')

    # Ensure all data is integer
    try:
        gender = int
        age = int
        physical_activity = int
        stress_level = int
        avg_sleep_duration = int
    except ValueError:
        return jsonify({"error": "All input fields must be integers"}), 400
    
    # Check if all fields are filled
    if gender is None or age is None or physical_activity is None or stress_level is None:
        return jsonify({"error": "All input fields are required"}), 400
    
    input_features = [[gender, age, physical_activity, stress_level]]
    input_features = scaler.transform(input_features) # Normalise input features
    prediction = s_duration.predict(input_features)
    return jsonify({"prediction": prediction[0]}), 200

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080, debug=True)