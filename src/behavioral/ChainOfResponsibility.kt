package behavioral

class MoneyPile(val value: Int, var quantity: Int, var nextPile: MoneyPile?) {
    fun canTakeSomeBill(want: Int): Boolean = (want / this.value) > 0

    fun canWithdraw(withdrawAmount: Int): Boolean {
        var amount = withdrawAmount
        var quantity = this.quantity

        while (canTakeSomeBill(want = amount)) {
            if (quantity == 0) {
                break
            }

            amount -= this.value
            quantity -= 1
        }

        if (amount <= 0) {
            return true
        }

        nextPile?.let {
            return it.canWithdraw(withdrawAmount = amount)
        }

        return false
    }
}

class ATM(val moneyPile: MoneyPile) {
    fun canWithdraw(amount: Int) {
        println("Can withdraw: ${moneyPile.canWithdraw(withdrawAmount = amount)}")
    }
}

fun main(args: Array<String>) {
    val ten = MoneyPile(value = 10, quantity = 6, nextPile = null) // 60
    val twenty = MoneyPile(value = 20, quantity = 2, nextPile = ten) // 40
    val fifty = MoneyPile(value = 50, quantity = 2, nextPile = twenty) // 100
    val hundred = MoneyPile(value = 100, quantity = 1, nextPile = fifty) // 100

    var atm = ATM(moneyPile = hundred)
    atm.canWithdraw(amount = 310) // Cannot because behavioral.ATM has only 300
    atm.canWithdraw(amount = 100) // Can withdraw - 1x100
    atm.canWithdraw(amount = 165) // Cannot withdraw because behavioral.ATM doesn't has bill with value of 5
    atm.canWithdraw(amount = 30)  // Can withdraw - 1x20, 2x10
}
