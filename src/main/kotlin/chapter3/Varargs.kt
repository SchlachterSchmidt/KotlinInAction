package chapter3

fun <T> List<T>.addEmAll(vararg values: T): List<T> {
    return this + values
}

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4)
    val newList: List<Int> = list.addEmAll(2, 3, 4)

    println(newList)

    val list2: List<Int> = listOf(5, 6, 7)
    val anotherNewList = list.addEmAll(list2)

    println(anotherNewList)
}