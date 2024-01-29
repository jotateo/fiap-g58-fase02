THISDIR := $(notdir $(CURDIR))
PROJECT := $(THISDIR)

up_api:
	docker compose -f docker-compose-app.yml up --force-recreate --build -d

up_db:
	docker compose -f docker-compose-db.yml up --force-recreate -d

down_api:
	docker compose -f docker-compose-app.yml down --remove-orphans

down_db:
	docker compose -f docker-compose-db.yml down --remove-orphans

prune-system:
	docker system prune --force

logs_api:
	docker compose -f docker-compose-app.yml logs -f

logs_db:
	docker compose -f docker-compose-db.yml logs -f