# Robot Framework Scala Remote Library

This is a working example of a Scala remote library based on a 2016 Robot Framework tutorial published by Thomas Jaspers.

- https://blog.codecentric.de/en/2016/01/robot-framework-tutorial-2016-remote-server-keywords-in-java/

Why does this exist?
- I want to learn Scala. It does a lot of neat stuff.
- I wanted to learn how the Robot Framework Remote Interface works.
- I was inspired by the simplicity of the example Thomas Jaspers made.
- Instead of hiding this Scala example on my local machine, I prefer to share it.

## Coming Soon

I will be doing a lot of experiments and improvements. The following are coming soon...
- Docker and docker-compose capability
- ~~sbt capability~~ (Please use Maven. For more details go to --> https://github.com/jg8481/robotframework-scala-remote-library/issues/1)
- Experiments with Scala Native
- Experiments with Ammonite
- Experiments with ScalaCheck
- Experiments with Gatling.io
- Experiments with Javascript interop

## Getting Started

1) Install Java and Maven on your machine. The rest of these steps assumes that you also have Robot Framework installed locally on your machine. If not please go here...

- https://robotframework.org/#documentation

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

***

## Big thank you to the following people and groups.

- Pekka Klarck
- Thomas Jaspers
- Li Haoyi
- The entire Robot Framework community and its contributors
