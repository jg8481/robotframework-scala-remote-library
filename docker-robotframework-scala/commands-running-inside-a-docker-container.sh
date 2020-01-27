#!/bin/bash

echo
echo
echo 'TEST_ENVIRONMENT='$TEST_ENVIRONMENT
echo
echo
rm -rf /rfw/message.txt
cd /rfw
rm -rf ./results
echo
echo
ls -l
cd ./simple-scala-native-example-application
sbt clean &&
make &&
sbt run
cd ..
nohup java -jar ./tools/scala-remote-library-server-1.0.jar  >/dev/null 2>&1 &
sleep 5s
cd ./tests
robot -N "This $TEST_ENVIRONMENT Docker container will build a simple Scala Native application and test it with Robot Framework Scala Keywords -- " ./ScalaNativeKeywordsTest.robot
echo
echo

