package Chapter6

fun main() {
    // Kotlin does not allow for automatic number conversion as Java does, even if the old data type 'fits' in the new one
    val i: Int = 1
    val j: Long = i.toLong()

    // Library functions are provided for all primitive conversions, except booleans, and support extending and shrinking:
    val k: Int = j.toInt()

    // this enables more user friendly equality checks
    val x = 1
    val list = listOf(1L, 2L, 3L)
    println(x.toLong() in list)

    // type literals can be expressed in Kotlin in the following ways:
    val long: Long = 1L // Longs with l or L
    val double: Double = 0.2 // double used by default
    val float: Float = 0.2f // float with f or F
    val hex = 0x12345678 // hexadecimal with 0x or 0X
    val binary = 0B001011 // binary with 0b or 0B

    // character literals work the same as in Java:
    val char: Char = 'b'
    var tab: Char = '\t'

    // arithmatic operators are overloaded to accept all appropriate numeric types:
    fun foo(l: Long) = println(l)

    val b: Byte = 1
    val l = b + 1L
    foo(l)

    // We can also attempt to convert a string to int directly. throws arithmetic exception if no number in the string
    println("42".toInt())

    val answer: Any = 42
    println(answer.javaClass)

}
