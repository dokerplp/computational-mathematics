package dokerplp

import dokerplp.calculator.executeOrder66
import dokerplp.data.Matrix
import dokerplp.exceptions.MatrixIncorrectException
import dokerplp.input.ConsoleInput
import dokerplp.input.FileInput
import dokerplp.input.Input
import dokerplp.input.RandomInput
import dokerplp.util.CustomLogger
import lombok.extern.slf4j.Slf4j

fun isCorrectMode(str: String?): Boolean {
    return str.equals("1") || str.equals("2") || str.equals("3")
}

@Slf4j
class Main

fun main() {

    val logger = CustomLogger()

    println("Mode:\n1 - console input\n2 - file input\n3 - random input")

    val input: Input?
    var mode: String?
    while (true) {
        mode = readLine()
        if (isCorrectMode(mode)) break
        println("Please print \"1\" or \"2\" or \"3\"")
    }

    val matrix = Matrix(ArrayList(), ArrayList(), ArrayList(),0.00001, 20, ArrayList())
    input = when(mode) {
        "1" -> ConsoleInput()
        "2" -> FileInput()
        "3" -> RandomInput()
        else -> null
    }
    input!!.fillUp(matrix)

    matrix.printMatrix()

    try {
        executeOrder66(matrix)
    } catch (e: MatrixIncorrectException) {
        logger.log(e.message)
    }

}