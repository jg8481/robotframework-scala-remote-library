import scalanative.native._
import scala.io.Source
import java.io._

object textwriter {
  def main(args: Array[String]): Unit = {
    messagewriter.messageInTextFile(c"message.txt",c"||    This part of the message was created with Scala Native using C...    ||")
    val fw = new FileWriter("message.txt", true) ;
    fw.write("||    This part of the message was created with robotframework-scala-remote-library Scala keywords...    || ----> " + args.mkString(",")) ;
    fw.flush
    fw.close()
  }
}

@extern
@link("messagewriter")
object messagewriter {
  def messageInTextFile(fName: CString,fContent: CString) : Unit = extern
}
