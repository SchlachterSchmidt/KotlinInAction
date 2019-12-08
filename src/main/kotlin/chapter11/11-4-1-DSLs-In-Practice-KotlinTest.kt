package chapter11

import java.lang.AssertionError

fun main() {
    
    // ex 1
    val string = "Kotlin"
    string should startWith("Kot")
    
    // ex 2
    "kotlin" should start with "kot"
    // translates to:
    "kotlin".should(start).with("kot")
    
}


// ex 1
infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)

interface Matcher<T> {
    fun test(value: T)
}

class startWith(val prefix: String) : Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix)) {
            throw AssertionError("String $value does not start with $prefix")
        }
    }
}

// ex 2
object start

infix fun String.should(x: start): StartWrapper = StartWrapper(this)

class StartWrapper(val value: String) {
    infix fun with(prefix: String) {
        if (!value.startsWith(prefix)) throw AssertionError("String $value does not start with $prefix")
    }
}