package com.qamercenaryforhire.robotframework.scalaremotelibrary.keywords

import org.robotframework.javalib.annotation.ArgumentNames
import org.robotframework.javalib.annotation.RobotKeyword
import org.robotframework.javalib.annotation.RobotKeywords
import scala.io._
import scala.util._
import scala.sys._
import scala.sys.process._
import scala.sys.process.Process
import scala.sys.process.ProcessLogger
import scala.collection.JavaConversions._



@RobotKeywords
class ScalaKeywords {

  @RobotKeyword("Say Something")
  @ArgumentNames(Array("args"))
  def saySomething(args: String): Unit = {
    println(args)
  }

  @RobotKeyword("Just Text")
  @ArgumentNames(Array())
  def justText(): String = "Nothing special"

  @RobotKeyword("Check String")
  @ArgumentNames(Array("args"))
  def checkString(args: AnyRef): String = args.getClass.getSimpleName

  @RobotKeyword("Run Scala Check Data Generator")
  @ArgumentNames(Array("args"))
  //This keyword deals with completely randomized QuickCheck-style side effects for fuzz testing.
  def runScalaCheckDataGenerator(args: Int): Unit = {
    if(args == 1){
      val path = "pwd".!!.trim
      //Reset the scalachecktestdata.txt test data file.
      s"rm -rf $path/tools/test-data-logs/scalachecktestdata.txt".!
      s"touch $path/tools/test-data-logs/scalachecktestdata.txt".!
      //Then run the ScalaCheck test data generator.
      s"sleep 1".!
      var i = 0
      for {
        i <- 1 to 5
        val mediumFuzzTest = Seq("/bin/bash", "-c",
        "path=$(pwd) && $path/tools/ammonite-library/Ammonite --no-remote-logging $path/tools/ammonite-library/AmmoniteLibrary.scala scalaCheckRunner >> $path/tools/test-data-logs/scalachecktestdata.txt").!!
      }
      s"sleep 1".!
      val prepareTestData = Seq("/bin/bash", "-c",
      "path=$(pwd) && cp $path/tools/test-data-logs/scalachecktestdata.txt $path/tools/test-data-logs/temporarydata.txt && cat $path/tools/test-data-logs/temporarydata.txt | sed -e 's/Some//g' | sed -e 's/(//g' | sed -e 's/)//g' > $path/tools/test-data-logs/scalachecktestdata.txt").!!
      s"rm -rf $path/tools/test-data-logs/temporarydata.txt".!
    }
    if(args == 2){
      val path = "pwd".!!.trim
      //Reset the scalachecktestdata.txt test data file.
      s"rm -rf $path/tools/test-data-logs/scalachecktestdata.txt".!
      s"touch $path/tools/test-data-logs/scalachecktestdata.txt".!
      //Then run the ScalaCheck test data generator.
      s"sleep 1".!
      var i = 0
      for {
        i <- 1 to 20
        val heavyFuzzTest = Seq("/bin/bash", "-c",
        "path=$(pwd) && $path/tools/ammonite-library/Ammonite --no-remote-logging $path/tools/ammonite-library/AmmoniteLibrary.scala scalaCheckRunner >> $path/tools/test-data-logs/scalachecktestdata.txt").!!
      }
      s"sleep 1".!
      val prepareTestData = Seq("/bin/bash", "-c",
      "path=$(pwd) && cp $path/tools/test-data-logs/scalachecktestdata.txt $path/tools/test-data-logs/temporarydata.txt && cat $path/tools/test-data-logs/temporarydata.txt | sed -e 's/Some//g' | sed -e 's/(//g' | sed -e 's/)//g' > $path/tools/test-data-logs/scalachecktestdata.txt").!!
      s"rm -rf $path/tools/test-data-logs/temporarydata.txt".!
    }
  }

  @RobotKeyword("Ammonite Initialization")
  @ArgumentNames(Array())
  def ammoniteInitialization() = {
    val path = "pwd".!!.trim
    s"$path/tools/ammonite-library/Ammonite --no-remote-logging $path/tools/ammonite-library/AmmoniteInitialize.scala"!!
  }

  @RobotKeyword("Run Ammonite HTTP Post Test")
  @ArgumentNames(Array())
  def runAmmoniteHttpPostTest(): String = {
    val path = "pwd".!!.trim
    s"$path/tools/ammonite-library/Ammonite --no-remote-logging $path/tools/ammonite-library/AmmoniteLibrary.scala postRequest qa test"!!
  }

  @RobotKeyword("Run Ammonite HTTP Get Test")
  @ArgumentNames(Array())
  def runAmmoniteHttpGetTest(): String = {
    val path = "pwd".!!.trim
    s"$path/tools/ammonite-library/Ammonite --no-remote-logging $path/tools/ammonite-library/AmmoniteLibrary.scala getRequest"!!
  }

  @RobotKeyword("Run Ammonite HTTP Fuzz Test")
  @ArgumentNames(Array())
  def runAmmoniteHttpFuzzTest(): String = {
    val path = "pwd".!!.trim
    s"$path/tools/ammonite-library/Ammonite --no-remote-logging $path/tools/ammonite-library/AmmoniteLibrary.scala patchFuzzTest"!!
  }

  @RobotKeyword("Run Scala Check Data Generator In Docker")
  @ArgumentNames(Array("args"))
  //This keyword deals with completely randomized QuickCheck-style side effects for fuzz testing.
  def runScalaCheckDataGeneratorInDocker(args: Int): Unit = {
    if(args == 1){
      //Reset the scalachecktestdata.txt test data file.
      s"rm -rf /rfw/tools/test-data-logs/scalachecktestdata.txt".!
      s"touch /rfw/tools/test-data-logs/scalachecktestdata.txt".!
      //Then run the ScalaCheck test data generator.
      s"sleep 1".!
      var i = 0
      for {
        i <- 1 to 5
        val mediumFuzzTest = Seq("/bin/bash", "-c",
        "/rfw/tools/ammonite-library/Ammonite --no-remote-logging /rfw/tools/ammonite-library/AmmoniteLibrary.scala scalaCheckRunner >> /rfw/tools/test-data-logs/scalachecktestdata.txt").!!
      }
      s"sleep 1".!
      val prepareTestData = Seq("/bin/bash", "-c",
      "cp /rfw/tools/test-data-logs/scalachecktestdata.txt /rfw/tools/test-data-logs/temporarydata.txt && cat /rfw/tools/test-data-logs/temporarydata.txt | sed -e 's/Some//g' | sed -e 's/(//g' | sed -e 's/)//g' > /rfw/tools/test-data-logs/scalachecktestdata.txt").!!
      s"rm -rf /rfw/tools/test-data-logs/temporarydata.txt".!
    }
    if(args == 2){
      //Reset the scalachecktestdata.txt test data file.
      s"rm -rf /rfw/tools/test-data-logs/scalachecktestdata.txt".!
      s"touch /rfw/tools/test-data-logs/scalachecktestdata.txt".!
      //Then run the ScalaCheck test data generator.
      s"sleep 1".!
      var i = 0
      for {
        i <- 1 to 20
        val heavyFuzzTest = Seq("/bin/bash", "-c",
        "/rfw/tools/ammonite-library/Ammonite --no-remote-logging /rfw/tools/ammonite-library/AmmoniteLibrary.scala scalaCheckRunner >> /rfw/tools/test-data-logs/scalachecktestdata.txt").!!
      }
      s"sleep 1".!
      val prepareTestData = Seq("/bin/bash", "-c",
      "cp /rfw/tools/test-data-logs/scalachecktestdata.txt /rfw/tools/test-data-logs/temporarydata.txt && cat /rfw/tools/test-data-logs/temporarydata.txt | sed -e 's/Some//g' | sed -e 's/(//g' | sed -e 's/)//g' > /rfw/tools/test-data-logs/scalachecktestdata.txt").!!
      s"rm -rf /rfw/tools/test-data-logs/temporarydata.txt".!
    }
  }

  @RobotKeyword("Run Ammonite Scala Native Executable Enter Letters Test")
  @ArgumentNames(Array())
  def runAmmoniteScalaNativeExecutableEnterLettersTest(): Unit = {
    val path = "pwd".!!.trim
    s"/rfw/tools/ammonite-library/Ammonite --no-remote-logging /rfw/tools/ammonite-library/AmmoniteLibrary.scala scalaNativeLettersTest"!!
  }

  @RobotKeyword("Run Ammonite Scala Native Executable Enter Numbers Test")
  @ArgumentNames(Array())
  def runAmmoniteScalaNativeExecutableEnterNumbersTest(): Unit = {
    val path = "pwd".!!.trim
    s"/rfw/tools/ammonite-library/Ammonite --no-remote-logging /rfw/tools/ammonite-library/AmmoniteLibrary.scala scalaNativeNumbersTest"!!
  }

  @RobotKeyword("Run Ammonite Scala Native Executable Fuzz Test")
  @ArgumentNames(Array())
  def runAmmoniteScalaNativeExecutableFuzzTest(): Unit = {
    s"/rfw/tools/ammonite-library/Ammonite --no-remote-logging /rfw/tools/ammonite-library/AmmoniteLibrary.scala scalaNativeFuzzTest"!!
  }

  @RobotKeyword("Run Scala Native C Library CPU Utilization Check")
  @ArgumentNames(Array())
  def runScalaNativeCLibraryCPUUtilizationCheck(): Unit = {
    s"rm -rf /rfw/tools/test-data-logs/scalanativecustomclibrary.txt".!
    s"touch /rfw/tools/test-data-logs/scalanativecustomclibrary.txt".!
    val cpuUtilization = Seq("/bin/bash", "-c",
    "/rfw/tools/scala-native-custom-c-library/target/scala-2.11/scala-native-custom-c-library-out cpucheck >> /rfw/tools/test-data-logs/scalanativecustomclibrary.txt").!!
  }

  @RobotKeyword("Run Scala Native C Library Memory Utilization Check")
  @ArgumentNames(Array())
  def runScalaNativeCLibraryMemoryUtilizationCheck(): Unit = {
    s"rm -rf /rfw/tools/test-data-logs/scalanativecustomclibrary.txt".!
    s"touch /rfw/tools/test-data-logs/scalanativecustomclibrary.txt".!
    val memoryUtilization = Seq("/bin/bash", "-c",
    "/rfw/tools/scala-native-custom-c-library/target/scala-2.11/scala-native-custom-c-library-out memorycheck >> /rfw/tools/test-data-logs/scalanativecustomclibrary.txt").!!
  }

  @RobotKeyword("Run Scala Native C Library Increased Memory Allocation Check")
  @ArgumentNames(Array())
  def runScalaNativeCLibraryIncreasedMemoryAllocationCheck(): Unit = {
    s"rm -rf /rfw/tools/test-data-logs/scalanativecustomclibrary.txt".!
    s"touch /rfw/tools/test-data-logs/scalanativecustomclibrary.txt".!
    val memoryUtilization = Seq("/bin/bash", "-c",
    "/rfw/tools/scala-native-custom-c-library/target/scala-2.11/scala-native-custom-c-library-out gradualincreasecheck >> /rfw/tools/test-data-logs/scalanativecustomclibrary.txt").!!
  }

}
