package methods

import utils.UserInput

object RectangleMiddleMethod extends IntegrationMethod {

  @scala.annotation.tailrec
  def getSum(input: UserInput, accum: Double = 0, i: Double = 0.5): Double = {
    if (i > input.n) accum
    else getSum(input, accum + getNextY(input, i), i + 1)
  }

  override def solve(userInput: UserInput): Double = userInput.h * getSum(userInput)

  override val name: String = "Метод прямоугольников (метод средних)"

}
