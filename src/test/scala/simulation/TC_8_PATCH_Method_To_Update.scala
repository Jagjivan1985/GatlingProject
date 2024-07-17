package simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TC_8_PATCH_Method_To_Update extends Simulation {

  // HTTP Configuration
  val httpconfig = http.baseUrl("https://gorest.co.in")
    .header("Accept", "application/json")
    .header("Authorization", "Bearer a398925ea095d2e175e04fbb1109b2fe56d7341d52bb82d8ee33271f96f46a0b")

  // Scenario Configuration
  val scn = scenario("To update the Existing Employee Record using PATCH HTTP Method")
    .exec(
      http("Using PATCH Method to update existing Employee details")
        .patch("/public/v2/users/7029218")
        .body(RawFileBody("src/test/resources/TestData/Update_Employee_Patch")).asJson
        .check(status.in(200 to 299)) // Changed to 200 for successful update
        .check(jsonPath("$.id").ofType[Int])
        .check(jsonPath("$.first_name").ofType[String])
        .check(jsonPath("$[0].name").is("Yash Raj"))
        .check(jsonPath("$[0].gender").is("male"))


    )

  // Setup Configuration
  setUp(scn.inject(atOnceUsers(1))).protocols(httpconfig)
}
