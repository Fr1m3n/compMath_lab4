package utils

import com.github.sh0hei.mascalade.tableflip.TableFlip
import methods.ApproximationMethod

class Result(val functionObject: FunctionObject,
             val table: Array[(Double, Double)],
             val methodName: String,
             val extraInfo: Array[(String, String)] = Array.empty,
             val coefficients: Map[String, Double] = Map.empty) {

  val s: Double = ApproximationMethod.sum(table, (b) => Math.pow(functionObject.f(b._1) - b._1, 2))

  val averageSqrMiss: Double = Math.sqrt(
    ApproximationMethod.sum(
      table,
      b => Math.pow(functionObject.f(b._1) - b._2, 2)
    ) / table.length.toDouble
  )

  override def toString: String = getInfo + getPrettyTable

  def getInfo: String = {
    var sb = new StringBuilder()
    sb.append(s"$methodName: \n")
      .append("Коэффициенты: \n")
      .append(s"S = $s\n")
        .append(s"𝜹 = ${averageSqrMiss}\n")
    coefficients.keys.map((k) => s"$k = ${coefficients(k)}\n").foreach((s) => sb.append(s))
    if (extraInfo.nonEmpty) {
      extraInfo.map((x) => s"${x._1} = ${x._2}\n").foreach((s) => sb.append(s))
    }
    sb.append('\n').toString()
  }

  def getPrettyTable: String = {
    val headers = Array("x", "y", "ф(x)", "ε")
    val values = table.map((xy) => Array(
      xy._1.toString,                             // x
      xy._2.toString,                             // y
      functionObject.f(xy._1).toString,           // ф(x)
      (functionObject.f(xy._1) - xy._2).toString  // ε
    ))
    TableFlip.of(headers, values)
  }


}
