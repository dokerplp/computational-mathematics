package data

import scala.math.BigDecimal.double2bigDecimal
import scala.util.Random

class Function(private val label: String, private val function: Double => Double) {

  override def toString: String = label

  def getFunction: Double => Double = function

  def generateFunction(bounds: (Double, Double), step: Double): (List[Double], List[Double]) = {
    val x = (bounds._1 to bounds._2 by step).map(f => f.doubleValue).toList
    val y = x.map(f => function(f))
    (x, y)
  }

  def generateFunctionWithNoise(bounds: (Double, Double), amount: Int, noise: Double): (List[Double], List[Double]) = {
    val x = List.fill(amount)(Random.nextDouble * (bounds._2 - bounds._1) + bounds._1)
    val y = x.map(f => function(f) + Random.nextDouble * noise)
    (x, y)
  }
}
