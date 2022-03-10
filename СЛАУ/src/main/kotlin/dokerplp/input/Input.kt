package dokerplp.input

import dokerplp.data.Matrix
import dokerplp.exceptions.BaseException
import dokerplp.util.CustomLogger
import dokerplp.util.checkEpsilonValue
import dokerplp.util.checkLineInput
import dokerplp.util.checkRowsValue

abstract class Input {

    private val logger: CustomLogger = CustomLogger()

    abstract fun fillUp(matrix: Matrix)

    fun setupEpsilonValue(matrix: Matrix) {
        while (true) {
            print("Enter epsilon value: ")
            try {
                matrix.epsilon = checkEpsilonValue(readLine()!!)
                break
            } catch (e: BaseException) {
                logger.log(e.message)
            }
        }
    }

    fun setupRowsValue(matrix: Matrix) {
        while (true) {
            print("Enter row value: ")
            try {
                matrix.rows = checkRowsValue(readLine()!!)
                break
            } catch (e: BaseException) {
                logger.log(e.message)
            }
        }
    }
}