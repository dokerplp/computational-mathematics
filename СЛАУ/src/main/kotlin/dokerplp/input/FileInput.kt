package dokerplp.input

import dokerplp.data.Matrix
import dokerplp.exceptions.BaseException
import dokerplp.exceptions.FileIncorrectException
import dokerplp.util.CustomLogger
import dokerplp.util.checkEpsilonValue
import dokerplp.util.checkLineInput
import dokerplp.util.checkRowsValue

import java.io.File

class FileInput : Input() {

    private val logger = CustomLogger()

    private fun checkFile(path: String): File {
        File(path).let {
            if (!it.isFile) throw FileIncorrectException("This is not a file!")
            if (!it.canRead()) throw FileIncorrectException("You have no permission to read this file!")
            return it
        }
    }


    override fun fillUp(matrix: Matrix) {
        while (true) {
            try {
                print("Enter file path: ")
                checkFile(readLine()!!).let {
                    it.readLines() as ArrayList
                }.let {
                    matrix.epsilon = checkEpsilonValue(it[0])
                    matrix.rows = checkRowsValue(it[1])
                    if (it.size != matrix.rows + 2) throw FileIncorrectException("Incorrect amount of lines if file")
                    it.removeAt(0)
                    it.removeAt(0)
                    it
                }.run {
                    for (i in 0 until matrix.rows) {
                        checkLineInput(this[i], matrix.rows + 1).let {
                            matrix.coof.add(it[matrix.rows])
                            (it as ArrayList).removeAt(matrix.rows)
                            matrix.list.add(it)
                        }
                    }
                }
                break
            } catch (e: FileIncorrectException) {
                logger.log(e.message)
            }
        }
    }
}