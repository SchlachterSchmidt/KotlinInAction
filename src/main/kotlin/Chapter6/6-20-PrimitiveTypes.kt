package Chapter6

fun main() {
    // Kotlin does not distinguish between primitive types (eg. int) and wrapper types (eg. Integer)
    val i: Int = 1

    // you can also call methods on values of a number type:
    fun showProgress(progress: Int) {
        val percent = progress.coerceIn(0,100)
        println("$percent done")
    }
    showProgress(140)

    // the Kotlin compiler will pick the right type (primitive or wrapper) for every occasion

    // nullable types like Int? cannot be compiled to Java primitives because they cannot hold null values, so they
    // are compiled to wrapper types instead
    data class Person(val name: String, val age: Int? = null) {

        fun isOlderThan(other: Person): Boolean? {
            if (age == null || other.age == null) return null
            return age > other.age
        }
    }
    println(Person("Sam", 35).isOlderThan(Person("Jane")))

    // when using generics, the wrapper type is also chosen automatically, since List<int> is not valid in Java
    val list: List<Int> = listOf(1, 2, 3)
}