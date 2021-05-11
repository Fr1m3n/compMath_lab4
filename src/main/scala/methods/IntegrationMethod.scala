package methods

import utils.UserInput

trait IntegrationMethod {

  val EPS = 0.000000001

  def getNextY(userInput: UserInput, i: Double): Double = {
    val x = userInput.integrationRange._1 + userInput.h * i
    if (x == 0) userInput.functionObject.f(x - EPS) + userInput.functionObject.f(x + EPS)
    else userInput.functionObject.f(x)
  }

  def solve(userInput: UserInput): Double

  def name: String

}
