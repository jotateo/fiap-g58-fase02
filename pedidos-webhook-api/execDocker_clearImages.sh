#!/bin/bash

docker rmi $(docker images -qa) && \
docker system prune --force
