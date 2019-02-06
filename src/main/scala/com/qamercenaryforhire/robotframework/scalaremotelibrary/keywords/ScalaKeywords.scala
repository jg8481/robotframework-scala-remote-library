package com.qamercenaryforhire.robotframework.scalaremotelibrary.keywords

import org.robotframework.javalib.annotation.ArgumentNames
import org.robotframework.javalib.annotation.RobotKeyword
import org.robotframework.javalib.annotation.RobotKeywords
import java.util._
import java.io.File
import java.io.InputStream
import scala.io._
import scala.util._
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
  //This keyword deals with completely randomized QuickCheck-style side effects for fuzz testing. Which is why I am using a jar file in a for-loop here instead of using ScalaCheck directly... for now. This could change.
  def runScalaCheckDataGenerator(args: Int): Unit = {
    if(args == 1){
      val path = "pwd".!!.trim
      //Reset the scalachecktestdata.txt test data file.
      s"touch $path/tools/test-data-logs/scalachecktestdata.txt".!
      s"rm $path/tools/test-data-logs/scalachecktestdata.txt".!
      s"touch $path/tools/test-data-logs/scalachecktestdata.txt".!
      //Then run the ScalaCheck test data generator.
      s"sleep 1".!
      var i = 0
      for {
        i <- 1 to 5
        val mediumFuzzTest = Seq("/bin/bash", "-c", "path=$(pwd) && java -jar $path/tools/create-your-own-scalacheck-fuzz-test-tool/target/scala-2.11/scalacheck-generic-test-data-generator.jar >> $path/tools/test-data-logs/scalachecktestdata.txt").!!
      }
      s"sleep 1".!
      val prepareTestData = Seq("/bin/bash", "-c", "path=$(pwd) && cp $path/tools/test-data-logs/scalachecktestdata.txt $path/tools/test-data-logs/temporarydata.txt && cat $path/tools/test-data-logs/temporarydata.txt | sed -e 's/Some//g' | sed -e 's/(//g' | sed -e 's/)//g' > $path/tools/test-data-logs/scalachecktestdata.txt").!!
      s"rm $path/tools/test-data-logs/temporarydata.txt".!
    }
    if(args == 2){
      val path = "pwd".!!.trim
      //Reset the scalachecktestdata.txt test data file.
      s"touch $path/tools/test-data-logs/scalachecktestdata.txt".!
      s"rm $path/tools/test-data-logs/scalachecktestdata.txt".!
      s"touch $path/tools/test-data-logs/scalachecktestdata.txt".!
      //Then run the ScalaCheck test data generator.
      s"sleep 1".!
      var i = 0
      for {
        i <- 1 to 20
        val heavyFuzzTest = Seq("/bin/bash", "-c", "path=$(pwd) && java -jar $path/tools/create-your-own-scalacheck-fuzz-test-tool/target/scala-2.11/scalacheck-generic-test-data-generator.jar >> $path/tools/test-data-logs/scalachecktestdata.txt").!!
      }
      s"sleep 1".!
      val prepareTestData = Seq("/bin/bash", "-c", "path=$(pwd) && cp $path/tools/test-data-logs/scalachecktestdata.txt $path/tools/test-data-logs/temporarydata.txt && cat $path/tools/test-data-logs/temporarydata.txt | sed -e 's/Some//g' | sed -e 's/(//g' | sed -e 's/)//g' > $path/tools/test-data-logs/scalachecktestdata.txt").!!
      s"rm $path/tools/test-data-logs/temporarydata.txt".!
    }
  }

  @RobotKeyword("Use Ammonite To Check File Contents")
  @ArgumentNames(Array())
  def useAmmoniteToCheckFileContents(args: String): Unit = {
    //Always use this --> "Ammonite --no-remote-logging"
    //val path = "pwd".!!.trim
    //val keywordDirectory = "src/main/scala/com/qamercenaryforhire/robotframework/scalaremotelibrary/keywords"
    val ammoniteRunner = Seq("/bin/bash", "-c", "path=$(pwd) &&  chmod +x $path/tools/ammonite-library/Ammonite && $path/tools/ammonite-library/Ammonite --no-remote-logging ./AmmoniteLibrary.sc 3 hi")!!
  }
}
