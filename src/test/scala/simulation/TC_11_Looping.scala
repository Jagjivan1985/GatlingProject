package simulation

import io.gatling.http.Predef._
import io.gatling.core.Predef._

class TC_11_Looping extends Simulation {

  // HTTP Configuration
  val httpConf = http.baseUrl("https://thetestingworldapi.com")
    .header("Accept", "application/json")

  // Scenario Configuration
  val scn = scenario("Executing the API in Loop")
    .repeat(3) {
      exec(
        http("GET A REQUEST DATA")
          .get("/api/studentsDetails/10294680")
          .check(status.is(200))
          .check(bodyString.saveAs("responsebody"))
      )
        .pause(10)
    }
    .exec(session => {
      val myResponseBody = session("responsebody").as[String]
      println(s"Student Record are =>>>>>>>> \n$myResponseBody")
      session
    })

    .repeat(2) {
      exec(
        http("GET ALL STUDENT DETAILS")
          .get("/api/studentsDetails")
          .check(status.is(200))
      )
        .pause(3)
    }

  // Setup Configuration
  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)
}
