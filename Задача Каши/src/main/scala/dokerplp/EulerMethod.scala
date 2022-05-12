package dokerplp

import scala.annotation.tailrec

class EulerMethod {

  def apply(h: Double, n: Int, function: Equation, start: (Double, Double)): List[(Double, Double)] =
    calculate(0, n, h, function, List((start._1, start._2)))

    
  @tailrec
  private def calculate(acc: Int, n: Int, h: Double, function: Equation, points: List[(Double, Double)]): List[(Double, Double)] =
    acc match {
      case x if x >= n => points
      case _ => calculate(acc + 1, n, h, function, points :+ (points.last._1 + h, points.last._2 + h * function(points.last._1, points.last._2)))
    }
}
