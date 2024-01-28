THISDIR := $(notdir $(CURDIR))
PROJECT := $(THISDIR)

up: down
	docker compose -f docker/docker-compose.yml up --force-recreate --build -d

down:
	docker compose -f docker/docker-compose.yml down --remove-orphans

logs:
	docker compose -f docker/docker-compose.yml logs -f