package creational

object Singleton {
    init {
        println("Singleton init.")
    }

    fun print() = println("Printing with object: $this")
}

fun main(args: Array<String>) {
    println(Singleton.print())
    println(Singleton.print())
}