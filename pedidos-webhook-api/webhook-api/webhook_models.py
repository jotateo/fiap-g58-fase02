from setup_app import db

from sqlalchemy import Column, Integer, DateTime, String, Float, Boolean, ForeignKey, Text
from sqlalchemy.sql import func


class Orders(db.Model):
    """
    WH Orders table
    """
    __tablename__ = 'orders'
    id = Column(Integer, primary_key=True)
    order_id = Column(String(256), unique=True)
    order_qtd = Column(Integer)
    order_value = Column(Float)
    date_created = Column(DateTime, server_default=func.now())

    # date_request = Column(DateTime(), server_default='NOW()')

    def __init__(self, order_id, order_qtd, order_value):
        self.order_id = order_id
        self.order_qtd = order_qtd
        self.order_value = order_value


class Payments(db.Model):
    """
    WH Payments table
    """
    __tablename__ = 'payments'
    id = Column(Integer, primary_key=True)
    payment_id = Column(String(256), unique=True)
    type = Column(String(256))
    user_id = Column(Integer)
    api_version = Column(String(256))
    action = Column(String(256))
    date_created = Column(DateTime, server_default=func.now())

    # date_request = Column(DateTime(), server_default='NOW()')

    def __init__(self, payment_id, type, user_id, api_version, action):
        self.payment_id = payment_id
        self.type = type
        self.user_id = user_id
        self.api_version = api_version
        self.action = action
