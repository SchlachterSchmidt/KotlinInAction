package chapter4

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main(args: Array<String>) {
    val countingSet = CountingSet<Int>()
    countingSet.addAll(listOf(1, 1, 2, 2))
    println("${countingSet.objectsAdded} given, ${countingSet.size} added")
}