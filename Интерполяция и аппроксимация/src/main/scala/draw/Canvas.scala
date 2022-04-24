package draw

import org.knowm.xchart.{SwingWrapper, XYChart}
import util.Converter
import data.Function

class Canvas(private val chart: XYChart) {

  def addFunction(x: List[Double], y: List[Double], label: String): Unit = {
    val d1 = Converter.scalaListToJavaListDouble(x)
    val d2 = Converter.scalaListToJavaListDouble(y)
    chart.addSeries(label, d1, d2)
  }

  def addFunction(function: Function, bounds: (Double, Double), step: Double): Unit = {
    val (x: List[Double], y: List[Double]) = function.generateFunction(bounds, step)
    addFunction(x, y, function.toString)
  }

  def draw(): Unit =
    new SwingWrapper[XYChart](chart).displayChart()
}
