from flask import Flask, jsonify, request
import json

app = Flask(__name__)


@app.route("/webhookpagamento", methods=["POST"])
def hookPagamento():
    response = {"status": 200}
    return jsonify(response)


@app.route("/webhookqrcode", methods=["POST"])
def hookQrcode():
    qrcode = request.json
    if not qrcode or qrcode == "":
        print('Not QRcode.')
        response = {"status": 500}
        return jsonify(response)
    else:
        # print("RQCODE: "+qrcode)
        response = {"status": 200, "push-webhook": "OK"}
        return jsonify(response)


@app.route("/health-check", methods=["GET"])
def healthcheck():
    response = {
        "status": 200,
        "version": "v0.1a"
    }
    return jsonify(response)


if __name__ == '__main__':
    app.run()
