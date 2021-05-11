package methods

import utils.UserInput

import scala.annotation.tailrec

object SimpsonMethod extends IntegrationMethod {

  @tailrec
  def getSumForEven(inp: UserInput, accum: Double = 0, i: Int = 2): Double = {
    if (i > inp.n - 2) accum
    else getSumForEven(inp, accum + getNextY(inp, i), i + 2)
  }

  @tailrec
  def getSumForOdd(inp: UserInput, accum: Double = 0, i: Int = 1): Double = {
    if (i > inp.n - 1) accum
    else getSumForOdd(inp, accum + getNextY(inp, i), i + 2)
  }

  override def solve(inp: UserInput): Double = {
    inp.h / 3 * (getNextY(inp, 0) + 4 * getSumForOdd(inp) + 2 * getSumForEven(inp) + getNextY(inp, inp.n))
  }

  override val name: String = "Метод Симпсона"

}
