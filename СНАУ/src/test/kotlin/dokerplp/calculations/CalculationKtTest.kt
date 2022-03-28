package dokerplp.calculations

import dokerplp.equations.Equation
import dokerplp.equations.EquationSystem
import dokerplp.exceptions.WrongBoundsException
import dokerplp.exceptions.WrongFunctionException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.*
import kotlin.test.assertFailsWith

internal class CalculationKtTest {

    private val equationsList = listOf(
        Equation("x^3 - x + 4 = 0", { x -> x.pow(3.0) - x + 4.0 }, { x -> 3.0 * x.pow(2.0) - 1.0 }),
        Equation(
            "ln|x| - 0.05*e^x = 0",
            { x -> ln(abs(x)) - 0.05 * Math.E.pow(x) },
            { x -> 1.0 / x - Math.E.pow(x) / 20.0 }),
        Equation(
            "2x^3 / (x * (x^2-4)) + 5 = 0",
            { x -> 2.0 * x.pow(3.0) / (x * (x.pow(2.0) - 4.0)) + 5.0 },
            { x -> (-16.0 * x) / (x.pow(2.0) - 4.0).pow(2.0) }),
        Equation(
            "2*x^(5/3) - 5*x^(2/3) + 1 = 0",
            { x -> 2.0 * x.pow(5.0 / 3.0) - 5.0 * x.pow(2.0 / 3.0) + 1.0 },
            { x -> (10.0 * x - 10.0) / (3.0 * x.pow(1.0 / 3.0)) }),
        Equation(
            "ln(2-cos(3x)) - 0.5 = 0",
            { x -> ln(2.0 - cos(3.0 * x)) - 0.5 },
            { x -> (3.0 * sin(3.0 * x)) / (2.0 - cos(3.0 * x)) })
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
//        EquationSystem(
//            2,
//            listOf(
//                Pair("x1 + 3*lg(x1) - x2^2 = 0", { (x1, x2) -> x1 + 3 * log10(x1) - x2.pow(2) }),
//                Pair("2*x1^2 - x1*x2 - 5*x1 + 1 = 0", { (x1, x2) -> 2 * x1.pow(2) - x1 * x2 - 5 * x1 + 1 })
//            )
//        )
    )

    @Test
    fun bisectionMethodTest() {
        println("Equation 1: ${equationsList[0]}")
        println("Root in (-2, 2) interval: ${bisectionMethod(equationsList[0], 0.001, Pair(-2.0, 2.0))}")
        println("\n")
        println("Equation 2: ${equationsList[1]}")
        println("Root in (-1.1, -1) interval: ${bisectionMethod(equationsList[1], 0.001, Pair(-1.1, -1.0))}")
        println("Root in (1, 1.5) interval: ${bisectionMethod(equationsList[1], 0.001, Pair(1.0, 1.5))}")
        println("Root in (3, 3.2) interval: ${bisectionMethod(equationsList[1], 0.001, Pair(3.0, 3.2))}")
        println("\n")
        println("Equation 3: ${equationsList[2]}")
        println("Root in (-1.7, -1.69) interval: ${bisectionMethod(equationsList[2], 0.001, Pair(-1.7, -1.69))}")
        println("Root in (1.69, 1.7) interval: ${bisectionMethod(equationsList[2], 0.001, Pair(1.69, 1.7))}")
        println("\n")
        println("Equation 4: ${equationsList[3]}")
        println("Root in (-0.1, -0.08) interval: ${bisectionMethod(equationsList[3], 0.001, Pair(-0.1, -0.08))}")
        println("Root in (0.08, 0.1) interval: ${bisectionMethod(equationsList[3], 0.001, Pair(0.08, 0.1))}")
        println("Root in (2.2, 2.21) interval: ${bisectionMethod(equationsList[3], 0.001, Pair(2.2, 2.21))}")
        println("Equation 5: ${equationsList[4]}")
        println("Root in (0.3, 0.5) interval: ${bisectionMethod(equationsList[4], 0.001, Pair(0.3, 0.5))}")
    }

    @Test
    fun simpleIterationMethodTest() {
        println("Equation 1: ${equationsList[0]}")

        assertFailsWith<WrongBoundsException> {
            println("Root in (-2, 2) interval: ${simpleIterationMethod(equationsList[0], 0.001, Pair(-2.0, 2.0))}")
        }.also { println("Wrong derivative") }

        println("\n")
        println("Equation 2: ${equationsList[1]}")

        assertFailsWith<WrongBoundsException> {
            println("Root in (-1.1, -1) interval: ${simpleIterationMethod(equationsList[1], 0.001, Pair(-1.1, -1.0))}")
        }.also { println("Wrong derivative") }

        println("Root in (1, 1.5) interval: ${simpleIterationMethod(equationsList[1], 0.001, Pair(1.0, 1.5))}")
        println("Root in (3, 3.2) interval: ${simpleIterationMethod(equationsList[1], 0.001, Pair(3.0, 3.2))}")
        println("\n")
        println("Equation 3: ${equationsList[2]}")

        assertFailsWith<WrongBoundsException> {
            println(
                "Root in (-1.7, -1.69) interval: ${
                    simpleIterationMethod(
                        equationsList[2],
                        0.001,
                        Pair(-1.7, -1.69)
                    )
                }"
            )
        }.also { println("Wrong derivative") }

        assertFailsWith<WrongBoundsException> {
            println("Root in (1.69, 1.7) interval: ${simpleIterationMethod(equationsList[2], 0.001, Pair(1.69, 1.7))}")
        }.also { println("Wrong derivative") }

        println("\n")
        println("Equation 4: ${equationsList[3]}")
        println("Root in (-0.1, -0.08) interval: ${simpleIterationMethod(equationsList[3], 0.001, Pair(-0.1, -0.08))}")

        assertFailsWith<WrongBoundsException> {
            println("Root in (0.08, 0.1) interval: ${simpleIterationMethod(equationsList[3], 0.001, Pair(0.08, 0.1))}")
        }.also { println("Wrong derivative") }

        assertFailsWith<WrongBoundsException> {
            println("Root in (2.2, 2.21) interval: ${simpleIterationMethod(equationsList[3], 0.001, Pair(2.2, 2.21))}")
        }.also { println("Wrong derivative") }

        println("Equation 5: ${equationsList[4]}")

        assertFailsWith<WrongBoundsException> {
            println(
                "Root in (0.399, 0.401) interval: ${
                    simpleIterationMethod(
                        equationsList[4],
                        0.001,
                        Pair(0.399, 0.401)
                    )
                }"
            )
        }.also { println("Wrong derivative") }
    }

    @Test
    fun simpleIterationMethodForSystemTest() {

        println("Equation system 1:\n${equationSystemsList[0]}")


        val r1 = simpleIterationMethod(equationSystemsList[0], 0.001)
        println("Answer: ")
        (0 until equationSystemsList[0].roots).forEach { i -> println("x${i + 1} = ${r1.first[i]} (discrepancy: ${r1.second[i]})") }

        println("Equation system 2:\n${equationSystemsList[1]}")
        val r2 = simpleIterationMethod(equationSystemsList[1], 0.001)
        println("Answer: ")
        (0 until equationSystemsList[1].roots).forEach { i -> println("x${i + 1} = ${r2.first[i]} (discrepancy: ${r2.second[i]})") }

//        assertFailsWith<WrongFunctionException> {
//            println("Equation system 3:\n${equationSystemsList[2]}")
//            val r3 = simpleIterationMethod(equationSystemsList[2], 0.001)
//            println("Answer: ")
//            (0 until equationSystemsList[2].roots).forEach { i -> println("x${i + 1} = ${r3.first[i]} (discrepancy: ${r3.second[i]})") }
//        }.also { println("Wrong function") }
    }

    @Test
    fun test() {
        println("Testing Bisection Method...")
        bisectionMethodTest()
        println("\n")
        println("Testing Simple Iteration Method...")
        simpleIterationMethodTest()
        println("\n")
        println("Testing Simple Iteration Method For System...")
        simpleIterationMethodForSystemTest()
    }
}