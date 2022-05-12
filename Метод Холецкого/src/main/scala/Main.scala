import scala.io.StdIn.readInt

object Main {

  val matrixs = List (
    new Matrix (
      List (
        List(18, 22, 54, 42),
        List(22, 70, 86, 62),
        List(54, 86, 174, 134),
        List(42, 62, 134, 106)
      ), 4
    ),
    new Matrix(
      List (
        List(25, 15, -5),
        List(15, 18, 0),
        List(-5, 0, 11)
      ), 3
    ),
    new Matrix(
      List (
        List (4, 12, -16),
        List (12, 37, -43),
        List (-16, -43, 98)
      ), 3
    )
  )

  def main(args: Array[String]): Unit = {
    val choleskyDecomposition = new CholeskyDecomposition

    for (i <- matrixs.indices) println(s"${matrixs(i)}\n")

    print("\nChoose matrix: ")
    val matrix = matrixs(readInt() - 1)

    val lower = choleskyDecomposition.toLower(matrix)
    println(new Matrix(lower, matrix.n))
  }
}