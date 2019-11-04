package Chapter6

fun main() {
    //Kotlin provides two separate interfaces for mutable and immutable collections
    fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
        source.forEach {
            target.add(it)
        }
    }

    val source: Collection<Int> = arrayListOf(1, 2, 3, 4, 5)
    val target: MutableCollection<Int> = arrayListOf()

    copyElements(source, target)

    println(target)
}