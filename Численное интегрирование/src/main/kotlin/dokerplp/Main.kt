import dokerplp.calculations.sympsonMethod
import dokerplp.equations.Equation
import dokerplp.exception.BreakOfTheFirstKind
import dokerplp.util.getBounds
import dokerplp.util.getEpsilon
import java.lang.Math.cos
import java.lang.Math.sin
import kotlin.math.pow

val functions = listOf (
    Equation("x^4", {x -> x.pow(4)}, {24.0}),
    Equation("sin(x) / x", {x -> sin(x) / x}, {x -> 4 * cos(x) / x.pow(2) - 12 * sin(x) / x.pow(3) + 24 * sin(x) / x.pow(5) - 24 * cos(x) / x.pow(4) + sin(x) / x }),
    Equation("1 / x", {x -> 1 / x}, {x -> 24 * x.pow(-5)})
)

fun resolve(equation: Equation, epsilon: Double, bounds: Pair<Double, Double>) {
    println()
    try {
        val ans = sympsonMethod(equation, bounds, epsilon)
        println("Answer: ${ans.first}")
    } catch (e: BreakOfTheFirstKind) {
        println("Function $equation has break of the first type in point x = ${e.x}")
        println("Now f(${e.x}) = (f(${e.x} - $epsilon) + f(${e.x} + $epsilon)) / 2")
        equation.specialPoint(e.x, (equation(e.x - epsilon) + equation(e.x + epsilon)) / 2)
        resolve(equation, epsilon, bounds)
    }
}

fun main(args: Array<String>) {
    println("Equations: ")
    for (i in functions.indices) println("${i + 1}. ${functions[i]}")

    print("\nChoose equation: ")
    var equationIndex = readLine()!!.toInt()
    while (equationIndex !in 1..functions.size) {
        print("Please enter number from 1 to ${functions.size}: ")
        equationIndex = readLine()!!.toInt()
    }

    val equation = functions[equationIndex - 1]
    val epsilon = getEpsilon()
    val bounds = getBounds()

    resolve(equation, epsilon, bounds)
}