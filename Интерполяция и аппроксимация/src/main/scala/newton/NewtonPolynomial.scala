package newton

class NewtonPolynomial(private val y: Map[Double, Double]) {

  def f(list: List[Double]): Double = {
    list match {
      case List(_) => y(list.head)
      case List(a, b) => (y(b) - y(a)) / (b - a)
      case _ => (f(list.slice(1, list.size)) - f(list.slice(0, list.size - 1))) / (list.last - list.head)
    }
  }

  def g(list: List[Double]): Double => Double = {
    list match {
      case List(_) => _ => 1
      case _ => (x: Double) => (x - list.head) * g(list.slice(1, list.size))(x)
    }
  }

  def s(list: List[Double]): Double => Double = {
    x => f(list) * g(list)(x)
  }

  def N(list: List[Double]): Double => Double = {
    list.reverse match {
      case List(_) => x => s(list)(x)
      case _ :: tail => x => s(list)(x) + N(tail.reverse)(x)
      case _ => _ => 0
    }
  }
}
