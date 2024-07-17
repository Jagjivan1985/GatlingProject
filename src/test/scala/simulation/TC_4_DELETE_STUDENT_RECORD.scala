package simulation

import io.gatling.http.Predef._
import io.gatling.core.Predef._

class TC_4_DELETE_STUDENT_RECORD extends Simulation{

  //https Configuration

  val httpconfig =http.baseUrl("https://thetestingworldapi.com")


  //Scenerio Configuration
val scn = scenario(scenarioName="To delete the student record using DELETE http method")
.exec(
http("Delete the Student Record")
.delete(url="/api/studentsDetails/10295050")
.check (status.is(expected = 200))
  )

 //setup configutaion
setUp(scn.inject(atOnceUsers(users = 1))).protocols(httpconfig)





}
