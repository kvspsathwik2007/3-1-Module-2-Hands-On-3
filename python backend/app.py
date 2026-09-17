import os

from dotenv import load_dotenv

# Load .env BEFORE importing routes/services
load_dotenv()

from flask import Flask
from flask_cors import CORS

from routes.prompt_routes import prompt_routes


app = Flask(__name__)

CORS(app)

app.register_blueprint(prompt_routes)


if __name__ == "__main__":

    if not os.getenv("GROQ_API_KEY"):
        print("WARNING: GROQ_API_KEY is not configured.")
    else:
        print("GROQ API key loaded successfully.")

    app.run(
        host="0.0.0.0",
        port=5000,
        debug=True
    )