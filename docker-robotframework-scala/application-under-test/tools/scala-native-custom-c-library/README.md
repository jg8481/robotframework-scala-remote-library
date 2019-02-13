# Scala Native Custom C Library

This is a working example of a Scala Native C library that combines Scala and C. The provided `scala-native-custom-c-library` executable was compiled on a Debian machine and will most likely only work in a Debian environment. For more information please go here --> https://scala-native.readthedocs.io/en/v0.3.8/user/setup.html#installing-clang-and-runtime-dependencies

## Building The Scala Native Executable

If you already have Scala and the runtime dependencies installed on your local machine, you can build your own executable using the following commands.

```
sbt clean &&
make &&
sbt run
```
