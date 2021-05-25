package utils

import scala.io.StdIn._
import scala.io.Source._

object InputUtils {

  def readFunctionTable(): Array[(Double, Double)] = {
    print("Введите имя файла (нажмите Enter для ввода из консоли): ")
    val fileName: String = readLine()
    if (fileName.isEmpty) readTableFromConsole()
    else readTableFromFile(fileName)
  }

  def readTableFromFile(fileName: String): Array[(Double, Double)] = {
    val source = fromFile(fileName)
    val lines = source.getLines

    val res = lines.map(s => {
      val splitted = s.split(' ')
      (splitted(0).toDouble, splitted(1).toDouble)
    }).toArray
    source.close()
    res
  }

  def readTableFromConsole(): Array[(Double, Double)] = {
    val count = requestInt("Введите кол-во точек: ")
    (0 until count).map((_) => {
      val s = readLine()
      val splitted = s.split(' ')
      (splitted(0).toDouble, splitted(1).toDouble)
    }).toArray
  }

  def requestInt(prompt: String = ""): Int = {
    print(prompt)
    readInt()
  }

  def requestDouble(prompt: String = ""): Double = {
    print(prompt)
    readDouble()
  }


}
