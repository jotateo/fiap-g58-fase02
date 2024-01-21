FROM python:3.10-alpine

COPY ./requirements.txt /app/requirements.txt
COPY ./main.py /app

WORKDIR /app

RUN pip install --upgrade pip
RUN pip install -r requirements.txt

CMD [ "python3", "main.py"]