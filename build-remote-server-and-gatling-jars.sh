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
cd ../../../
rm -rf ./docker-robotframework-scala/application-under-test/tools/scala-remote-library-gatling-jar-1.0.0.jar
rm -rf ./docker-robotframework-scala/application-under-test/tools/scala-remote-library-server-1.0.jar
cp ./target/tools/scala-remote-library-gatling-jar/target/scala-remote-library-gatling-jar-1.0.0.jar ./docker-robotframework-scala/application-under-test/tools/scala-remote-library-gatling-jar-1.0.0.jar
cp ./target/scala-remote-library-server-1.0.jar ./docker-robotframework-scala/application-under-test/tools/scala-remote-library-server-1.0.jar
exit
exit