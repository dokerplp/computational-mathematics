package dokerplp.input

import dokerplp.data.Matrix
import java.lang.Math.*


class RandomInput : Input() {

    private fun randomValue(from: Int, to: Int): Double {
        return random() * (to - from) + from
    }

    private fun generateStroke(rows: Int): ArrayList<Double> {
        val stroke = ArrayList<Double>()
        for(i in 0 until rows) stroke.add(randomValue(0, 99))
        return stroke
    }

    override fun fillUp(matrix: Matrix) {
        setupEpsilonValue(matrix)
        setupRowsValue(matrix)
        for (i in 0 until matrix.rows) {
            matrix.list.add(generateStroke(matrix.rows))
            matrix.coof.add(randomValue(0, 99))
        }
    }
}