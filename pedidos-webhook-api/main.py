from flask import Flask, jsonify, request

app = Flask(__name__)


@app.route("/whqrcode", methods=["POST"])
def hookQrcode():
    qrcode = request.get_json(force=True)
    print(qrcode)
    response = {"status": 200, "push-qrcode": "OK"}
    return jsonify(response)


@app.route("/health-check", methods=["GET"])
def healthcheck():
    response = {
        "status": 200,
        "version": "v0.1a"
    }
    return jsonify(response)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
