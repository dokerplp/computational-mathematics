package dokerplp.input

import dokerplp.data.Matrix
import dokerplp.exceptions.BaseException
import dokerplp.util.CustomLogger
import dokerplp.util.checkLineInput
import org.slf4j.LoggerFactory

class ConsoleInput : Input() {

    private val logger = CustomLogger()

    private fun setupMatrix(matrix: Matrix) {
        var counter = 0
        while (counter < matrix.rows) {
            try {
                checkLineInput(readLine()!!, matrix.rows + 1).let {
                    matrix.coof.add(it[matrix.rows])
                    (it as ArrayList).removeAt(matrix.rows)
                    matrix.list.add(it)
                }
                counter++
            } catch (e: BaseException) {
                logger.log(e.message)
            }
        }
    }

    override fun fillUp(matrix: Matrix) {
        setupEpsilonValue(matrix)
        setupRowsValue(matrix)
        setupMatrix(matrix)
    }
}