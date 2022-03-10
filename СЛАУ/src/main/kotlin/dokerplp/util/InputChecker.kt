package dokerplp.util

import dokerplp.data.Matrix
import dokerplp.exceptions.InputIncorrectException

fun checkLineInput(line: String, rows: Int): List<Double> {
    val symbols = line.trim().split("\\s+".toRegex())
    if (symbols.size != rows) throw InputIncorrectException("Wrong amount of numbers in row")

    val row = ArrayList<Double>()
    for (i in 0 until rows) {
        symbols[i].replace(",", ".").toDoubleOrNull()?.let {
            row.add(it)
        } ?: run {
            throw InputIncorrectException("In your input ${symbols[i]} isn't a number!")
        }
    }

    return row
}

fun checkEpsilonValue(epsilon: String) : Double {
    epsilon.replace(",", ".").toDoubleOrNull()?.let {
        if (it <= 0) throw InputIncorrectException("Epsilon value must be greater than 0")
        return it
    } ?: run {
        throw InputIncorrectException("Incorrect epsilon value")
    }
}

fun checkRowsValue(rows: String) : Int {
    rows.toIntOrNull()?.let {
        if (it <= 0) throw InputIncorrectException("Rows value must be greater than 0")
        return it
    } ?: run {
        throw InputIncorrectException("Incorrect rows value")
    }
}
