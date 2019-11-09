package chapter8

import kotlin.math.pow

fun main() {

    // we can store function references with implicit types
    val sum = { x: Int, y: Int -> x + y }
    println( sum(2, 3) )

    // or declare them explicitly with () for the arguments, and -> for the return types
    val raise: (Int, Int) -> Int = { x, y -> x.toDouble().pow(y.toDouble()).toInt() }
    println(raise(3, 5))

    // no arg, no return value
    val print42: () -> Unit = { println(42) }
    print42()

    // we can also return null
    val returnNull: (Int, Int) -> Int? = { x, y -> null }

    // the entire function reference can also be null
    val funOrNull: ((Int, Int) -> Int)? = null

    listOf(1, 2, 3, 4).map { raise(it, 2) }.also { println(it) }

    // defining a simple higher order function
    fun performRequest(
        url: String,
        callback: (code: Int, content: String) -> Unit
    ) {
        println("performing request with $url")
        callback(1, "bc")
    }

    performRequest("www.url.com") { code, content -> println("performing callback with $code and $content") }

    // and another one
    fun twoAndThree(operation: (Int, Int) -> Int) {
        val result = operation(2, 3)
        println("the result is $result")
    }

    twoAndThree { x, y -> x + y }
    twoAndThree { x, y -> x * y }
    twoAndThree { x, y -> x.toDouble().pow(y).toInt() }

    // reimplementing a simple filter function
    fun String.filter(predicate: (Char) -> Boolean): String {
        println("Running your custom filter")
        val sb = StringBuilder()
        for (index in 0 until length) {
            val element = get(index)
            if (predicate(element)) sb.append(element)
        }
        return sb.toString()
    }

    println("I Am A Long String With Lots Of Capital Letters".filter { c -> c.isLowerCase() })

    // reimplementing a joinToString method with a default transformer. We can supply our own transformer method if we want to
    // calling the lambda is transformed to a call to the invoke method under the hood (with the body of the lambda inserted)
    // since in this case the parameter is nullable, we need to use the safe call syntax, and use the elvis to provide a default behaviour in case no
    // transformer is supplied
    fun <T> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: ((T) -> String)?  = null
    ): String {
        val result = StringBuilder(prefix)

        for ((index, element) in this.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(transform?.invoke(element) ?: element.toString())
        }
        result.append(postfix)

        println(result)
        return result.toString()
    }

    val values = listOf("Alpha", "Beta", "Gamma")

    values.joinToString()
    values.joinToString(separator = "-")
    values.joinToString(prefix = "~", postfix = "!") { it.toUpperCase() }

    // we can also define functions that return other functions! To declare a function that returns another function, the
    // same syntax applies in the declaration with () denoting the parameter(s) of  the function, and -> the return type
    // to return a function, we can use either a lambda, a member reference or a function type
    fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
        if (delivery == Delivery.EXPEDITED) return { order -> 6 + 2.1 * order.itemCount  }
        return { order -> 1.2 * order.itemCount }
    }
    val calculator = getShippingCostCalculator(delivery = Delivery.STANDARD)
    println("Shipping costs ${calculator(Order(3))}")


    // Immagine a UI that allows you to filter contacts by attributes. We can decouple the filter logic from the UI we can define a function that creates a predicate
    // to filter the contact list. We need to update the contactListFilters as the user types to
    val contacts = listOf(Person("Dmitry", "Jemerov", "123-456-789"), Person("Svetlana", "Isakov", null))
    val contactListFilters = ContactListFilters()

    with (contactListFilters){
        prefix = "Dm"
        onlyWithPhoneNumber = true

    }
    println(contacts.filter(contactListFilters.getPredicate()))

    // we can also remove a lot of duplication by using higher order functions, like when we are filtering. Rather than defining a filter for every possible combinatin of
    // values, we can use higher order functions
    val log = listOf(
        SiteVisit("/", 34.0, OS.LINUX),
        SiteVisit("/", 24.0, OS.MAC),
        SiteVisit("/login", 14.0, OS.LINUX),
        SiteVisit("/signup", 8.0, OS.ANDROID),
        SiteVisit("/", 34.0, OS.LINUX),
        SiteVisit("/", 164.0, OS.IOS),
        SiteVisit("/home", 124.0, OS.WINDOWS),
        SiteVisit("/signup", 8.0, OS.WINDOWS)

        )

    // for example, filters all windows visits only and averages duration
    val averageWindowsDuration = log
        .filter { it.os == OS.WINDOWS }
        .map { it.duration }
        .average()
    println(averageWindowsDuration)

    // if we want to calculate the same metric for MAC, we would have to duplicate. Or:
    fun List<SiteVisit>.averageDurationFor(os: OS) =
        filter { it.os == os }.map { it.duration }.average()

    println(log.averageDurationFor(OS.MAC))

    // Now if we would like to know the average duration for all mobile platforms we are again in the same bind:
    val averageMobileDuration = log
        .filter { it.os in setOf(OS.ANDROID, OS.IOS) }
        .map { it.duration }
        .average()
    println(averageMobileDuration)

    // to be really flexible, we can use a higher order function
    fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
        filter(predicate).map { it.duration }.average()


    // now we can recycle this method for any filter we would like to supply ourselves!
    println(log.averageDurationFor { it.os == OS.LINUX && it.path == "/"} )
}

enum class Delivery { STANDARD, EXPEDITED}

data class Order(val itemCount: Int)

data class Person(val firstName: String, val lastName: String, val phoneNumber: String?)

class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }
        if (!onlyWithPhoneNumber) return startsWithPrefix
        return { startsWithPrefix(it) && it.phoneNumber != null }
    }
}

data class SiteVisit(val path: String, val duration: Double, val os: OS)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }
