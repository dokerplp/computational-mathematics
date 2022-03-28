package dokerplp

import dokerplp.calculations.bisectionMethod
import dokerplp.calculations.simpleIterationMethod
import dokerplp.equations.Equation
import dokerplp.equations.EquationSystem
import dokerplp.util.getBounds
import dokerplp.util.getEpsilon
import kotlin.math.*

private val equationsList = listOf (
    Equation("x^3 - x + 4 = 0", { x -> x.pow(3.0) - x + 4.0 }, {x -> 3.0 * x.pow(2.0) - 1.0}),
    Equation("ln|x| - 0.05*e^x = 0", { x -> ln(abs(x)) - 0.05 * Math.E.pow(x) }, { x -> 1.0 / x - Math.E.pow(x) / 20.0}),
    Equation("2x^3 / (x * (x^2-4)) + 5 = 0", { x -> 2.0 * x.pow(3.0) / (x * (x.pow(2.0) - 4.0)) + 5.0 }, {x -> (-16.0 * x) / (x.pow(2.0) - 4.0).pow(2.0)}),
    Equation("2*x^(5/3) - 5*x^(2/3) + 1 = 0", { x -> 2.0 * x.pow(5.0 / 3.0) - 5.0 * x.pow(2.0 / 3.0) + 1.0 }, {x -> (10.0 * x - 10.0) / (3.0 * x.pow(1.0 / 3.0))}),
    Equation("ln(2-cos(3x)) - 0.5 = 0", { x -> ln(2.0 - cos(3.0 * x)) - 0.5 }, { x -> (3.0 * sin(3.0 * x)) / (2.0 - cos(3.0 * x))})
)

private val equationSystemsList = listOf(
    EquationSystem(
        3,
        listOf(
            Pair("0.16 * x2 - 0.08 * x3 + 1.2 - x1 = 0", { (x1, x2, x3) -> 0.16 * x2 - 0.08 * x3 + 1.2 }),
            Pair("0.2 * x1 - 0.424 * x3 - 1.786 - x2 = 0", { (x1, x2, x3) -> 0.2 * x1 - 0.424 * x3 - 1.786 }),
            Pair("-0.1389 * x1 - 0.58889 * x2 - 0.0667 - x3 = 0", { (x1, x2, x3) -> -0.1389 * x1 - 0.58889 * x2 - 0.0667 }),
        )
    ),
    EquationSystem(
        2,
        listOf(
            Pair("sin(x1 + 1)- 1.2 - x1 = 0", { (x1, x2) -> sin(x1 + 1) - 1.2 }),
            Pair("1 - cos(x2) / 2 - x2 = 0", { (x1, x2) -> 1 - cos(x2) / 2 })
        )
    ),
//    EquationSystem(
//        2,
//        listOf(
//            Pair("x1 + 3*lg(x1) - x2^2 = 0", { (x1, x2) -> x1 + 3 * log10(x1) - x2.pow(2) }),
//            Pair("2*x1^2 - x1*x2 - 5*x1 + 1 = 0", { (x1, x2) -> 2 * x1.pow(2) - x1 * x2 - 5 * x1 + 1 })
//        )
//    )
)

fun equation() {
    println("Equations: ")
    for (i in equationsList.indices) println("${i + 1}. ${equationsList[i]}")

    print("\nChoose equation: ")
    var equationIndex = readLine()!!.toInt()
    while (equationIndex !in 1..equationsList.size) {
        print("Please enter number from 1 to ${equationsList.size}: ")
        equationIndex = readLine()!!.toInt()
    }

    val equation = equationsList[equationIndex - 1]
    val epsilon = getEpsilon()
    val bounds = getBounds()

    val m1 = bisectionMethod(equation, epsilon, bounds)
    val m2 = simpleIterationMethod(equation, epsilon, bounds)

    println("\nBisection method: ${m1.first}, (discrepancy: ${m1.second})")
    println("Simple iteration method: ${m2.first}, (discrepancy: ${m1.second})\"")
    println("Difference: ${abs(m1.first - m2.first)}")
}

fun equationSystem() {
    println("Equation systems: ")
    for (i in equationSystemsList.indices) println("${i + 1}.\n${equationSystemsList[i]}")

    print("\nChoose equation system: ")
    var equationIndex = readLine()!!.toInt()
    while (equationIndex !in 1..equationSystemsList.size) {
        print("Please enter number from 1 to ${equationSystemsList.size}: ")
        equationIndex = readLine()!!.toInt()
    }

    val equationSystem = equationSystemsList[equationIndex - 1]
    val epsilon = getEpsilon()

    val roots = simpleIterationMethod(equationSystem, epsilon)

    println("Answer: ")
    (0 until  equationSystem.roots).forEach { i -> println("x${i + 1} = ${roots.first[i]} (discrepancy: ${roots.second[i]})") }

}

fun main(args: Array<String>) {
    equation()
    println("\n")
    equationSystem()
}