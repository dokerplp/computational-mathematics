package dokerplp.equations

class Equation(private val text: String, private val function: (Double) -> Double) {

    var derivative: (Double) -> Double = function

    constructor(text: String, function: (Double) -> Double, derivative: (Double) -> Double) : this(text, function) {
        this.derivative = derivative
    }

    operator fun invoke(x: Double): Double {
        return function(x)
    }

    override fun toString(): String {
        return text
    }

}