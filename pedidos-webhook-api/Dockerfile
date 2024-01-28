FROM python:3.10-alpine

RUN apk update && apk add postgresql-dev gcc python3-dev musl-dev && \
    cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    echo "America/Sao_Paulo" > /etc/timezone

WORKDIR /app

COPY ./main.py /app/
COPY ./core/* /app/core/
COPY ./static/* /app/static/
COPY ./templates/* /app/templates/
COPY ./requirements.txt /app/

RUN --mount=type=cache,target=/root/.cache/pip
RUN pip3 install --upgrade pip && \
    pip3 install -r requirements.txt

ENTRYPOINT ["python3"]
CMD [ "main.py"]
