#!/usr/bin/env sh

docker stop $(docker ps -q --filter ancestor=hotel-room-allocation)
docker rm $(docker ps -aq --filter ancestor=hotel-room-allocation)

./gradlew clean build

docker build -t hotel-room-allocation .

docker run -d -p 8080:8080 hotel-room-allocation


