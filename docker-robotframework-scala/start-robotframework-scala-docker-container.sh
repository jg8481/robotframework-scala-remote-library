#! /bin/bash

clear
TIMESTAMP=$(date)


if [ "$1" == "Build-Docker-Container" ]; then
  source ./.env
  echo
  echo "------------------------------------[[[[ Build Robot Framework Docker Containers ]]]]------------------------------------"
  echo
  echo "This will build the Docker images defined in the docker-compose.yml file. This run started on $TIMESTAMP."
  echo
  docker-compose -f docker-compose.yml down
  docker-compose -f docker-compose.yml rm -f
  docker-compose -f docker-compose.yml build
  TIMESTAMP2=$(date)
  echo "This build ended on $TIMESTAMP2."
fi

if [ "$1" == "Run-Docker-Container" ]; then
  # Before running this step you should update the ".env" file to the values you need.
  source ./.env
  echo
  echo "------------------------------------[[[[ Robot Framework Docker Container - Scala Native Application Tests ]]]]------------------------------------"
  echo
  echo "This Docker Container will run various Robot Framework tests on a simple Scala Native Application. This run started on $TIMESTAMP."
  echo
  docker-compose -f docker-compose.yml down
  docker-compose -f docker-compose.yml rm -f
  docker-compose -f docker-compose.yml build
  docker-compose run docker-robot-framework-scala-test-runner commands-running-inside-a-docker-container.sh
  docker stop $(docker ps -a -q) &&
  docker rm $(docker ps -a -q)
  TIMESTAMP2=$(date)
  echo "This test run ended on $TIMESTAMP2."
fi