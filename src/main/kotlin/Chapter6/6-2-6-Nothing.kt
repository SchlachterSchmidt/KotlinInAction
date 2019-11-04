package Chapter6

import Chapter6.java.Person
import java.lang.IllegalArgumentException

fun main() {

    // Kotlin has the Nothing type to indicate that a function never completes under normal circumstances
    fun f1(): Nothing {
        while (true) {
            println("i can do this all day")
        }
    }

    // or to indicate that a method only throws exceptions:
    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }
    // where we can use it on the right of the elvis:
    val person = Person(null, 0, null)
    val name = person.name ?: fail("no name provided")

    // we can alo use nothing as variable type, but we cannot store anything in a nothing variable, so it makes no sense to do so
    val nothing: Nothing


}