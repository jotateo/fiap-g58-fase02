THISDIR := $(notdir $(CURDIR))
PROJECT := $(THISDIR)

up: down
	docker compose -f docker/docker-compose.yml up --force-recreate --build -d

updb:
	docker compose -f docker/docker-compose.yml up webhook-db --force-recreate -d && \
	docker compose -f docker/docker-compose.yml up webhook-db-admin --force-recreate -d

down:
	docker compose -f docker/docker-compose.yml down --remove-orphans

prune-system:
	docker system prune --force

logs:
	docker compose -f docker/docker-compose.yml logs -f

