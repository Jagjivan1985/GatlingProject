package simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TC_5_TEST_ALLHTTPMETHOD extends Simulation {

  // HTTP Configuration
  val httpConfig = http.baseUrl("https://thetestingworldapi.com")
    .headers(Map("Accept" -> "application/json", "Connection" -> "keep-alive", "Content-Type" -> "application/json"))

  // Scenario Configuration

  // POST Scenario
  val postScenario = scenario("This is used to create a Student database using POST method")
    .exec(
      http("Create Student Details")
        .post("/api/studentsDetails")
        .body(RawFileBody("src/test/resources/TestData/CreateStudent.json")).asJson
        .check(status.is(201))
    )
    .pause(20)

  // PUT Scenario
  val putScenario = scenario("To Update an existing student record using PUT http method")
    .exec(
      http("Update Student Test Case")
        .put("/api/studentsDetails/10294950")
        .body(RawFileBody("src/test/resources/TestData/UpdateStudentDetails.json")).asJson
        .check(status.is(200))
    )
    .pause(400.seconds)

  // DELETE Scenario
  val deleteScenario = scenario("To delete the student record using DELETE http method")
    .exec(
      http("Delete the Student Record")
        .delete("/api/studentsDetails/10294950")
        .check(status.is(200))
    )
    .pause(1,10)

  // GET Scenario
  val getScenario = scenario("To retrieve the student record using GET http method")
    .exec(
      http("Get Student Details")
        .get("/api/studentsDetails/10294950")
        .check(status.is(200))
    )
    .pause(20)

  // Setup Configuration
  setUp(
    postScenario.inject(atOnceUsers(100)),
    putScenario.inject(atOnceUsers(100)),
    deleteScenario.inject(atOnceUsers(100)),
    getScenario.inject(atOnceUsers(100))
  ).protocols(httpConfig)
}
