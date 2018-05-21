#!/usr/bin/env bash

echo "Running docker cleanup script"

echo "Stopping all containers"
docker stop $(docker ps -a -q) # Stop all containers

echo "Removing stopped containers"
docker rm $(docker ps -a -q) # Remove all stopped containers

echo "Removing dangling images"
docker rmi $(sudo docker images --filter "dangling=true" -q --no-trunc)

echo "Removing all images"
docker rmi -f $(docker images -a -q) # FORCE Remove all images

echo "Removing all volumes"
docker volume rm $(docker volume ls -qf dangling=true) # Remove all volumes