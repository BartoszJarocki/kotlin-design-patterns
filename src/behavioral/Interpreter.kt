package behavioral


interface IntegerExpression {
    fun evaluate(context: IntegerContext): Int
    fun replace(character: Char, integerExpression: IntegerExpression): IntegerExpression
    fun copied(): IntegerExpression
}

class IntegerContext(var data: MutableMap<Char, Int> = mutableMapOf()) {
    fun lookup(name: Char): Int {
        return data[name]!!
    }

    fun assign(expression: IntegerVariableExpression, value: Int) {
        data[expression.name] = value
    }
}

class IntegerVariableExpression(val name: Char) : IntegerExpression {
    override fun evaluate(context: IntegerContext): Int {
        return context.lookup(name = name)
    }

    override fun replace(name: Char, integerExpression: IntegerExpression): IntegerExpression {
        if (name == this.name) {
            return integerExpression.copied()
        } else {
            return IntegerVariableExpression(name = this.name)
        }
    }

    override fun copied(): IntegerExpression {
        return IntegerVariableExpression(name = this.name)
    }
}

class AddExpression(var operand1: IntegerExpression, var operand2: IntegerExpression) : IntegerExpression {
    override fun evaluate(context: IntegerContext): Int {
        return this.operand1.evaluate(context) + this.operand2.evaluate(context)
    }

    override fun replace(character: Char, integerExpression: IntegerExpression): IntegerExpression {
        return AddExpression(operand1 = operand1.replace(character = character, integerExpression = integerExpression),
                operand2 = operand2.replace(character = character, integerExpression = integerExpression))
    }

    override fun copied(): IntegerExpression {
        return AddExpression(operand1 = this.operand1, operand2 = this.operand2)
    }
}

fun main(args: Array<String>) {
    val context = IntegerContext()

    val a = IntegerVariableExpression(name = 'A')
    val b = IntegerVariableExpression(name = 'B')
    val c = IntegerVariableExpression(name = 'C')

    val expression = AddExpression(operand1 = a, operand2 = AddExpression(operand1 = b, operand2 = c)) // a + (b + c)
    context.assign(expression = a, value = 2)
    context.assign(expression = b, value = 1)
    context.assign(expression = c, value = 3)

    println(expression.evaluate(context))
}