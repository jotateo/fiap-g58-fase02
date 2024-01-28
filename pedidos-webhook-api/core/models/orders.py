from core.database import main as db
from sqlalchemy import Column, Integer, String, Float
from sqlalchemy.types import DateTime


class Orders(db.Base):
    """
    WH Payments table
    """
    __tablename__ = 'orders'
    id = Column(Integer, primary_key=True)
    order_id = Column(String(256), unique=True)
    order_qtd = Column(Integer)
    order_value = Column(Float)
    date_created = Column(DateTime)
    # date_request = Column(DateTime(), server_default='NOW()')

    def __init__(self, order_id, order_qtd, order_value, date_created):
        self.order_id = order_id
        self.order_qtd = order_qtd
        self.order_value = order_value
        self.date_created = date_created