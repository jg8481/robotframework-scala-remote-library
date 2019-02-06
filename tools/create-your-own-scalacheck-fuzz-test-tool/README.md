# Create Your Own ScalaCheck Fuzz Test Tool

Use this sub-project to modfiy the ScalaCheck code and create your own command line black box fuzz testing tool.

## Getting Started

1) Install Scala and sbt on your machine.

2) Create the ScalaCheck fuzz testing jar by running the following commands.

```
sbt clean
sbt assembly
```

3) Check the jar in the `target` folder. It should have a `scalacheck-generic-test-data-generator.jar`.
