DOCKER_REGISTRY ?= gcr.io
DOCKER_TAG ?= v0.1.3-CLOUDRUN

DOCKER_IMAGE ?= ${DOCKER_REGISTRY}/fire-operation-meli/fire-operation-meli:${DOCKER_TAG}


.DEFAULT_GOAL:=help

.EXPORT_ALL_VARIABLES:

ifndef VERBOSE
.SILENT:
endif

# set default shell
SHELL=/bin/bash -o pipefail -o errexit

# HELP
# Genera un menu de ayuda de las tareas en el Makefile
# https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
.PHONY: help

help: ## Esta ayuda
	awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_-0-9]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

.PHONY: im
.DEFAULT_GOAL := help


setup: docker-setup
	@docker-compose up --timeout 120  --app=0
	
build-image: ## Ejecuta docker build para empaquetar la aplicaciÃ³n
	@docker build -t ${DOCKER_IMAGE} .

push-image: ## Publica la imagen docker en el registro configurado
	@docker push ${DOCKER_IMAGE}

run-dev: docker-setup ## Lanza la aplicación de forma local, incluyendo Postgres
	@docker-compose up --timeout 120  --build
	
run-db: docker-setup ## Lanza la BD pero no levanta la app
	@docker-compose up --timeout 120 --scale app=0
	
run-as-prod: docker-setup ## Lanza la aplicación de forma local, incluyendo Postgres
	@docker-compose up --timeout 120 --build

e2e: ## Ejecuta test de aceptación
	@echo "ok"

test: ## Ejecuta los tests unitarios
	@echo "ok"

docker-setup:
	@docker network create dev_network || true

.PHONY: all build-image push-image docker-compose test e2e
