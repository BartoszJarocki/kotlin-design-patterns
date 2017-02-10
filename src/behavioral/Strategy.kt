package behavioral

class Printer(val strategy: (String) -> String) {
    fun print(string: String): String = strategy(string)
}

val lowerCaseFormatter: (String) -> String = String::toLowerCase
val upperCaseFormatter: (String) -> String = String::toUpperCase

fun main(args: Array<String>) {
    val lower = Printer(strategy = lowerCaseFormatter)
    println(lower.print("O tempora, o mores!"))

    val upper = Printer(strategy = upperCaseFormatter)
    println(upper.print("O tempora, o mores!"))
}