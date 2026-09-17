3-1 Module-2 Hands-On-3

A practical Prompt Engineering Lab that demonstrates and compares three prompting strategies — Zero-Shot, Few-Shot, and Chain-of-Thought — using the same user task.

📌 Project Overview

The purpose of this project is to understand how different prompting techniques can influence the structure, clarity, and quality of Large Language Model (LLM) responses.

The application allows a user to enter a task and generates responses using:

Zero-Shot Prompting – The task is given directly without examples.
Few-Shot Prompting – The task is provided along with examples to guide the expected response style.
Chain-of-Thought Prompting – The model is instructed to carefully reason about the task internally and provide a clear final answer without exposing private reasoning.

All three strategies are evaluated using the same task, making the comparison meaningful.

🏗️ System Architecture
┌──────────────────────────┐
│      Android App         │
│      Kotlin + XML        │
│                          │
│  Task Input              │
│  Compare Prompts Button  │
│  Results Cards            │
└────────────┬─────────────┘
             │
             │ Retrofit REST API
             ▼
┌──────────────────────────┐
│      Flask Backend       │
│                          │
│  Prompt Routes           │
│  Prompt Engineering      │
│  Response Generation     │
└────────────┬─────────────┘
             │
             │ Groq API
             ▼
┌──────────────────────────┐
│        Groq LLM          │
│                          │
│  Zero-Shot               │
│  Few-Shot                │
│  Chain-of-Thought        │
└──────────────────────────┘
🛠️ Technologies Used
Frontend
Kotlin
XML
Android Studio
Material 3
RecyclerView
Retrofit
Kotlin Coroutines
ViewModel
StateFlow
Backend
Python
Flask
Flask-CORS
python-dotenv
Groq API
AI / LLM
Groq API
openai/gpt-oss-120b
📂 Project Structure
3-1-module-2-hands-on-3/
│
├── android/
│   └── 3_1_Module2_HandsOn3/
│       └── app/
│
├── backend/
│   ├── routes/
│   │   ├── __init__.py
│   │   └── prompt_routes.py
│   │
│   ├── services/
│   │   ├── __init__.py
│   │   └── prompt_engine.py
│   │
│   ├── app.py
│   ├── config.py
│   ├── prompts.py
│   ├── requirements.txt
│   └── .env.example
│
├── README.md
└── .gitignore
🔄 How It Works
The user enters a task in the Android application.
The Android app sends the task to the Flask backend using Retrofit.
Flask receives the task through the /prompt/compare endpoint.
The Prompt Engineering Layer creates three different prompts.
The prompts are sent to the Groq LLM.
Three responses are generated.
Flask returns the responses as JSON.
Android receives and displays the responses in separate cards.
The user can compare the outputs of the three prompting strategies.
🔌 API Endpoint
Health Check
GET /health

Response:

{
  "status": "healthy"
}
Prompt Comparison
POST /prompt/compare

Request:

{
  "task": "Explain Artificial Intelligence to a first-year engineering student."
}

Response:

{
  "results": [
    {
      "strategy": "Zero-Shot",
      "response": "..."
    },
    {
      "strategy": "Few-Shot",
      "response": "..."
    },
    {
      "strategy": "Chain-of-Thought",
      "response": "..."
    }
  ]
}
🔐 Security

The Groq API key is stored in a local .env file and is not committed to GitHub.

Example:

GROQ_API_KEY=your_groq_api_key_here
GROQ_MODEL=openai/gpt-oss-120b

The actual .env file must remain private.

🎯 Learning Outcomes

Through this hands-on project, we learned:

Fundamentals of prompt engineering.
Difference between Zero-Shot and Few-Shot prompting.
Use of reasoning-oriented prompting.
How to integrate an LLM API with a Flask backend.
How Android communicates with a REST API using Retrofit.
How to structure an Android + Python AI application.
How different prompt strategies can produce different responses for the same task.
👨‍🏫 Mentor

Mentor:

Kanoj Kumar Chavalam

This project was completed as part of the 3-1 Module-2 Hands-On-3 practical learning activity under the guidance of our mentor Kanoj Kumar Chavalam.

👨‍💻 Project

Project Title: Prompting Engineering Lab
Module: 3-1 Module-2
Hands-On: 3
Domain: Prompt Engineering / Generative AI
