import draw.Canvas
import newton.NewtonPolynomial
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle

import scala.io.StdIn._
import data.Function



object Main {

  private val functions = List(
    new Function("y = sin(x)", x => Math.sin(x)),
    new Function("y = x^3", x => Math.pow(x, 3)),
    new Function("y = x^6 + 7x^5 - 105x^2 + 21", x => Math.pow(x, 6) + 7 * Math.pow(x, 5) - 10 * Math.pow(x, 2) + 21)
  )

  def main(args: Array[String]): Unit = {
    println("Choose function: ")
    for (i <- functions.indices) println(s"${i + 1}. ${functions(i)}")
    val function = functions(readInt() - 1)

    print("Choose bounds: ")
    val bounds = readLine().split(" ").map(_.toDouble).toList
    val (a:Double, b:Double) = (bounds.head, bounds(1))

    val (x, y) = function.generateFunctionWithNoise((a, b), 20, 0.001)

    val values = x.indices.map(f => (x(f), y(f))).toList
    val table = Map[Double, Double](values:_*)
    val newton = new NewtonPolynomial(table)

    val N = newton.N(table.keys.toList)


    // Create Chart
    val chart = new XYChartBuilder().width(600).height(600).title("Newton polynomial method").xAxisTitle("X").yAxisTitle("Y").build()
    chart.getStyler.setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter)
    val canvas = new Canvas(chart)

    canvas.addFunction(function, (x.min, x.max), 0.1)
    canvas.addFunction(new Function("Newton", x => N(x)), (x.min, x.max), 0.07)
    canvas.addFunction(x, y, "Dots")

    canvas.draw()
  }
}

