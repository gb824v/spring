.DEFAULT_GOAL := all
SHELL := /bin/bash

$(shell echo OS=$(OS) > .env)
ADMIN := sudo

ifeq ($(OS), Windows_NT) # Windows
DEL := del
# Override ADMIN for Windows
ADMIN :=
IMAGE_NAME=$(shell mvn help:evaluate -Dexpression=docker.imageName | FINDSTR /v INFO)
HOST_IP_ADDR := $(strip $(shell FOR /F "tokens=2 delims=:" %%A IN ('ipconfig ^| findstr /i ipv4') DO echo %%A))
$(shell echo HOST_IP=$(HOST_IP_ADDR) >> .env)
endif # Windows OS

ifeq ($(shell uname -s), Darwin) # MacOS
DEL := rm -rf
IMAGE_NAME := $(shell mvn help:evaluate -Dexpression=docker.imageName | grep -v 'INFO')
$(shell echo HOST_IP="$(shell ipconfig getifaddr en0 || ipconfig getifaddr en3)" >> .env)
endif # MacOS

ifeq ($(shell uname -s), Linux)
DEL := rm -rf
ETH0 := $(shell ifconfig eth0 | grep 'inet addr' | cut -d: -f2 | grep -o '^\S*')
ENP0S3 := $(shell ifconfig enp0s3 | grep 'inet addr' | cut -d: -f2 | grep -o '^\S*')
IMAGE_NAME := $(shell mvn help:evaluate -Dexpression=docker.imageName | grep -v 'INFO')
ifneq ($(ETH0),) # Not Blank
$(shell echo HOST_IP=$(ETH0) >> .env)
endif # ETH0
ifneq ($(ENP0S3), ) # Not Blank
$(shell echo HOST_IP=$(ENP0S3) >> .env)
endif # ENP0S3
endif # Linux OS

CONTAINER_NAME=app_container
DOCKER_ROOT=src/main/docker
DOCKER_COMPOSE_ROOT=$(DOCKER_ROOT)/compose
COMPOSE_COMMON=docker-compose -f ${DOCKER_COMPOSE_ROOT}/common-services.yml
COMPOSE_DEV=${COMPOSE_COMMON} -f ${DOCKER_COMPOSE_ROOT}/dev-services.yml
DOCKER_REGISTRY=docker-registry.aeg.cloud

# Export certain environment variables to a .env file for reference
$(shell echo IMAGE_NAME=$(IMAGE_NAME) >> .env)
$(shell echo CONTAINER_NAME=$(CONTAINER_NAME) >> .env)
$(shell echo DOCKER_REGISTRY=$(DOCKER_REGISTRY) >> .env)


# Completely remove all Docker containers, images, and volumes. FOR LOCAL USE ONLY.
full-docker-cleanup:	clean
	$(ADMIN) bash ${DOCKER_ROOT}/local_dev_cleanup/docker-cleanup.sh

report-maven-dependencies:
	$(ADMIN) $(DEL) report.txt
	mvn dependency:tree -DoutputFile=report.txt

# Stop and remove Docker containers defined in the common-services.yml Compose file, and delete the Maven target directory
clean:
	$(ADMIN) ${COMPOSE_COMMON} down
	$(ADMIN) $(DEL) target

compile:
	@echo "Building source..."
	mvn clean package

all:	clean compile
	@echo "Building image..."
	mvn docker:build -X

test:
	mvn clean test

# Bring up all services defined in common-services.yml
up:
	$(ADMIN) ${COMPOSE_COMMON} up -d

# Bring up the application service defined in common-services.yml, with dev-local configuration
up-app-local:
	$(ADMIN) ${COMPOSE_DEV} up -d app

# Bring up all services defined in common-services.yml, with dev-local configuration
up-dev-local:
	$(ADMIN) ${COMPOSE_DEV} up -d

# Run the appinfo.sh script in the Docker container. Try first as if container is running, then try as if container is stopped
get-sdk-version:
	$(MAKE) get-sdk-version-running || $(MAKE) get-sdk-version-stopped

get-sdk-version-running:
	$(ADMIN) docker exec -it ${CONTAINER_NAME} bash -c "sh appinfo.sh"

get-sdk-version-stopped:
	$(ADMIN) docker commit ${CONTAINER_NAME} test_${CONTAINER_NAME}
	$(ADMIN) docker run -ti --entrypoint=/appinfo.sh test_${CONTAINER_NAME}