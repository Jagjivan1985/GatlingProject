package simulation

import io.gatling.http.Predef._
import io.gatling.core.Predef._

class TC_6_GET_EMP_DETAILS_WITH_AUTH extends Simulation{

  //http configuration

   val httpconf =  http.baseUrl("https://gorest.co.in")
    .header("Authorization", "Bearer a398925ea095d2e175e04fbb1109b2fe56d7341d52bb82d8ee33271f96f46a0b")
    .header("Accept", "application/json")

  //Scenerio Configuration
val scn= scenario(scenarioName = "To Get all epmployee details with AUTH")
.exec(
    http(requestName = "Get List of all Employee")
      .get("/public/v2/users")
      .check(status.is(expected=200))
      //.check(status.not(expected = 400))
      //.check(status.not(expected = 400))
      .check(header("Content-Type").exists)
      .check(header("Connection").exists)
      .check(header("Server").exists)
      .check(substring(pattern = "id"))
      .check(substring(pattern = "name"))
      .check(substring(pattern = "email"))
      .check(substring(pattern = "gender"))
      .check(substring(pattern = "status"))
      .check(responseTimeInMillis.lte(1000))
      //.check(header(Content-Type).is(expected = "application/json;charset=utf-8"))
      .check(header("Connection").is(expected = "keep-alive"))
      



  )

//setup configuration

  setUp(scn.inject(atOnceUsers(1))).protocols(httpconf) // Fixed syntax here
 //setUp(scn.inject(atOnceUsers(users = 1))).protocls(httpconf)

}
