interface Currency {
    fun symbol(): String
    fun code(): String
}

class Euro : Currency {
    override fun symbol(): String {
        return "€"
    }

    override fun code(): String {
        return "EUR"
    }
}

class UnitedStatesDollar : Currency {
    override fun symbol(): String {
        return "$"
    }

    override fun code(): String {
        return "USD"
    }
}

enum class Country {
    unitedStates, spain, uk, greece
}

object CurrencyFactory {
    fun currency(country: Country): Currency? {
        when (country) {
            Country.spain, Country.greece -> return Euro()
            Country.unitedStates -> return UnitedStatesDollar()
            else -> return null
        }
    }
}

fun main(args: Array<String>) {
    val noCurrencyCode = "No Currency Code Available"

    println(CurrencyFactory.currency(Country.greece)?.code() ?: noCurrencyCode)
    println(CurrencyFactory.currency(Country.spain)?.code() ?: noCurrencyCode)
    println(CurrencyFactory.currency(Country.unitedStates)?.code() ?: noCurrencyCode)
    println(CurrencyFactory.currency(Country.uk)?.code() ?: noCurrencyCode)
}