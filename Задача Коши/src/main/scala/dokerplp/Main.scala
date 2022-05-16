package dokerplp

import data.Function
import draw.Canvas

import scala.math.BigDecimal.double2bigDecimal
import scala.io.StdIn.{readInt, readLine}
import newton.NewtonPolynomial
import org.knowm.xchart.{SwingWrapper, XYChart, XYChartBuilder}
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle
import org.knowm.xchart.style.lines.SeriesLines
import org.knowm.xchart.style.markers.Marker
import util.Converter

import java.awt.Color

object Main {

  private val functions = List[Equation](
    new Equation("y' = y*x", (x, y) => x * y),
    new Equation("y' = x^2 - 2y", (x, y) => x*x - 2*y),
    new Equation("y' = sin(x) + cos(y)", (x, y) => Math.sin(x) + Math.cos(y)),
    new Equation("y' = sqrt(xy)", (x, y) => Math.sqrt(x*y)),
  )


  def main(args: Array[String]): Unit = {
    println("Choose function: ")
    for (i <- functions.indices) println(s"${i + 1}. ${functions(i)}")
    val function = functions(readInt() - 1)

    print("Start point: ")
    val bounds = readLine().split(" ").map(_.toDouble).toList
    val start = (bounds.head, bounds(1))

    println("Steps: ")
    val steps = readInt()

    val eulerMethod = new EulerMethod

    val list = eulerMethod(0.1, steps, function, start)

    val rawX = Converter.scalaListToJavaList(list.map(_._1))
    val rawY = Converter.scalaListToJavaList(list.map(_._2))

    val newton = new NewtonPolynomial(list.toMap)
    val N = newton.N(list.map(_._1))

    val drawPoints = (list.head._1 to list.last._1 by 0.001).map(_.toDouble).toList

    val x = Converter.scalaListToJavaList(drawPoints)
    val y = Converter.scalaListToJavaList(drawPoints.map(N(_)))

    // Create Chart
    val chart = new XYChartBuilder().width(600).height(600).title("Lab 5").xAxisTitle("X").yAxisTitle("Y").build()
    chart.getStyler.setMarkerSize(5)

    val func = chart.addSeries("Newton method", x, y)
    func.setLineStyle(SeriesLines.DASH_DASH)
    func.setMarkerColor(Color.PINK)

    val points = chart.addSeries("Euler method", rawX, rawY)
    points.setMarkerColor(Color.BLACK)



    new SwingWrapper(chart).displayChart()

  }
}
