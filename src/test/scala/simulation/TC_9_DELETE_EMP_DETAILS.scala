package simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TC_9_DELETE_EMP_DETAILS extends Simulation {

  // HTTP Configuration
  val httpConfig = http.baseUrl("https://gorest.co.in")

    //To Simulate real user behaivour in Browser

    .header("Authorization", "Bearer a398925ea095d2e175e04fbb1109b2fe56d7341d52bb82d8ee33271f96f46a0b")
    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")

  // Scenario Configuration
  val scn = scenario("Delete the record")
    .exec(
      http("Delete the API using AUTH")
        .delete("/public/v2/users/7029283")
        .check(status.is(204)) // Check for successful deletion
        .check(status.in(200 to 299)) // Check for any successful status code
    )

  // Setup Configuration
  setUp(scn.inject(atOnceUsers(1))).protocols(httpConfig)
}
