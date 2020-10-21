#!/bin/bash

set -e

cd "$(dirname "$0")"

cd ..
mvn package
cd docker
cp ../target/rdrandms-*-jar-with-dependencies.jar app.jar

docker build . -t zarmin/rdrandms:0.1
docker push zarmin/rdrandms:0.1
