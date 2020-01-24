import $ivy.`io.gatling:gatling-http:3.1.2`
import $ivy.`io.gatling:gatling-app:3.1.2`
import $ivy.`io.gatling.highcharts:gatling-charts-highcharts:3.1.2`
import $ivy.`ch.qos.logback:logback-classic:1.2.3`


import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import org.slf4j.{LoggerFactory, Logger}


val rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME).asInstanceOf[ch.qos.logback.classic.Logger]
rootLogger.setLevel(ch.qos.logback.classic.Level.ERROR)


class RobotFrameworkScalaGatlingScenario extends Simulation {

  def gatlingScenario =
    scenario("Simple load").during(30 seconds) {
      exec(
        http("get200")
          .get("/")
          .check(status.is(200)) )
        .pause(1000 milliseconds, 2000 milliseconds)
        .exec(
          http("get401")
            .get("/status/401")
            .check(status.is(401)) )
        .pause(2000 milliseconds, 4000 milliseconds)
    }

  def gatlingConfiguration = {
    http
      .baseUrl("https://httpbin.org/")
      .userAgentHeader("curl/7.58.0")
      .disableFollowRedirect
  }

  def gatlingAssertions = List(
    global.responseTime.mean.lt(90),
    global.successfulRequests.percent.gt(90)
  )


  setUp(gatlingScenario.inject(rampUsers(10) during (10 seconds)))
    .protocols(gatlingConfiguration)
    .assertions(gatlingAssertions)

}



val propertiesToBuild =
  new GatlingPropertiesBuilder()
    .simulationClass(classOf[RobotFrameworkScalaGatlingScenario].getName)
    .resultsDirectory("/tmp")
    //.noReports()
    .build

Gatling.fromMap(propertiesToBuild)