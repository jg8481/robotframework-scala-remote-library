# Robot Framework Scala Remote Library

This is a working example of a Scala remote library based on a 2016 Robot Framework tutorial published by Thomas Jaspers.

- https://blog.codecentric.de/en/2016/01/robot-framework-tutorial-2016-remote-server-keywords-in-java/

Why does this exist?
- I want to learn Scala. It does a lot of neat stuff.
- I wanted to learn how the Robot Framework Remote Interface works.
- I was inspired by the simplicity of the example Thomas Jaspers made.
- Instead of hiding this Scala example on my local machine, I prefer to share it.

## Working On The Following

I will be doing a lot of experiments and improvements.
- **Docker and docker-compose capability (Windows users, please try this Docker container)**
  - Progress:
    - (February 10, 2019) Created a Docker container and docker-compose file that will build a simple Scala Native application and run the Robot Framework Scala Remote Library to test the native executable.
- ~~**sbt capability**~~ (Please use Maven. For more details go to --> https://github.com/jg8481/robotframework-scala-remote-library/issues/1)
- **Experiments with Scala Native**
  - Progress:
    - (February 10, 2019) Created a working example of a Scala Native application that will take text from the command line and pipe it into a text file.
    - (February 12, 2019) Created a working Scala Native custom C library that combines Scala and C to test gradually increasing memory allocation, memory utilization, and cpu utilization. More improvements will be coming soon.
- **Experiments with Ammonite**
  - Progress:
    - (February 7, 2019) Created a working Ammonite Library POC (proof-of-concept). Improvements and experiments will be ongoing. One of the interesting advantages of the Ammonite Library is that it does not require Maven to compile or run. Changes to the Ammonite Library can be done while the Scala keyword server is running. In general Ammonite has many capabilities. For example it has the scalaj-http library already built-in. Please check out the following for more information --> http://ammonite.io
- **Experiments with ScalaCheck**
  - Progress:
    - (February 7, 2019) Created a working ScalaCheck keyword driven by the Ammonite Library POC.
- **Experiments with Gatling.io**
- **Experiments with Javascript interop**

## Getting Started (Only for Mac OSX or Linux users)

1) Install **cURL** (must be installed in /usr/bin/curl), **Java 8**, **Maven**, and **Scala** on your machine. The rest of these steps assumes that you also have Robot Framework installed locally on your machine. If not please go here...

- https://robotframework.org/#documentation

2) Clone this repo, go into `robotframework-scala-remote-library`, then create the server jar by running the following commands.

```
mvn dependency:copy-dependencies
mvn clean
mvn package
```

3) Go into `target` then run one of the following commands to start the server.

```
java -jar scala-remote-library-server-1.0.jar

#or...

scala scala-remote-library-server-1.0.jar
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
- Tatu Kairi
- The entire Robot Framework community and its contributors
