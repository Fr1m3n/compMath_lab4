package utils

import methods.{IntegrationMethod, RectangleLeftMethod, RectangleMiddleMethod, RectangleRightMethod, SimpsonMethod, TrapezoidMethod}

import scala.math.pow
import scala.io.StdIn._

object InputUtils {

  type FunctionType = (Double) => Double

  val functions: Array[FunctionObject] = Array(
    new FunctionObject((x: Double) => 2 * pow(x, 3) - 3 * pow(x, 2) + 5 * x - 9, "2 * x^3 - 3 * x^2 + 5 * x - 9"),
    new FunctionObject((x: Double) => Math.sin(x) / x, "sin(x) / x"),
    new FunctionObject((x: Double) => 1 / x, "1 / x")
  )

  val methods: Array[IntegrationMethod] = Array(
    SimpsonMethod,
    RectangleLeftMethod,
    RectangleRightMethod,
    RectangleMiddleMethod,
    TrapezoidMethod
  )

  def printAllFunction() = {
    println("Выберите функцию:")
    functions.indices.foreach((i) => {
      val f: FunctionObject = functions(i)
      println(s"${i + 1}: ${f.str}")
    })
  }

  def printAllMethods(): Unit = {
    println("Выберите метод:")
    methods.indices.foreach((i) => {
      val method: IntegrationMethod = methods(i)
      println(s"${i + 1}: ${method.name}")
    })
  }

  def requestInt(prompt: String = ""): Int = {
    print(prompt)
    readInt()
  }

  def requestDouble(prompt: String = ""): Double = {
    print(prompt)
    readDouble()
  }

  def requestFunction(): FunctionObject = {
    printAllFunction()
    functions(requestInt("Введите номер желаемой функции: ") - 1)
  }

  def requestMethod(): IntegrationMethod = {
    printAllMethods()
    methods(requestInt("Введите номер выбранного метода: ") - 1)
  }

  def requestIntegrationRange(): (Int, Int) = {
    (requestInt("Введите левый предел интегрирования: "), requestInt("Введите правый предел интегрирования: "))
  }

  def requestPartition(): (Int) = {
    print("Введите число разбиения (для использования значения 4 по умолчанию нажмите Enter): ")
    val partition = readLine()
    try {
      if (partition == "") 4
      else partition.toInt
    } catch {
      case e: NumberFormatException =>
        println("Было введено не число. Используем значение по умолчанию.")
        4
    }
  }

}
