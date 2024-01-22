FROM python:3.10-alpine

WORKDIR /app

COPY requirements.txt /app

RUN --mount=type=cache,target=/root/.cache/pip
RUN pip3 install --upgrade pip
RUN pip3 install -r requirements.txt

COPY main.py /app

ENTRYPOINT ["python3"]
CMD [ "main.py"]