from core.database import main as db
from sqlalchemy import Column, Integer, String, Float
from sqlalchemy.types import DateTime


class Payments(db.Base):
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
    date_created = Column(DateTime())
    # date_request = Column(DateTime(), server_default='NOW()')