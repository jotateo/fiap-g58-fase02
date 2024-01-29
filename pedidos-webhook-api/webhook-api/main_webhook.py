from flask import request, jsonify

# Import all the things
from setup_app import app
from webhook_action import WebhookAction

action = WebhookAction(app)


@app.route("/whorder", methods=["POST"])
def whorder():
    request_json = request.get_json(force=True)
    print(f'REQUEST JSON: {request_json}')
    print(f'request_json["order_id"] = {request_json["order_id"]}')
    print(f'request_json["order_qtd"] = {request_json["order_qtd"]}')
    print(f'request_json["order_value"] = {request_json["order_value"]}')

    return action.order_add(request)


@app.route("/whpay", methods=["POST"])
def whpay():
    request_json = request.get_json(force=True)
    print(f'REQUEST JSON: {request_json}')
    print(f'request_json["payment_id"] = {request_json["payment_id"]}')
    print(f'request_json["type"] = {request_json["type"]}')
    print(f'request_json["user_id"] = {request_json["user_id"]}')
    print(f'request_json["api_version"] = {request_json["api_version"]}')
    print(f'request_json["action"] = {request_json["action"]}')

    return action.payment_add(request)


@app.route("/health-check", methods=["GET"])
def healthcheck():
    response = {
        "status": 200,
        "version": "v0.2a"
    }
    return jsonify(response)
