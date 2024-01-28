from core.database import main as Base
from sqlalchemy import Column, Integer, String, Float
from sqlalchemy.types import DateTime


class Orders(Base):
    """
    WH Payments table
    """
    __tablename__ = 'orders'
    id = Column(Integer, primary_key=True)
    order_id = Column(String(256), unique=True)
    order_qtd = Column(Integer)
    order_value = Column(Float)
    date_created = Column(DateTime())
    # date_request = Column(DateTime(), server_default='NOW()')