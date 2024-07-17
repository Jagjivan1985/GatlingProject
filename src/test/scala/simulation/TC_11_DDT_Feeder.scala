package simulation

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._

class TC_11_DDT_Feeder extends Simulation {

  // HTTPS Configuration
  val httpconf = http.baseUrl("https://reqres.in/")
  val csvfeeder = csv("src/test/resources/TestData/EmpTestData.csv").circular

  // Scenario Configuration
  val scn = scenario("To verify the Emp_Id and Emp_Name")
    .feed(csvfeeder)
    .exec(
      repeat(12) { // This will repeat the requests, not the feed
        exec(
          http("TO GET A DETAILS OF SINGLE EMP")
            .get("/api/users/${empID}")
            .check(status.is(200))
            .check(jsonPath("$.data.first_name").is("${first_name}"))
        )
      }
    )

  // Setup Configuration
  //setUp(scn.inject(atOnceUsers(1))).protocols(httpconf)
  //Delay of 10 seconds
  //setUp(scn.inject(nothingFor(10),atOnceUsers(1))).protocols(httpconf)

  // Injects 10 users over a duration of 5 seconds
  setUp(scn.inject(rampUsers(100).during(10)).protocols(httpconf))


}
