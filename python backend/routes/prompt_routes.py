from flask import Blueprint, jsonify, request

from services.prompt_engine import compare_prompts


prompt_routes = Blueprint(
    "prompt_routes",
    __name__
)


@prompt_routes.route("/", methods=["GET"])
def home():

    return jsonify({
        "message": "Prompting Engineering Lab API",
        "status": "running"
    })


@prompt_routes.route("/health", methods=["GET"])
def health():

    return jsonify({
        "status": "healthy"
    })


@prompt_routes.route("/prompt/compare", methods=["POST"])
def compare():

    try:

        data = request.get_json()

        if not data:

            return jsonify({
                "error": "Request body is required."
            }), 400

        task = data.get("task", "").strip()

        if not task:

            return jsonify({
                "error": "Task cannot be empty."
            }), 400

        results = compare_prompts(task)

        return jsonify({
            "results": results
        }), 200

    except Exception as exception:

        print("ERROR:", exception)

        return jsonify({
            "error": "Failed to generate prompt responses.",
            "details": str(exception)
        }), 500