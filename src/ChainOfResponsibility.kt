class MoneyPile(val value: Int, var quantity: Int, var nextPile: MoneyPile?) {
    fun canTakeSomeBill(want: Int): Boolean = (want / this.value) > 0

    fun canWithdraw(amount: Int): Boolean {
        var a = amount
        var quantity = this.quantity

        while (canTakeSomeBill(want = a)) {
            if (quantity == 0) {
                break
            }

            a -= this.value
            quantity -= 1
        }

        if (a <= 0) {
            return true
        }

        nextPile?.let {
            return it.canWithdraw(amount = a)
        }

        return false
    }
}

class ATM(val moneyPile: MoneyPile) {
    fun canWithdraw(amount: Int) {
        println("Can withdraw: ${moneyPile.canWithdraw(amount = amount)}")
    }
}

fun main(args: Array<String>) {
    val ten = MoneyPile(value = 10, quantity = 6, nextPile = null)
    val twenty = MoneyPile(value = 20, quantity = 2, nextPile = ten)
    val fifty = MoneyPile(value = 50, quantity = 2, nextPile = twenty)
    val hundred = MoneyPile(value = 100, quantity = 1, nextPile = fifty)

    var atm = ATM(moneyPile = hundred)
    atm.canWithdraw(amount = 310) // Cannot because ATM has only 300
    atm.canWithdraw(amount = 100) // Can withdraw - 1x100
    atm.canWithdraw(amount = 165) // Cannot withdraw because ATM doesn't has bill with value of 5
    atm.canWithdraw(amount = 30)  // Can withdraw - 1x20, 2x10
}
