package dokerplp.equations

import kotlin.math.abs
import kotlin.math.max

class Equation(private val text: String, private var function: (Double) -> Double, private val derivative4: (Double) -> Double) {

    fun specialPoint(x: Double, value: Double) {
        val f = function
        val g: (Double) -> Double = {t -> if (t == x) value else f(t)}
        function = g
    }

    operator fun invoke(x: Double): Double {
        return function(x)
    }

    operator fun get(a: Double, b: Double, h: Double): Double {
        var m = Double.MIN_VALUE
        var i = a
        while (i < b) {
            m = max(m, abs(derivative4(i)))
            i += h
        }
        return m
    }

    override fun toString(): String {
        return text
    }

}