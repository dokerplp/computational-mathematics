package dokerplp

class Equation (val name: String, val func: (Double, Double) => Double) {
  override def toString: String = name

  def apply(x: Double, y: Double): Double = func(x, y)
  
}
