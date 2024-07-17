package simulation

import io.gatling.http.Predef._
import io.gatling.core.Predef._

class TC_3_PUT_Update_Stud_Detail extends Simulation{

  //HTTP Configuration

  val httpcong= http.baseUrl("https://thetestingworldapi.com")
                http.header(name="Content-Type", value="application/json" )
                http.header(name="Accept",value="application/json")

 //Scenerio Configuration

 val scn= scenario (scenarioName = "To Update an existing student record using PUT http method")
    .exec(http(requestName = "Update Student Test Case")
      .put(url="/api/studentsDetails/10294677")
      .body(RawFileBody(filePath = "src/test/resources/TestData/Update Student Details")).asJson
      .check(status.is(expected = 200))

      //This line of code is used for if the status code in between range-200-299
      .check(status.in(expected =200,299))

    )

  //Setup Configuration

  setUp (scn.inject(atOnceUsers(users = 1))).protocols(httpcong)

  }
