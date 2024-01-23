FROM python:3.10-alpine

WORKDIR /app

COPY requirements.txt /app

RUN apk update \
    && cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime \
    && echo "America/Sao_Paulo" > /etc/timezone
RUN --mount=type=cache,target=/root/.cache/pip
RUN pip3 install --upgrade pip \
    && pip3 install -r requirements.txt

COPY main.py /app

ENTRYPOINT ["python3"]
CMD [ "main.py"]