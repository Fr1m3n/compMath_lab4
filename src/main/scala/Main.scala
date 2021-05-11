import methods.{IntegrationMethod, SimpsonMethod}
import utils._

object Main extends App {

  @scala.annotation.tailrec
  def solveWithPrecisionCorrection(userInput: UserInput, method: IntegrationMethod, prevAnswer: Double): Result = {
    val answer = method.solve(userInput)
    if (Math.abs(answer - prevAnswer) > userInput.precision) {
      userInput.increasePrecision()
      solveWithPrecisionCorrection(userInput, method, answer)
    } else new Result(userInput, answer, method)
  }

  val userInput: UserInput = new UserInput(
    InputUtils.requestFunction(),
    InputUtils.requestIntegrationRange(),
    InputUtils.requestDouble("Введите точность: "),
//    InputUtils.requestPartition()
  )

  val method: IntegrationMethod = InputUtils.requestMethod()

  val firstAnswer = method.solve(userInput)
  userInput.increasePrecision()

  println(solveWithPrecisionCorrection(userInput, method, firstAnswer).toString)

}
