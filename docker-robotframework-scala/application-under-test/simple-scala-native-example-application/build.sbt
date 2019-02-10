enablePlugins(ScalaNativePlugin)

scalaVersion := "2.11.11"

nativeLinkingOptions ++= Seq("-L" ++ baseDirectory.value.getAbsolutePath() ++ "/target")

nativeMode := "release"

nativeGC := "immix"
