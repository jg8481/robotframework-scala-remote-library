# Robot Framework Scala Remote Library

This is a working example of a Scala remote library based on a 2016 Robot Framework tutorial published by Thomas Jaspers.

https://blog.codecentric.de/en/2016/01/robot-framework-tutorial-2016-remote-server-keywords-in-java/

## Getting Started

1) Install Java and Maven on your machine.

2) Clone this repo, go into `robotframework-scala-remote-library`, then create the server jar by running the following commands.

```
mvn dependency:copy-dependencies
mvn clean
mvn package
```

3) Go into `target` then run the following to start the server.

```
java -jar scala-remote-library-server-1.0.jar
```

4) Go into `tests` then run the following.

```
robot .
```
