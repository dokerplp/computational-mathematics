package dokerplp.calculations

import dokerplp.equations.Equation
import dokerplp.equations.EquationSystem
import dokerplp.exceptions.WrongBoundsException
import dokerplp.exceptions.WrongFunctionException
import kotlin.math.abs
import kotlin.math.max

private fun half(l: Double, r: Double): Double {
    return r - (r - l) / 2
}

fun bisectionMethod(equation: Equation, epsilon: Double, bounds: Pair<Double, Double>): Pair<Double, Double> {
    var l = bounds.first
    var r = bounds.second
    if (equation(l) * equation(r) > 0) throw WrongBoundsException("Bounds have the same sign")
    var x = half(l, r)
    while (abs(equation(x)) > epsilon) {
        if (equation(x) * equation(l) > 0) l = x
        else r = x
        x = half(l, r)
    }
    return Pair(x, abs(equation(x)))
}

fun correctBounds(equation: Equation, bounds: Pair<Double, Double>): Boolean {
    var i = bounds.first
    while (i < bounds.second) {
        if (abs(equation.derivative(i)) > 1) return false
        i += 0.0001
    }
    return true
}

fun simpleIterationMethod(equation: Equation, epsilon: Double, bounds: Pair<Double, Double>): Pair<Double, Double> {

    if (!correctBounds(equation, bounds)) throw WrongBoundsException("Function's derivative must be absolutely less than 1")

    val phi = {x: Double -> equation(x) + x}

    var x = bounds.first

    while (abs(equation(x)) > epsilon) x = phi(x)

    return Pair(x, abs(equation(x)))
}

fun simpleIterationMethod(equationSystem: EquationSystem, epsilon: Double): Pair<Array<Double>, Array<Double>> {

    val arr1 = Array(equationSystem.roots) {0.0}
    val arr2 = Array(equationSystem.roots) {0.0}
    val arr3 = Array(equationSystem.roots) {0.0}

    var diff = 0.0
    do {
        diff = 0.0
        for (i in 0 until  equationSystem.roots) arr2[i] = equationSystem(i, arr1)
        for (i in 0 until equationSystem.roots) diff = max(diff, abs(arr1[i] - arr2[i]))
        System.arraycopy(arr2, 0, arr1, 0, equationSystem.roots)
    } while (diff > epsilon)

    for (i in 0 until  equationSystem.roots) arr3[i] = arr2[i] - equationSystem(i, arr1)

    for (r in arr1) if (r == Double.NEGATIVE_INFINITY || r == Double.POSITIVE_INFINITY || r.isNaN()) throw WrongFunctionException("Function's derivative must be absolutely less than 1")

    return Pair(arr2, arr3)
}


