import $ivy.`io.gatling:gatling-http:3.1.2`
import $ivy.`io.gatling:gatling-app:3.1.2`
import $ivy.`io.gatling.highcharts:gatling-charts-highcharts:3.1.2`
import $ivy.`ch.qos.logback:logback-classic:1.2.3`


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Random

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("BasicSimulation")
    .exec(http("request_1")
      .get("/"))
    .pause(5)

  setUp( 
    scn.inject(atOnceUsers(1000))
  ).protocols(httpProtocol)
}