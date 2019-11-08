package chapter7


class NameComponents(val name: String, val ext: String) {
    operator fun component1() = name
    operator fun component2() = ext

}

fun String.splitFileName(): NameComponents {
    val result = this.split(".", limit = 2)
    return NameComponents(result[0], result[1])
}

fun Map<String, String>.printEntries() {
    for ((key, value) in this) {
        println("$key -> $value")
    }
}

fun main() {
    val p1 = Point(1, 5)

    // destructuring the components of a data class
    val (x, y) = p1

    val split = "file.txt".splitFileName()
    // we can do this because we implemented the componentN functions for the members
    val (name, ext) = split
    println("$name -> $ext")

    // we can also use the destructuring in loops
    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    map.printEntries()
}