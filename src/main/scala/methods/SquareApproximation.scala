package methods
import breeze.linalg.{DenseMatrix, DenseVector}
import utils.{FunctionObject, Result}

class SquareApproximation extends ApproximationMethod {

  val methodName = "Квадратичная зависимость"

  override def findApproximation(table: Array[(Double, Double)]): Option[Result] = {
    val SX = getSN(table, 1)
    val SXX = getSN(table, 2)
    val SXXX = getSN(table, 3)
    val SXXXX = getSN(table, 4)
    val SY = sum(table, (b) => b._2)
    val SXY = sum(table, (b) => b._1 * b._2)
    val SXXY = sum(table, (b) => b._2 * b._1 * b._1)
    val n: Double = table.length

    val solution = solve(DenseMatrix(
      (n, SX, SXX),
      (SX, SXX, SXXX),
      (SXX, SXXX, SXXXX)
    ), DenseVector(SY, SXY, SXXY))
    val a = solution(2)
    val b = solution(1)
    val c = solution(0)
    if (a.isNaN) None
    else Some(buildResult(table, (x) => a * x * x + b * x + c, extraInfo = Array(
      ("a", a.toString),
      ("b", b.toString),
      ("c", c.toString)
    )))
  }
}
