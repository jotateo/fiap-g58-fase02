from webhook_models import Orders, Payments, db


class WebhookAccess():
    def __init__(self):
        self.db = db
        self.Orders = Orders
        self.Payments = Payments

    def get_order(self, order_id=None, get_all=False, as_dict=False):
        if order_id != None:
            order_obj = self.Orders.query.filter_by(user_id=order_id).order_by(
                self.Orders.created_date.desc())

        if get_all:
            order_obj = order_obj.all()
        else:
            order_obj = order_obj.first()

        if as_dict and order_obj != None:
            return [self.Orders.as_dict(order_) for order_ in order_obj]

        return order_obj

    def get_payment(self, payment_id=None, get_all=False, as_dict=False):
        if payment_id != None:
            pay_obj = self.Payments.query.filter_by(user_id=payment_id).order_by(
                self.Payments.created_date.desc())

        if get_all:
            pay_obj = pay_obj.all()
        else:
            pay_obj = pay_obj.first()

        if as_dict and pay_obj != None:
            return [self.Payments.as_dict(pay_) for pay_ in pay_obj]

        return pay_obj

    def create_order(self, order_dict):
        order_row = self.Orders(**order_dict)
        self.db.session.add(order_row)
        self.db.session.commit()

    def update_order_by_dict(self, order_id, order_obj):
        order_row = self.Orders.query.filter_by(id=order_id).first()
        order_row.update(**order_obj)
        self.db.session.commit()

    def create_payment(self, payment_dict):
        payment_row = self.Payments(**payment_dict)
        self.db.session.add(payment_row)
        self.db.session.commit()

    def update_payment_by_dict(self, pay_id, pay_obj):
        payment_row = self.Payments.query.filter_by(id=pay_id).first()
        payment_row.update(**pay_obj)
        self.db.session.commit()
