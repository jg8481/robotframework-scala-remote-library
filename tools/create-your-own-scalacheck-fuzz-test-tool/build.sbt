//WARNING: Please don't change the scalaVersion. If you need to do it, you will need to change "scala-2.11" in the paths that are hardcoded in the ScalaKeywords.scala file.
scalaVersion := "2.11.11"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.5"
assemblyJarName in assembly := s"scalacheck-generic-test-data-generator.jar"
