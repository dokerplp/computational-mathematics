import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.collection.mutable.ListBuffer

class CholeskyDecomposition() {

  def toLower(matrix: Matrix): List[List[Double]] = {

    val lower: ListBuffer[ListBuffer[Double]] = (0 until matrix.n).map(_ => (0 until matrix.n).map(_ => 0.0).to(ListBuffer)).to(ListBuffer)

    for (i <- 0 until matrix.n) {
      for (j <- 0 to i) {
        val sum: Double = (0 until j).map(k => lower(i)(k) * lower(j)(k)).sum
        if (j == i) lower(j)(j) = Math.sqrt(matrix.values(j)(j) - sum)
        else lower(i)(j) = (matrix.values(i)(j) - sum) / lower(j)(j)
      }
    }

    lower.map(f => f.toList).toList
  }

}
