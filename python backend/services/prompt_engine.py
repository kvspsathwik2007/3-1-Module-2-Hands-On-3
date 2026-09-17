import os

from groq import Groq

from prompts import (
    SYSTEM_PROMPT,
    ZERO_SHOT_PROMPT,
    FEW_SHOT_PROMPT,
    REASONING_PROMPT
)

MODEL_NAME = os.getenv(
    "GROQ_MODEL",
    "openai/gpt-oss-120b"
)


client = Groq(
    api_key=os.getenv("GROQ_API_KEY")
)


def generate_response(prompt):

    response = client.chat.completions.create(
        model=MODEL_NAME,
        messages=[
            {
                "role": "system",
                "content": SYSTEM_PROMPT
            },
            {
                "role": "user",
                "content": prompt
            }
        ],
        temperature=0.7,
        max_tokens=1000
    )

    return response.choices[0].message.content


def generate_zero_shot(task):

    prompt = ZERO_SHOT_PROMPT.format(
        task=task
    )

    return generate_response(prompt)


def generate_few_shot(task):

    prompt = FEW_SHOT_PROMPT.format(
        task=task
    )

    return generate_response(prompt)


def generate_reasoning(task):

    prompt = REASONING_PROMPT.format(
        task=task
    )

    return generate_response(prompt)


def compare_prompts(task):

    zero_shot = generate_zero_shot(task)

    few_shot = generate_few_shot(task)

    reasoning = generate_reasoning(task)

    return [
        {
            "strategy": "Zero-Shot",
            "response": zero_shot
        },
        {
            "strategy": "Few-Shot",
            "response": few_shot
        },
        {
            "strategy": "Chain-of-Thought",
            "response": reasoning
        }
    ]