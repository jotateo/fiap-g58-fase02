import json, traceback
from flask import jsonify
from webhook_db import WebhookAccess
from webhook_models import Orders, Payments

db_access = WebhookAccess()


class WebhookAction():
    def __init__(self, app):
        self.app = app

    def payment_add(self, request):
        try:
            data = request.get_json(force=True)
            payment = Payments(data['payment_id'], data['type'], data['user_id'], data['api_version'], data['action'])
            db_access.create_payment(payment.dict())
        except Exception as ex:
            stacktrace = traceback.format_exc()
            print(stacktrace)
            return json.dumps({'message': 'Unknown error, we apologize'}), 500

    def payment_read(self, request):
        try:
            data = request.get_json(force=True)
            pay_id = data['payment_id']

            payment = db_access.get_payment(payment_id=pay_id)
            payment_update = dict(isRead=True)

            db_access.update_payment_by_dict(payment.id, payment_update)

            return json.dumps({'message': ''}), 200
        except Exception as ex:
            stacktrace = traceback.format_exc()
            print(stacktrace)
            return json.dumps({'message': 'Unknown error, we apologize'}), 500

    def get_payments(self, pay_id):
        try:
            payment = db_access.get_payment(payment_id=pay_id, get_all=True, as_dict=True)

            if payment != None:
                return jsonify(payment), 200
            else:
                return json.dumps({'message': 'Payment was not found'}), 404
        except Exception as ex:
            stacktrace = traceback.format_exc()
            print(stacktrace)
            return json.dumps({'message': 'Unknown error, we apologize'}), 500

    def order_add(self, request):
        try:
            data = request.get_json(force=True)
            order = Orders(data['order_id'], data['order_qtd'], data['order_value'])
            db_access.create_order(order.dict())
        except Exception as ex:
            stacktrace = traceback.format_exc()
            print(stacktrace)
            return json.dumps({'message': 'Unknown error, we apologize'}), 500

    def order_read(self, request):
        try:
            data = request.get_json(force=True)
            order_id = data['order_id']

            order = db_access.get_order(order_id=order_id)
            order_update = dict(isRead=True)

            db_access.update_order_by_dict(order.id, order_update)

            return json.dumps({'message': ''}), 200
        except Exception as ex:
            stacktrace = traceback.format_exc()
            print(stacktrace)
            return json.dumps({'message': 'Unknown error, we apologize'}), 500

    def get_orders(self, order_id):
        try:
            order = db_access.get_order(order_id=order_id, get_all=True, as_dict=True)

            if order != None:
                return jsonify(order), 200
            else:
                return json.dumps({'message': 'Payment was not found'}), 404
        except Exception as ex:
            stacktrace = traceback.format_exc()
            print(stacktrace)
            return json.dumps({'message': 'Unknown error, we apologize'}), 500
