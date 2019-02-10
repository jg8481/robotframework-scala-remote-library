import $ivy.`org.scalacheck::scalacheck:1.12.5`
import sys.process._
import scala.sys.process.Process
import collection.mutable
import scala.collection.immutable.Map
import ammonite.ops._
import ammonite.ops.ImplicitWd._
import scalaj.http._
import scala.util._
import org.scalacheck._
import org.scalacheck.Gen
import org.scalacheck.Arbitrary.arbitrary


@doc("usage: ./Ammonite --no-remote-logging ./AmmoniteLibrary.scala getRequest")
@main
def getRequest(): Unit = {
  val getResponse = Http("http://jsonplaceholder.typicode.com/posts").asString.body
  println(getResponse)
  println("Get Request Script completed.")
}

@doc("usage: ./Ammonite --no-remote-logging ./AmmoniteLibrary.scala postRequest qa test")
@main
def postRequest(title: String, body: String): Unit = {
  val postResponse = ujson.read(
  Http("http://jsonplaceholder.typicode.com/posts")
    .postForm(Seq("title"  -> title,
                  "body"   -> body,
                  "userId" -> "1"))
    .asString
    .body
  )
  println(postResponse)
  println("Post Request Script completed.")
}


@doc("usage: ./Ammonite --no-remote-logging ./AmmoniteLibrary.scala patchFuzzTest ... FYI - cURL must be installed first and full path to cURL binary is required.")
@main
def patchFuzzTest(): Unit = {
  val curlCommand = %('bash, "-c",
  "PATH=$(pwd) && FUZZ_TEST_DATA=$(/bin/cat $PATH/tools/test-data-logs/scalachecktestdata.txt) && /usr/bin/curl -i -X PATCH -H \'Content-Type: application/json\' http://$BASE_URL/posts/1 -d \'{\"title\": \'\"$FUZZ_TEST_DATA\"\'}\'",
  BASE_URL="jsonplaceholder.typicode.com")
}


@doc("usage: ./Ammonite --no-remote-logging ./AmmoniteLibrary.scala scalaCheckRunner")
@main
def scalaCheckRunner(): Unit = {
  val palindromeRandomGen: Gen[String] = for {
  base <- arbitrary[String]
  middle <- Gen.option(arbitrary[Char])
  } yield base + middle.getOrElse("") + base.reverse
  print(palindromeRandomGen.sample)
}


@doc("usage: ./Ammonite --no-remote-logging ./AmmoniteLibrary.scala scalaNativeLettersTest")
@main
def scalaNativeLettersTest(): Unit = {
  val lettersTest = %('bash, "-c",
  "$SCALA_NATIVE_EXECUTABLE_PATH/simple-scala-native-example-application-out Hello",
  SCALA_NATIVE_EXECUTABLE_PATH="/rfw/simple-scala-native-example-application/target/scala-2.11")
}

@doc("usage: ./Ammonite --no-remote-logging ./AmmoniteLibrary.scala scalaNativeNumbersTest")
@main
def scalaNativeNumbersTest(): Unit = {
  val numbersTest = %('bash, "-c",
  "$SCALA_NATIVE_EXECUTABLE_PATH/simple-scala-native-example-application-out 12345",
  SCALA_NATIVE_EXECUTABLE_PATH="/rfw/simple-scala-native-example-application/target/scala-2.11")
}

@doc("usage: ./Ammonite --no-remote-logging ./AmmoniteLibrary.scala scalaNativeFuzzTest")
@main
def scalaNativeFuzzTest(): Unit = {
  s"touch /rfw/tools/test-data-logs/fuzztestdata.txt".!
  val executableFuzzTest = %('bash, "-c",
  "SCALA_CHECK_DATA=$(/bin/cat /rfw/tools/test-data-logs/scalachecktestdata.txt) && echo $SCALA_CHECK_DATA >> /rfw/tools/test-data-logs/fuzztestdata.txt && FUZZ_TEST_DATA=$(/bin/cat /rfw/tools/test-data-logs/fuzztestdata.txt) && $SCALA_NATIVE_EXECUTABLE_PATH/simple-scala-native-example-application-out $FUZZ_TEST_DATA",
  SCALA_NATIVE_EXECUTABLE_PATH="/rfw/simple-scala-native-example-application/target/scala-2.11")
}
