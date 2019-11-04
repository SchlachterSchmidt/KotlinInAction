package Chapter6

fun main() {
    // we can use nullable type arguments as well
    val result = ArrayList<Int?>()
    (1..100).forEach {
        if (Math.floorMod(it, 2) == 0) {
            result.add(it)
        }
        else result.add(null)
    }
    println(result)

    // only individual items may be null
    val l1 = ArrayList<Int?>()

    // the entire collection may null, but if the collection is not null, neither are it's members
    val l2: List<Int>? = null

    fun addValidNumbers(numbers: List<Int?>) {
        val validNumbers = numbers.filterNotNull()
        println(validNumbers.sum())
    }

    addValidNumbers(listOf(1, 2, 3, null, 4, null, 5))
}