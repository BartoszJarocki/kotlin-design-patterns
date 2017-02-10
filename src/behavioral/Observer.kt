package behavioral

import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Returns a property delegate for a read/write property that calls a specified callback function when changed.
 * @param initialValue the initial value of the property.
 * @param beforeChange the callback which is called before the change of the property.
 * @param afterChange the callback which is called after the change of the property is made. The value of the property
 *  has already been changed when this callback is invoked.
 */
inline fun <T> observable(initialValue: T,
                          crossinline beforeChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Boolean,
                          crossinline afterChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit): ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = afterChange(property, oldValue, newValue)
    override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T) = beforeChange(property, oldValue, newValue)
}

interface PropertyObserver {
    fun willChange(propertyName: String, newPropertyValue: Any?)
    fun didChange(propertyName: String, oldPropertyValue: Any?)
}

class Observer : PropertyObserver {
    override fun willChange(propertyName: String, newPropertyValue: Any?) {
        if (newPropertyValue is String && newPropertyValue == "test") {
            println("Okay. Look. We both said a lot of things that you're going to regret.")
        }
    }

    override fun didChange(propertyName: String, oldPropertyValue: Any?) {
        if (oldPropertyValue is String && oldPropertyValue == "<no name>") {
            println("Sorry about the mess. I've really let the place go since you killed me.")
        }
    }
}

class User(val propertyObserver: PropertyObserver?) {
    var name: String by observable("<no name>", { prop, old, new ->
        println("Before change: $old -> $new")
        propertyObserver?.willChange(name, new)

        return@observable true
    }, { prop, old, new ->
        propertyObserver?.didChange(name, old)

        println("After change: $old -> $new")
    })
}

fun main(args: Array<String>) {
    val observer = Observer()
    val user = User(observer)

    user.name = "test"
}