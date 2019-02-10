# Simple Scala Native Example Application

This is a working example of a Scala Native application that will take text from the command line and pipe it into a text file. The provided `simple-scala-native-example-application-out` executable was compiled on a Debian machine and will most likely only work in a Debian environment. For more information please go here --> https://scala-native.readthedocs.io/en/v0.3.8/user/setup.html#installing-clang-and-runtime-dependencies

```
# Usage example:
$ ./simple-scala-native-example-application-out "hi"
$ ls
message.txt  simple-scala-native-example-application-out
$ cat ./message.txt
||    This part of the message was created with Scala Native using C...    ||||    This part of the message was created with robotframework-scala-remote-library Scala keywords...    || ----> hi
```

## Building The Scala Native Executable

If you already have Scala and the runtime dependencies installed on your local machine, you can build your own executable using the following commands.

```
sbt clean &&
make &&
sbt run
```
