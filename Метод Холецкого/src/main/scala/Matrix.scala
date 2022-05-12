import scala.collection.mutable._

class Matrix(val values: List[List[Double]], val n: Int) {
  override def toString = values.map(l => l.mkString(" ")).mkString("\n")
}
