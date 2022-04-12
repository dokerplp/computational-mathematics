package dokerplp.calculations

import dokerplp.equations.Equation
import dokerplp.exception.BreakOfTheFirstKind
import dokerplp.exception.BreakOfTheSecondType
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.round

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun sympsonMethod(equation: Equation, bounds: Pair<Double, Double>, epsilon: Double): Pair<Double, Double> {
    var even = 0.0
    var odd = 0.0

    var n = 0
    var i = (bounds.first + epsilon).round(-log10(epsilon).toInt() + 1)
    while (i < bounds.second) {
        val y = equation(i)
        if (y.isNaN() || y == Double.NEGATIVE_INFINITY || y == Double.POSITIVE_INFINITY)
            throw BreakOfTheFirstKind("Break of the first type in x = $i", i)

        if (n % 2 == 0) even += y
        else odd += y

        i = (i + epsilon).round(-log10(epsilon).toInt() + 1)
        n++
    }

    val int = epsilon / 3 * (equation(bounds.first) + 4 * odd + 2 * even + equation(bounds.second))

    val r = equation[bounds.first, bounds.second, epsilon] * (bounds.second - bounds.first).pow(5) / (180 * n.toDouble().pow(4))
    if (abs(r) > abs(int)) throw BreakOfTheSecondType("R value is bigger than integral. Maybe it diverges.")

    return Pair(int, r)
}