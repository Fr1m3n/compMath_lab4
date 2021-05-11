package utils

class UserInput(val functionObject: FunctionObject,
                val integrationRange: (Int, Int),
                val precision: Double,
                var n: Int = 4) {
  require(n % 2 == 0, "Параметр N должен быть чётным")
  require(integrationRange._2 > integrationRange._1, "Правый предел интегрирования должен быть больше левого!")

  var integrationRangeLength: Double = integrationRange._2 - integrationRange._1
  var h: Double = integrationRangeLength / n

  def increasePrecision() = {
    n *= 2
    integrationRangeLength = integrationRange._2 - integrationRange._1
    h = integrationRangeLength / n
  }

}
