package simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TC_12_RAMPUP_USER extends Simulation {

//http configuration

val httpConfig = http.baseUrl("https://thetestingworldapi.com")
  .headers(Map("Accept" -> "application/json", "Connection" -> "keep-alive"))

//Scenerio configuration

val scn = scenario("This API is Used to retrieve all student details from the database")
  .exec(
    http("GET ALL STUDENT DETAILS")
      .get("/api/StudentsDetails")
      //.check(bodyLength.is(expected = 10786))
      //.check(jsonPath("$[0]").ofType[Int])
      //.check(jsonPath("$[0].first_name").is("Salman"))
      //.check(jsonPath("$[0].gender").is("male"))
      //.check(jsonPath("$[0].status").is("active"))

  )

//Setup  Configuration

//setUp(scn.inject(atOnceUsers(users=100))).protocols(httpConfig)

  //Delay of 10 seconds
  //setUp(scn.inject(nothingFor(10),atOnceUsers(1))).protocols(httpconf)

  // Injects 10 users over a duration of 5 seconds
  //setUp(scn.inject(rampUsers(100).during(10)).protocols(httpConfig))

  //setUp(scn.inject(rampUsersPerSec(rate1=10).to(rate2=20).during(3000)).protocols(httpConfig))

  // This command will inject 20 users per second continuously for a duration of 15 seconds.
setUp(scn.inject(constantUsersPerSec(20).during(15))).protocols(httpConfig)


}
