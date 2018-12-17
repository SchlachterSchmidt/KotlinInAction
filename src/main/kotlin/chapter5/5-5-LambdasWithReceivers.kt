package chapter5

import chapter5.java.Context
import chapter5.java.TextView

fun main(args: Array<String>) {

    // with allows you to call several operations on the same object without repeating it's name
    // 'with' is a method in Kotlin

    // naive implementation
    fun alphabet1(): String {
        val result = StringBuilder()
        for (letter in 'A'..'Z') {
            result.append(letter)
        }
        return result.toString()
    }

    // with is a function with two params: a receiver (StringBuilder in this case) and a lambda
    fun alphabet2() {
        val result = StringBuilder()
        with (result) {
            for (letter in 'A'..'Z') {
                this.append(letter) // this is optional as with any receiver
            }
            append("Now I know the alphabet") // optional this omitted
            toString()
        }
    }

    // simplify further
    fun alphabet3() = with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("Now I know the alphabet")
        toString()
    }

    // with returns the result of the lambda. Apply on the other hand returns the receiver object
    fun alphabet4() = StringBuilder().apply {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("Now I know the alphabet")
    }.toString() // Apply returns the receiver (StringBuilder) so we call toString outside of the lambda

    // there is even a dedicated receiver lambda called buildString that always used a StringBuilder as receiver and
    // calls toString
    fun alphabet5(): String = buildString {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("Now I know the alphabet")
    }

    // this is useful if you need to declare an object and initialize it right away with some value
    // in Java, this could be accomplished using a builder pattern
    fun createTextViewWithCustomAttributes(context: Context) = TextView(context).apply {
        text = "Hello"
        textSize = 22.5
        padding = 4
    }
}