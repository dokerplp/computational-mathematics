package dokerplp.calculator

import dokerplp.data.Matrix
import dokerplp.exceptions.MatrixIncorrectException
import kotlin.math.abs

class MatrixCalculations

fun executeOrder66(matrix: Matrix) {
    if (isDegenerateMatrix(matrix)) throw MatrixIncorrectException("Determinant of this matrix equals 0")
    diagonal(matrix)
    matrix.printMatrix()
    normalize(matrix)
    findX(matrix)
    matrix.printX()
}

fun isDegenerateMatrix(matrix: Matrix): Boolean {
    for (i in 0 until matrix.list.size) {
        for (j in 0 until matrix.list.size) {
            if (i == j) continue
            if (
                matrix.isZeroDevLine(i) ||
                matrix.isZeroDevLine(j) ||
                matrix.isZeroDevColumn(i) ||
                matrix.isZeroDevColumn(j) ||
                matrix.lineEquals(i, j) ||
                matrix.columnEquals(i, j)
            ) return true
        }
    }
    return false
}

fun diagonal(matrix: Matrix) {
    val correct = ArrayList<Int>()
    val lines = ArrayList<Boolean>()
    val rows = ArrayList<Boolean>()
    (0 until matrix.list.size).forEach { _ -> lines.add(false); rows.add(false); correct.add(0) }
    for (i in 0 until matrix.list.size) {
        val sum = matrix.getRowSum(i)
        for (j in 0 until matrix.list.size) {
            if (2 * abs(matrix.list[i][j]) > sum) {
                lines[i] = true
                rows[j] = true
                correct[j] = i
                break
            }
        }
    }
    lines.forEach { if(!it) throw MatrixIncorrectException("Can't normalize this matrix") }
    rows.forEach{ if(!it) throw MatrixIncorrectException("Can't normalize this matrix")}

    val newMatrix = ArrayList<ArrayList<Double>>()
    val newCoof = ArrayList<Double>()
    correct.forEach{newMatrix.add(matrix.list[it]); newCoof.add(matrix.coof[it])}
    matrix.list = newMatrix
    matrix.coof = newCoof
}

fun normalize(matrix: Matrix) {
    val c = ArrayList<ArrayList<Double>>()
    val d = ArrayList<Double>()
    (0 until matrix.list.size).forEach { _ -> c.add(ArrayList()) }
    for (i in 0 until matrix.list.size) {
        for (j in 0 until  matrix.list.size) {
            if (i == j) c[i].add(0.0)
            else c[i].add( -(matrix.list[i][j] / matrix.list[i][i]) )
        }
        d.add(matrix.coof[i] / matrix.list[i][i])
    }
    matrix.list = c
    matrix.coof = d
}

fun newX(matrix: Matrix, i: Int): Double {
    var res = 0.0
    (0 until matrix.list.size).forEach { res += matrix.list[i][it] * matrix.x[it] }
    res += matrix.coof[i]
    return res
}

fun findX(matrix: Matrix) {
    matrix.coof.forEach{matrix.x.add(it)}
    var diff = Double.MAX_VALUE
    val diffArr = ArrayList<Double>()
    (0 until matrix.list.size).forEach { _ -> diffArr.add(0.0) }
    while (diff > matrix.epsilon) {
        diff = 0.0
        for (i in 0 until matrix.list.size) {
            newX(matrix, i).let {
                diffArr[i] = abs(it - matrix.x[i])
                if (diffArr[i] > diff) diff = abs(it - matrix.x[i])
                matrix.x[i] = it
            }
        }
    }
    matrix.diffArr = diffArr
}