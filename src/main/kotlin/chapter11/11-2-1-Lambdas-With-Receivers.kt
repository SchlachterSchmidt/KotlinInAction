package chapter11

fun main() {

    // this is nice
    val s1 = buildString1 {
        it.append("Hello")
        it.append("World")
    }
    println(s1)

    // but we would like to be able to omit the it, and just refer to the receiver directly
    val s2 = buildString2 {
        append("Hwllo")
        append("World")
        appendExcl()
    }
    println(s2)

    val s3 = buildString3 {
        append("Hello")
        append("World")
        appendExcl()
    }
    println(s3)

    // if you don't care about the type, with and apply are interchangeable
    val map = mutableMapOf(1 to "one")
    map.apply { this[2] = "two" }
    with (map) { this[3] = "three" }
    println(map)


}

fun buildString1(builderAction: (StringBuilder) -> Unit): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}

// notice the lambda with receiver
fun buildString2(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.builderAction()
    return sb.toString()
}

// it is also possible to declare a variable of an extension function type.
val appendExcl : StringBuilder.() -> Unit = { this.append("!") }

fun buildString3(builderAction: StringBuilder.() -> Unit): String = StringBuilder().apply(builderAction).toString()

// implementing apply:
// 1. Apply will apply a given argument of a type of extension function on the given receiver
// 2. and return the receiver
inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

// implementing with
// Where apply returns the receiver, with returns the result of the lambda passed to the receiver
inline fun <T, R> T.with(receiver: T, block: T.() -> R) = receiver.block()