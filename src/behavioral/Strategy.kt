package behavioral

interface PrintStrategy {
    fun print(string: String): String
}

class Printer(val strategy: PrintStrategy) {
    fun print(string: String): String {
        return strategy.print(string)
    }
}

class UpperCaseStrategy : PrintStrategy {
    override fun print(string: String): String {
        return string.toUpperCase()
    }
}

class LowerCaseStrategy : PrintStrategy {
    override fun print(string: String): String {
        return string.toLowerCase()
    }
}

fun main(args: Array<String>) {
    var lower = Printer(strategy = LowerCaseStrategy())
    println(lower.print("O tempora, o mores!"))

    var upper = Printer(strategy = UpperCaseStrategy())
    println(upper.print("O tempora, o mores!"))
}