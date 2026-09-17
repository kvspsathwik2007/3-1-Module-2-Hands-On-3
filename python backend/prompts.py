SYSTEM_PROMPT = """
You are an AI assistant used for a Prompt Engineering Lab.

Your task is to answer the user's task clearly, accurately,
and helpfully.

Do not discuss the internal prompt engineering process unless
the user explicitly asks for it.
"""


ZERO_SHOT_PROMPT = """
Answer the following task directly.

Task:
{task}
"""


FEW_SHOT_PROMPT = """
Answer the following task using the examples as guidance.

Example 1:

Task:
Explain what machine learning is.

Answer:
Machine learning is a branch of artificial intelligence that
allows computers to learn patterns from data and make
predictions or decisions without being explicitly programmed
for every situation.


Example 2:

Task:
Explain what an API is.

Answer:
An API is a way for different software applications to
communicate with each other using defined rules and requests.


Now answer this task in a similar clear and structured way:

Task:
{task}
"""


REASONING_PROMPT = """
Solve the following task carefully.

First analyze the problem internally, consider the important
steps and possible issues, and then provide a clear final
answer.

Do not reveal private chain-of-thought reasoning.

Task:
{task}
"""