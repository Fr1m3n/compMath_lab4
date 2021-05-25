package methods
import breeze.linalg.{DenseMatrix, DenseVector}
import utils.{FunctionObject, Result}

class LogarithmicallyApproximation extends ApproximationMethod {

  val methodName = "Логарифмическая зависимость"

  override def findApproximation(table: Array[(Double, Double)]): Option[Result] = {
    val SLNX = sum(table, (b) => Math.log(b._1))
    val SLNXX = sum(table, (b) => Math.pow(Math.log(b._1), 2))
    val SY = sum(table, _._2)
    val SYLNX = sum(table, (b) => Math.log(b._1) * b._2)
    val n: Double = table.length

    val solution = solve(DenseMatrix(
      (n, SLNX),
      (SLNX, SLNXX)
    ), DenseVector(SY, SYLNX))

    val a = solution(1)
    val b = solution(0)
    if (a.isNaN) None
    else Some(buildResult(table, (x) => a * Math.log(x) + b, extraInfo = Array(("a", a.toString), ("b", b.toString))))
  }
}
