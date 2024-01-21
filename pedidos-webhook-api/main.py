from flask import Flask, jsonify, request
import json

app = Flask(__name__)

@app.route("/webhookpagamento", methods=["POST"])
def hookPagamento():
    response = {"status":200}
    return jsonify(response)

@app.route("/webhookqrcode", methods=["POST"])
def hookQrcode():
    qrcode = request.json
    data = request.json
    with open('data.txt','a') as outfile:
        outfile.write("\n")
        json.dump(data,qrcode)
    return jsonify(qrcode)

@app.route("/health-check", methods=["GET"])
def healthcheck():
    response = {
        "status": 200,
        "version": "v0.1a"
    }
    return jsonify(response)

if __name__ == '__main__':
    app.run()