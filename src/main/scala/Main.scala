import utils.Result
import methods.{ExponentialApproximation, LinearApproximation, LogarithmicallyApproximation, PowerApproximation, SquareApproximation}
import utils._


object Main extends App {
  val methods = Array(
    new LinearApproximation,
    new ExponentialApproximation,
    new SquareApproximation,
    new LogarithmicallyApproximation,
    new PowerApproximation
  )

  val table = InputUtils.readFunctionTable()

  val array: Array[Result] = methods.flatMap(m => m.findApproximation(table)).sortBy(_.s)
  ChartWriter.drawChartForFunctions(array.map(_.functionObject), table)
  array.foreach((a) => println(a.toString))
  println(s"Лучшая функция - ${array(0).methodName}. 𝜹 = ${array(0).averageSqrMiss}")
}
