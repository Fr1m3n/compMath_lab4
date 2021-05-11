package utils

import methods.IntegrationMethod

class Result(val userInput: UserInput, val answer: Double, val method: IntegrationMethod) {
  override def toString: String =
    s"""
      |Использован метод: ${method.name}
      |Разбиение: ${userInput.n}
      |Ответ: ${answer}
      |""".stripMargin
}
