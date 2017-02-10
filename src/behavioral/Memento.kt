package behavioral

import java.util.ArrayList

class Memento(val savedState: String)

class Originator {
    private var state: String? = null

    fun set(state: String) {
        println("Originator: Setting state to $state")

        this.state = state
    }

    fun saveToMemento(): Memento {
        println("Originator: Saving to Memento.")

        return Memento(state!!)
    }

    fun restoreFromMemento(memento: Memento) {
        state = memento.savedState

        println("Originator: State after restoring from Memento:  ${state!!}")
    }
}

class Caretaker {
    private val savedStates = ArrayList<Memento>()

    fun addMemento(memento: Memento) {
        savedStates.add(memento)
    }

    fun getMemento(index: Int): Memento {
        return savedStates[index]
    }
}

fun main(args: Array<String>) {
    val caretaker = Caretaker()

    val originator = Originator()
    originator.set("State1")
    originator.set("State2")
    caretaker.addMemento(originator.saveToMemento())
    originator.set("State3")
    caretaker.addMemento(originator.saveToMemento())
    originator.set("State4")

    originator.restoreFromMemento(caretaker.getMemento(0))
}