package methods
import utils.UserInput

object RectangleLeftMethod extends IntegrationMethod {

  @scala.annotation.tailrec
  def getSum(input: UserInput, accum: Double = 0, i: Int = 1): Double = {
    if (i > input.n) accum
    else getSum(input, accum + getNextY(input, i - 1), i + 1)
  }

  override def solve(userInput: UserInput): Double = userInput.h * getSum(userInput)

  override val name: String = "Метод прямоугольников (левая модификация)"

}
