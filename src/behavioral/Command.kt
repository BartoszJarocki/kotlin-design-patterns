package behavioral

interface DoorCommand {
    fun execute()
}

class OpenCommand(val doors: String) : DoorCommand {
    override fun execute() = println("Opened $doors")
}

class CloseCommand(val doors: String) : DoorCommand {
    override fun execute() = println("Closed $doors")
}

class HAL9000DoorsOperations(doors: String) {
    val openCommand: DoorCommand = OpenCommand(doors = doors)
    val closeCommand: DoorCommand = CloseCommand(doors = doors)

    fun close() = closeCommand.execute()

    fun open() = openCommand.execute()
}

fun main(args: Array<String>) {
    val podBayDoors = "Pod Bay Doors"
    val doorModule = HAL9000DoorsOperations(doors = podBayDoors)

    doorModule.open()
    doorModule.close()
}