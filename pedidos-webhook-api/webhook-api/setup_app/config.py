MODE='Testing'
DEBUG=True

if MODE == 'Testing':
    # For MacOS, this fixes an issue where you can't reach other endpoints on localhost
    DB_HOST='host.docker.internal'
    BASE_URL='host.docker.internal'
else:
    DB_HOST='localhost'
    BASE_URL='localhost'

DB_PORT='33061'
DB_USER="webhook-api"
DB_PASSWORD="webhook-api"
DB_NAME="webhook-api"

TRIAL_LENGTH_DAYS=1
SQLALCHEMY_TRACK_MODIFICATIONS=False
PROTOCOL='http'
SECRET_KEY='yixx0gvc62ae67MSIEuBHMYHEDufmzMl'

# Ports for the microservices
NOTIFICATION_PORT='5002'
