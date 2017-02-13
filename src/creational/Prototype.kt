package creational


data class Person(val name: String?, val lastName: String?)

fun main(args: Array<String>) {
    val prototype = Person("Bartosz", "Jarocki")
    val differentPerson = prototype.copy(lastName = "Ikcoraj")
}