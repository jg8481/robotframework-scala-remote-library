#! /bin/bash

clear
TIMESTAMP=$(date)

echo "------------------------------------[[[[ Building Robot Framework Scala Remote Library Server And Gatling Jars ]]]]------------------------------------"
echo
mvn clean
mvn dependency:copy-dependencies &&
mvn package &&
cd ./target/tools/scala-remote-library-gatling-jar
mvn clean
mvn dependency:copy-dependencies &&
mvn package
exit