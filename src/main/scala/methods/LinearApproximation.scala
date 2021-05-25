package methods
import breeze.linalg.{DenseMatrix, DenseVector}
import utils.{FunctionObject, Result}

class LinearApproximation extends ApproximationMethod {

  val methodName = "Линейная зависимость"

  override def findApproximation(table: Array[(Double, Double)]): Option[Result] = {
    val n: Double = table.length
    val SX = sum(table, (b) => b._1)
    val SXX = sum(table, (b) => Math.pow(b._1, 2))
    val SY = sum(table, (b) => b._2)
    val SXY = sum(table, (b) => (b._1 * b._2))
    val delta = SXX * n - SX * SX

    if (delta == 0) None
    else {
      val solution = solve(DenseMatrix(
        (n, SX),
        (SX, SXX)
      ), DenseVector(SY, SXY))
      val a = solution(1)
      val b = solution(0)

      val averageX = SX / n
      val averageY = SY / n

      val r = sum(table, (b) => (b._1 - averageX) * (b._2 - averageY)) /
        Math.sqrt(sum(table, (b) => Math.pow(b._1 - averageX, 2)) *
          sum(table, (b) => Math.pow(b._2 - averageY, 2)))
      Some(buildResult(table, (x) => a * x + b, extraInfo = Array(("a", a.toString), ("b", b.toString), ("r", r.toString))))
    }
  }
}
