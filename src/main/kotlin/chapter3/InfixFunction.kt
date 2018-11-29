package chapter3

infix fun Any.mapTo(other: Any) = Pair(this, other)

fun main(args: Array<String>) {
    val map = mapOf("1" mapTo 1, 2 mapTo "2", "3" mapTo "three", 4 mapTo 4)

    println(map)

    val (name, elem) = 1 mapTo "4"

    println("$name -> $elem")

    for ((key, value) in map) {
        println("$key ~> $value")
    }
}