FROM python:3.10-alpine

RUN apk update && apk add postgresql-dev gcc python3-dev musl-dev && \
    cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    echo "America/Sao_Paulo" > /etc/timezone

COPY ./requirements.txt /app/requirements.txt

WORKDIR /app

RUN --mount=type=cache,target=/root/.cache/pip
RUN pip3 install --upgrade pip && \
    pip3 install -r requirements.txt \

ADD ./main.py ./main.py
ADD ./core ./core

ENTRYPOINT ["python3"]
CMD [ "main.py"]
