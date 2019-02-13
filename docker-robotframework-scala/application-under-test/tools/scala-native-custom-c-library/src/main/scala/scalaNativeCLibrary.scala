import scalanative.native._
import scopt.OptionParser

case class Config(count: Int = 1, objects: List[String] = List(), mode: String = "information")

object Main extends App {
  val parser = new scopt.OptionParser[Config]("c-library") {
    head("scala-native-custom-c-library", "1.0")
    help("help").text("Usage text...")
    cmd("cpucheck").action((_, c) => c.copy(mode = "cpucheck"))
      .text("Custom C library code that measures CPU Utilization.")
    cmd("memorycheck").action((_, c) => c.copy(mode = "memorycheck"))
      .text("Custom C library code that measures Physical Memory Utilization.")
    cmd("gradualincreasecheck").action((_, c) => c.copy(mode = "gradualincreasecheck"))
      .text("Custom C library code that gradually increases Memory Allocation then measures Physical Memory Utilization.")
}
  parser.parse(args, Config()) match {
    case Some(config) =>
      config.mode match {
        case "cpucheck" =>
          cpucheck.cpuutilization()
        case "memorycheck" =>
          memorycheck.memoryutilization()
        case "gradualincreasecheck" =>
          gradualincreasecheck.increaseusage()
        case "information" =>
          println("")
          println("------------------------------------[[[[ scala-native-custom-c-library ]]]]------------------------------------")
          println("")
          println("A simple Scala Native application created for this project -> https://github.com/jg8481/robotframework-scala-remote-library")
          println("")
          println("For more information about Scala Native, go here -> https://scala-native.readthedocs.io/en/v0.3.8/user/interop.html#interop")
          println("")
          println("")
      }
    case None =>
  }
}


@extern
@link("cpucheck")
object cpucheck {
  def cpuutilization(): Unit = extern
}

@extern
@link("memorycheck")
object memorycheck {
  def memoryutilization(): Unit = extern
}

@extern
@link("gradualincreasecheck")
object gradualincreasecheck {
  def increaseusage(): Unit = extern
}

//@extern
//object libc {
//  def malloc(size: CSize): Ptr[Byte] = extern
//  def free(ptr: Ptr[Byte]): Unit = extern
//}
