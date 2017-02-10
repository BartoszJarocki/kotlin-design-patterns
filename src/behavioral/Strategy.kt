package behavioral

class Printer(val strategy: (String) -> String) {
    fun print(string: String): String = strategy(string)
}

val lowerCaseFormatter: (String) -> String = String::toLowerCase
val upperCaseFormatter: (String) -> String = String::toUpperCase

val addOne = fun(number: Int): Int {
    return 1 + number
}

fun test(value: Int, function: (Int) -> Int) {
    println(function(value))
}

fun main(args: Array<String>) {
    val lower = Printer(strategy = lowerCaseFormatter)
    println(lower.print("O tempora, o mores!"))

    val upper = Printer(strategy = upperCaseFormatter)
    println(upper.print("O tempora, o mores!"))

    test(1, addOne)
    test(1, { 1 + it })

    test(2) {
        1 + it
    }
}