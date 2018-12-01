package chapter3

// as extension function
fun String.lastChar() = get(length - 1)

// as extension property
val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

fun main(args: Array<String>) {
    println("Hello".lastChar())

    println("Goodbye".lastChar)

    val sb = StringBuilder().append("Some String")
    println(sb.lastChar)

    sb.lastChar = 'c'
    println(sb.lastChar)
}