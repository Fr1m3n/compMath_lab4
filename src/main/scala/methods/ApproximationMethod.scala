package methods

import breeze.linalg.{DenseMatrix, DenseVector}
import utils.{FunctionObject, Result}

trait ApproximationMethod {

  def methodName: String

  def findApproximation(table: Array[(Double, Double)]): Option[Result]

  def buildResult(table: Array[(Double, Double)],
                  f: (Double) => Double,
                  extraColumns: Array[(String, String)]=Array.empty,
                  extraInfo: Array[(String, String)]=Array.empty): Result = {
    new Result(
      new FunctionObject(f, methodName),
      table,
      methodName,
      extraInfo=extraInfo
    )
  }

  def solve(matrix: DenseMatrix[Double], vector: DenseVector[Double]): DenseVector[Double] = {
    try {
      val inv = breeze.linalg.inv(matrix)
      inv * vector
    } catch {
      case _: breeze.linalg.MatrixSingularException =>
        println(s"Вырожденная матрица при вычислении вектора коэффициентов для ${methodName}")
        DenseVector.fill(3)(Double.NaN)

      case _: Throwable =>
        println("Что-то пошло вообще не по плану при вычислении вектора коэффициентов")
        DenseVector.fill(3)(Double.NaN)
    }
  }

  def sum: (Array[(Double, Double)], ((Double, Double)) => Double) => Double = ApproximationMethod.sum
  def getSN: (Array[(Double, Double)], Double) => Double = ApproximationMethod.getSN

}

object ApproximationMethod {

  def sum(table: Array[(Double, Double)], f: ((Double, Double)) => Double): Double = {
    table.foldLeft(0.0)((a, b) => a + f(b))
  }

  def getSN(table: Array[(Double, Double)], n: Double): Double = {
    sum(table, (b) => Math.pow(b._1, n))
  }


}
