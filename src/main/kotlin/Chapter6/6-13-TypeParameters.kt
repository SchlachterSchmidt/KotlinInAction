package Chapter6

fun main() {


    // T may be null, even though it has no question mark so we need to safe call the hashCode method
    fun <T> printHashCode(t: T) {
        println(t?.hashCode())
    }

    val test1: String? = null
    val test2: String = "ASD"

    printHashCode(test1)
    printHashCode(test2)

    // to get rid of this, we can specify a non-nullable upper bound
    fun <T: Any> printHashCodeSafe(t: T) {
        println(t.hashCode())
    }

    // this will cause a type mismatch error:
    // printHashCodeSafe(test1)

    // this works fine
    printHashCodeSafe(test2)
}