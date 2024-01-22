import os
from flask import Flask, jsonify, request
from redis import Redis

app = Flask(__name__)

redis_host = os.getenv('REDISTOGO_HOST', 'localhost')
redis_port = os.getenv('REDISTOGO_PORT', '6379')
redis_expire_time = os.getenv('REDIS_EXPIRE_MSG_TIME', 10)

redis = Redis(host=redis_host, port=redis_port)


def searchRedisValue(value):
    return redis.get(value)


@app.route("/whqrcode", methods=["POST"])
def hookQrcode():
    request_json = request.get_json(force=True)
    print(f'REQUEST JSON: {request_json}')
    qr_code = request_json["qrcode"]
    qr_id = request_json["id"]
    print(f'QRCODE: {qr_code}\nID: {qr_id} ')

    ## valida se valor ja existe
    validate = searchRedisValue(qr_id)
    print(f'Validacao: {validate}')

    if not validate:
        print(f'Inserindo dados [{qr_id}]')
        redis.setex(qr_id, redis_expire_time, qr_code)

    response = {"status": 200, "push-qrcode": "OK"}
    return jsonify(response)


@app.route("/findRedisKey", methods=["GET"])
def findRedisKey():
    request_json = request.get_json(force=True)
    print(f'REQUEST JSON: {request_json}')
    qr_id = request_json["id"]
    ## valida se valor ja existe
    validate = searchRedisValue(qr_id)
    if not validate:
        print(f'A chave [{qr_id}] nao existe.')
        response = {"status": 500, "error": f"A chave [{qr_id}] nao existe."}
        return jsonify(response)
    else:
        response = {"status": 200, "data": f"[{qr_id} = {validate}]"}
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
