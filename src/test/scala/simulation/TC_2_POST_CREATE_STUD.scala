package simulation

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._

class TC_2_POST_CREATE_STUD extends Simulation {

  //http Configuration

  val httpconfig = http.baseUrl("https://thetestingworldapi.com")
    .headers(Map("Accept" -> "application/json", "connection" -> "keep-alive", "Content-Type" -> "Application/json"))
    http.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
    http.header("User_Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/126.0.6478.108 Mobile/15E148 Safari/604.1")
 
  //Scenerio Configuration

  val scn = scenario(scenarioName = "This is used to create a Student database using POST method")
    .exec(

      http(requestName = "Create Student Details")
        .post("/api/studentsDetails")
        .body(RawFileBody(filePath = "src/test/resources/TestData/CreateStudent.json")).asJson
        .check(status.is(expected = 201))
    )
    .exec(session => {
      val myresponseBody = session("BODY").as[String]
      //println(s"Response body received from the Server: \n$myresponseBody")
      println(s"Response body received from the Server: \n$myresponseBody")
      session
    }

   )

  //Setup Config

setUp(scn.inject(atOnceUsers(users = 1))).protocols(httpconfig)

}
