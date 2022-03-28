package dokerplp.equations

class EquationSystem(val roots: Int, private val functions: List<Pair<String, (Array<Double>) -> Double>>) {

    operator fun get(i: Int): String {
        return functions[i].first
    }

    operator fun invoke(i: Int, x: Array<Double>): Double {
        return functions[i].second(x)
    }

    override fun toString(): String {
        var text = ""
        for (i in functions.indices) text += "${get(i)}\n"
        return text
    }
}