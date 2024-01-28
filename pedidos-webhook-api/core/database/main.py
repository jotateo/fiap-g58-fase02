from os import getenv
from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base

DB_USER = getenv('POSTGRES_USER', 'webhook-api')
DB_PASSWD = getenv('POSTGRES_PASSWORD', 'webhook-api')
DB_SCHEMA = getenv('POSTGRES_DB', 'webhook-api')
DB_HOST = getenv('DB_HOST', 'localhost') # webhook-db
DB_PORT = getenv('DB_PORT', '5447')

# engine = create_engine('postgres://%s:%s@%s:%s/%s' % (DB_USER, DB_PASSWD, DB_HOST, DB_PORT, DB_SCHEMA))
engine = create_engine(f'postgresql://{DB_USER}:{DB_PASSWD}@{DB_HOST}:{DB_PORT}/{DB_SCHEMA}')

db_session = scoped_session(sessionmaker(autocommit=False,
                                         autoflush=False,
                                         bind=engine))

Base = declarative_base()
Base.query = db_session.query_property()

def init_db():
    # import all modules here that might define models so that
    # they will be registered properly on the metadata.  Otherwise
    # you will have to import them first before calling init_db()
    import core.models
    Base.metadata.create_all(bind=engine)
