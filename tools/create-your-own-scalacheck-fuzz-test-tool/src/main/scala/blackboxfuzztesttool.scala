import org.scalacheck._
import org.scalacheck.Gen
import org.scalacheck.Arbitrary.arbitrary
//import org.scalacheck.{Arbitrary, Properties, Gen}

object textwriter {
  val palindromeRandomGen: Gen[String] = for {
  base <- arbitrary[String]
  middle <- Gen.option(arbitrary[Char])
  } yield base + middle.getOrElse("") + base.reverse

  def main(args: Array[String]): Unit = {
    print(palindromeRandomGen.sample)
  }
}
