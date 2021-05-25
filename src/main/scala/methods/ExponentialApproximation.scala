package methods
import breeze.linalg.{DenseMatrix, DenseVector}
import utils.{FunctionObject, Result}

class ExponentialApproximation extends ApproximationMethod {

  val methodName = "Экспоненциальная зависимость"

  override def findApproximation(table: Array[(Double, Double)]): Option[Result] = {
    val n: Double = table.length
    val SX = getSN(table, 1)
    val SXX = getSN(table, 2)
    val SLNY = sum(table, (b) => Math.log(b._2) )
    val SXLNY = sum(table, (b) => b._1 * Math.log(b._2))

    val solution = solve(DenseMatrix((n, SX), (SX, SXX)), DenseVector(SLNY, SXLNY))
    val a = solution(1)
    val b = solution(0)
    if (a.isNaN) None
    else Some(buildResult(table, (x) => Math.exp(a * x + b), extraInfo = Array(("a", a.toString), ("b", b.toString))))

  }

}
