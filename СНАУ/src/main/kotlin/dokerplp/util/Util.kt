package dokerplp.util

fun getEpsilon(): Double {
    print("Enter epsilon value: ")
    var eps: Double? = readLine()!!.toDoubleOrNull()
    while (eps == null) {
        println("Incorrect epsilon value")
        print("Try again: ")
        eps = readLine()!!.toDoubleOrNull()
    }
    return eps
}

fun getBounds(): Pair<Double, Double> {
    print("Enter bounds (example: \"-1.32 2.34\"): ")
    var input = readLine()!!.split(" ")
    var pair = if (input.size == 2) Pair(input[0].toDoubleOrNull(), input[1].toDoubleOrNull()) else Pair(null, null)
    while (pair.first == null || pair.second == null) {
        println("Incorrect bounds value")
        print("Try again: ")
        input = readLine()!!.split(" ")
        pair = Pair(input[0].toDoubleOrNull(), input[1].toDoubleOrNull())
    }
    return Pair(pair.first!!, pair.second!!)
}