interp.load.ivy(
  "com.lihaoyi" %
  s"ammonite-shell_${scala.util.Properties.versionNumberString}" %
  ammonite.Constants.version
)
@
import $ivy.`org.scalacheck::scalacheck:1.12.5`

val shellSession = ammonite.shell.ShellSession()
import shellSession._
import ammonite.shell._
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
