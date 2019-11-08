package chapter7

import kotlin.reflect.KProperty

class ManualDelegate {
    operator fun getValue(nothing: Nothing?, property: KProperty<*>): Int = 23
}

data class Email(val body: String)

class Person(val name: String) {
    private var _emails: List<Email>? = null

    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails()
            }
            return _emails!!
        }


}

private fun loadEmails(): List<Email> {
    println("i am a really slow and expensive operation that you only want to perform when really necessary!")
    return listOf(Email("Hey Joe"), Email("Jane said"), Email("Your order"))
}

class LazyPerson(val name: String) {
    val emails by lazy { loadEmails() }
}

fun main() {

    // handrolled delegate class. for vals, we just need to implement the getValue method on the delegate class
    val i: Int by ManualDelegate()
    println(i)

    // using delegates is useful when we want to use something like lazy instantiation. For example, with a backing property approach we need to write a lot of boilerplate
    val person = Person("Joe")
    println(person.emails)

    // we can achieve the same functionality without boilerplate by using the by lazy keywords with a lambda. Additionally, it is threadsafe
    val lazyPerson = LazyPerson("Pete")
    println(person.emails)
}