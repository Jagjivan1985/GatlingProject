package simulation

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TC_1_GET_ALL_STUDENT_DETAILS extends Simulation {

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
        .check(jsonPath("$[0].status").is("active"))




    )

  //Load Configuration
setUp(scn.inject(atOnceUsers(users=100))).protocols(httpConfig)

}
