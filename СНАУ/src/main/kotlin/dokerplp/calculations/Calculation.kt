package dokerplp.calculations

import dokerplp.equations.Equation
import dokerplp.equations.EquationSystem
import dokerplp.exceptions.WrongBoundsException
import kotlin.math.abs
import kotlin.math.max

private fun half(l: Double, r: Double): Double {
    return r - (r - l) / 2
}

fun bisectionMethod(equation: Equation, epsilon: Double, bounds: Pair<Double, Double>): Double {
    var l = bounds.first
    var r = bounds.second
    if (equation(l) * equation(r) > 0) throw WrongBoundsException("Bounds have the same sign")
    var x = half(l, r)
    while (abs(equation(x)) > epsilon) {
        if (equation(x) * equation(l) > 0) l = x
        else r = x
        x = half(l, r)
    }
    return x
}

fun correctBounds(equation: Equation, bounds: Pair<Double, Double>): Boolean {
    var i = bounds.first
    while (i < bounds.second) {
        if (abs(equation.derivative(i)) > 1) return false
        i += 0.0001
    }
    return true
}

fun simpleIterationMethod(equation: Equation, epsilon: Double, bounds: Pair<Double, Double>): Double {

    if (!correctBounds(equation, bounds)) throw WrongBoundsException("Function's derivative must be absolutely less than 1")

    val phi = {x: Double -> equation(x) + x}

    var x = bounds.first

    while (abs(equation(x)) > epsilon) x = phi(x)

    return x
}

fun maxRoot(arr1: Array<Double>, arr2: Array<Double>): Double {
    var maxVal = Double.MIN_VALUE
    arr1.indices.forEach { i -> maxVal = max(maxVal, abs(arr1[i] - arr2[i]))}
    return maxVal
}


fun simpleIterationMethod(equationSystem: EquationSystem, epsilon: Double): Array<Double> {

    val phi = Array(equationSystem.roots) {i -> {x: Array<Double> -> equationSystem(i, x) + x[i]}}

    val lastArr = Array(equationSystem.roots) {-1.0}
    val curArr = Array(equationSystem.roots) {1.0}
    while (maxRoot(lastArr, curArr) > epsilon) {
        for (i in lastArr.indices) {
            lastArr[i] = curArr[i]
            curArr[i] = phi[i](lastArr)
        }
    }

    for (r in curArr) if (r == Double.NEGATIVE_INFINITY || r == Double.POSITIVE_INFINITY || r.isNaN()) throw WrongBoundsException("Function's derivative must be absolutely less than 1")

    return curArr
}


