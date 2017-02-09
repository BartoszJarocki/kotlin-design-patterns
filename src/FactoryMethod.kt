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
    UnitedStates, Spain, UK, Greece
}

object CurrencyFactory {
    fun currency(country: Country): Currency? {
        when (country) {
            Country.Spain, Country.Greece -> return Euro()
            Country.UnitedStates -> return UnitedStatesDollar()
            else -> return null
        }
    }
}

fun main(args: Array<String>) {
    val noCurrencyCode = "No Currency Code Available"

    println(CurrencyFactory.currency(Country.Greece)?.code() ?: noCurrencyCode)
    println(CurrencyFactory.currency(Country.Spain)?.code() ?: noCurrencyCode)
    println(CurrencyFactory.currency(Country.UnitedStates)?.code() ?: noCurrencyCode)
    println(CurrencyFactory.currency(Country.UK)?.code() ?: noCurrencyCode)
}