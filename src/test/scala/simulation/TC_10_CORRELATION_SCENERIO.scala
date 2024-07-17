package simulation

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TC_10_CORRELATION_SCENARIO extends Simulation {

  // HTTP Configuration
  val httpConfig = http.baseUrl("https://thetestingworldapi.com")
    .headers(Map("Accept" -> "application/json", "Connection" -> "keep-alive"))

  // Scenario Configuration
  val scn = scenario("This API is Used to retrieve all student details from the database")
    .exec(
      http("GET ALL STUDENT DETAILS")
        .get("/api/StudentsDetails")
        .check(jsonPath("$[0].id").saveAs("extractedID"))
    )
    .pause(10) // Pause for 10 seconds

    .exec { session =>
      val extractedID = session("extractedID").asOption[String]
      println(s"Extracted ID: $extractedID")
      session
    }
    .exec(
      http("Delete the Student Record")
        .delete("/api/studentsDetails/${extractedID}")
        //.check(status.is(200))
    )

  // Load Configuration
  setUp(scn.inject(atOnceUsers(100))).protocols(httpConfig)
}
