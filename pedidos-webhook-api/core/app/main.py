# import datetime

from flask import Flask, jsonify, request
from os import getenv

# from core.models import payments
from core.models import orders
from core.database import main as db

app = Flask(__name__)
app.secret_key = getenv('APP_SECRET_KEY', 'testeXXX')
FLAG_DEBUG = getenv('FLAG_DEBUG', False)

def build_results(payment):
    response = []
    for pay in payment:
        response.append(pay.dict())
    return { "results": response }


@app.route("/whorder", methods=["POST"])
def whorder():
    request_json = request.get_json(force=True)
    print(f'REQUEST JSON: {request_json}')

    ## Ao criar o pedido o webhook sera chamado para registrar este na base
    order = orders(order_id=request_json["order_id"], order_qtd=request_json["order_qtd"])
    db.db_session.add(order)
    db.db_session.commit()
    response = {"status": 200, "return": f'{request_json}'}
    return jsonify(response)


@app.route("/whpay", methods=["POST"])
def whpay():
    request_json = request.get_json(force=True)
    print(f'REQUEST JSON: {request_json}')
    qr_code = request_json["qrcode"]
    qr_id = request_json["id"]
    print(f'QRCODE: {qr_code}\nID: {qr_id} ')

    response = {"status": 200, "return": f'{request_json}'}
    return jsonify(response)


@app.route("/health-check", methods=["GET"])
def healthcheck():
    response = {
        "status": 200,
        "version": "v0.2a"
    }
    return jsonify(response)


def __init__():
    print(f'iniciando app...')
    app.run(host='0.0.0.0', port=5000, debug=FLAG_DEBUG)
