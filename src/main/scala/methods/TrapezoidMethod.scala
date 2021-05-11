package methods
import utils.UserInput

object TrapezoidMethod extends IntegrationMethod {

  def getSum(userInput: UserInput, accum: Double = 0, i: Int = 1): Double = {
    if (i > userInput.n - 1) accum
    else getSum(userInput, accum + getNextY(userInput, i), i + 1)
  }

  override def solve(userInput: UserInput): Double = userInput.h / 2 * (getNextY(userInput, 0) + getNextY(userInput, userInput.n) + 2 * getSum(userInput))

  override val name: String = "Метод трапеций"

}
