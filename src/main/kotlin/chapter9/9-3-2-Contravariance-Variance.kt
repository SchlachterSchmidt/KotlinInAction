package chapter9

fun main() {

    // A comparator declared for a certain type can also be used to compare subtypes. That is because the T can only occur
    // in the in `in` position.
    val anyComparator = Comparator<Any> {
        e1, e2 -> e1.hashCode() - e2.hashCode()
    }
    val strings = listOf("ABC", "DEF", "GHI")
    strings.sortedWith(anyComparator)

    // with contravariance, the typing relationship is reversed. Consumer<Animal> is a subtype of Consumer<Cat>!

    // classes or interfaces can be covariant on one parameter, and contravariant to another. Consider the funciton
    // interface

    // this is legal in Kotlin: Animal is a subtype of Cat in the in position (contravariant) and Int is a subtype of
    // Number in the covariant case
    fun enumerateCats(f: (Cat) -> Number) { }
    fun Animal.getIndex() = 1
    enumerateCats(Animal::getIndex)

}

interface MyComparator<in T> {
    fun compare(e1: T, e2: T): Int
    // this line will not compile. We can only consume T's, not produce them
    // fun add(e1: T, e2: T): T
}

interface Function<in P, out R> {
    operator fun invoke(p: P): R
}