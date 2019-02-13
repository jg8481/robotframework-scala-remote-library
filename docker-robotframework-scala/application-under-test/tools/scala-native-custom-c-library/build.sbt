enablePlugins(ScalaNativePlugin)

scalaVersion := "2.11.11"

libraryDependencies += "com.github.scopt" %%% "scopt" % "3.6.0"

nativeLinkingOptions ++= Seq("-L" ++ baseDirectory.value.getAbsolutePath() ++ "/target")

nativeMode := "release"

nativeGC := "immix"
