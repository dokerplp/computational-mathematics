package dokerplp.data

import kotlin.math.abs

data class Matrix(var list: ArrayList<ArrayList<Double>>, var coof: ArrayList<Double>, var x: ArrayList<Double>, var epsilon: Double, var rows: Int, var diffArr: ArrayList<Double>) {

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    private fun ArrayList<Double>.getDiv(): Double {
        var div = 0.0
        for (el in this) {
            if (el != 0.0) {
                div = el
                break
            }
        }
        return div
    }

    private fun ArrayList<Double>.hash(): Int {

        val div = this.getDiv()
        if (div == 0.0) return 0

        val p = 1000000007
        var deg = p
        var hash = 0
        for (i in 0 until this.size) {
            hash += ((this[i] / div).hashCode() * deg) % Int.MAX_VALUE
            deg = deg * deg % Int.MAX_VALUE
        }
        return hash
    }

    private fun ArrayList<Double>.lineEquals(other: ArrayList<Double>): Boolean {
        if (this.hash() != other.hash() || this.size != other.size) return false
        val thisDiv = this.getDiv()
        val otherDiv = other.getDiv()
        if (thisDiv == 0.0 && otherDiv == 0.0) return true
        else if (thisDiv == 0.0 || otherDiv == 0.0) return false
        for (i in 0 until this.size) if (this[i] / thisDiv != other[i] / otherDiv) return false
        return true
    }

    private fun getColumn(index: Int): ArrayList<Double> {
        val arr = ArrayList<Double>()
        for (i in 0 until this.list.size) arr.add(this.list[i][index])
        return arr
    }

    fun lineEquals(i: Int, j: Int): Boolean {
        return this.list[i].lineEquals(this.list[j])
    }

    fun columnEquals(i: Int, j: Int): Boolean {
        return getColumn(i).lineEquals(getColumn(j))
    }

    fun isZeroDevLine(i: Int): Boolean {
        return this.list[i].getDiv() == 0.0
    }

    fun isZeroDevColumn(i: Int): Boolean {
        return getColumn(i).getDiv() == 0.0
    }

    fun getRowSum(i: Int): Double {
        var sum = 0.0
        list[i].forEach {sum += abs(it)}
        return sum
    }

    fun printMatrix() {
        for (i in 0 until this.rows) {
            for (l2 in this.list[i]) {
                print("${l2.format(3)} ")
                if (l2 < 10) print(" ")
            }
            print("   ${this.coof[i].format(3)}")
            println()
        }
        println()
    }

    fun printX() {
        println("Answer:")
        for (i in 0 until this.rows) println("x${i + 1} = ${this.x[i].format(5)} | err = ${diffArr[i]}")
    }
}

