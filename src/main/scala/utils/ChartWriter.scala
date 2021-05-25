package utils

import breeze.linalg._
import breeze.plot._
import com.github.sh0hei.mascalade.tableflip.TableFlip

object ChartWriter extends App {

    final val MARGIN = 1;

    def drawPoints(figure: Figure, table: Array[(Double, Double)]): Unit = {
        val p = figure.subplot(0)
        val x = DenseVector(table.map(_._1))
        val y = DenseVector(table.map(_._2))
        p += plot(x, y, '.', name="input")

    }

    def drawChartForFunction(figure: Figure, function: FunctionObject, table: Array[(Double, Double)]): Unit = {
        val p = figure.subplot(0)
        val xLin = linspace(table.map(_._1).min - MARGIN, table.map(_._1).max + MARGIN)
        p += plot(xLin, xLin.map(xi => function.f(xi)), name = function.str)
        p.xlabel = "x axis"
        p.ylabel = "y axis"
//        figure.saveas("plot.png")
    }


    def drawChartForFunctions(functions: Array[FunctionObject], table: Array[(Double, Double)]) = {
        val figure = Figure()
        val p = figure.subplot(0)
        p.legend = true
        drawPoints(figure, table)
        functions.foreach((f) => drawChartForFunction(figure, f, table))
//        figure.saveas("plot.png")
    }


}
