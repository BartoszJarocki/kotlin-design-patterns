package behavioral

import java.util.*

class Programmer(val name: String) {
    fun receive(message: String) = println("$name received: $message")
}

interface MessageSending {
    fun send(message: String)
}

class MessageMediator(val recipients: MutableList<Programmer> = ArrayList()) : MessageSending {
    fun add(recipient: Programmer) {
        recipients.add(recipient)
    }

    override fun send(message: String) {
        for (recipient in recipients) {
            recipient.receive(message = message)
        }
    }
}

fun spamMonster(message: String, worker: MessageSending) {
    worker.send(message = message)
}

fun main(args: Array<String>) {
    val messagesMediator = MessageMediator()

    val user0 = Programmer(name = "Linus Torvalds")
    val user1 = Programmer(name = "Avadis 'Avie' Tevanian")
    messagesMediator.add(recipient = user0)
    messagesMediator.add(recipient = user1)

    spamMonster(message = "I'd Like to Add you to My Professional Network", worker = messagesMediator)
}