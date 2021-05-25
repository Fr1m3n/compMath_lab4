package methods
import breeze.linalg.{DenseMatrix, DenseVector}
import utils.{FunctionObject, Result}

class PowerApproximation extends ApproximationMethod {

  val methodName = "Степенная зависимость"

  override def findApproximation(table: Array[(Double, Double)]): Option[Result] = {
    val SLNX = sum(table, (b) =>  Math.log(b._1))
    val SLNXX = sum(table, (b) => Math.pow(Math.log(b._1), 2))
    val SLNY = sum(table, (b) => Math.log(b._2))
    val SLNXY = sum(table, b => Math.log(b._2) * Math.log(b._1))
    val n: Double = table.length

    val solution = solve(DenseMatrix(
      (n, SLNX),
      (SLNX, SLNXX)
    ), DenseVector(SLNY, SLNXY))

    val a = solution(1)
    val b = solution(0)

    if (a.isNaN) None
    else Some(buildResult(table, (x) => a * Math.pow(x, b), extraInfo = Array(("a", a.toString), ("b", b.toString))))
  }
}
