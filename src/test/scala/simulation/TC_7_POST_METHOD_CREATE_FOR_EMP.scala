package simulation

import io.gatling.http.Predef._
import io.gatling.core.Predef._

class TC_7_POST_METHOD_CREATE_FOR_EMP extends Simulation {

  // HTTP Configuration
  val httpconfig = http.baseUrl("https://gorest.co.in")
    .header("Accept", "application/json")
    .header("Authorization", "Bearer a398925ea095d2e175e04fbb1109b2fe56d7341d52bb82d8ee33271f96f46a0b")

  // Scenario Configuration
  val scn = scenario("To Test the POST Method")
    .exec(
      http("POST METHOD USED AUTH")
        .post("/public/v2/users/")
        .body(RawFileBody("src/test/resources/TestData/Create_Employee.json")).asJson
        .check(status.is(201))
    )

  // Setup Configuration
  setUp(scn.inject(atOnceUsers(100))).protocols(httpconfig)
}
