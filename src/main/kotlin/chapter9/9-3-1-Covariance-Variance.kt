package chapter9

fun main() {
    // in general, it is fine to pass a list of string to a function that expects a list of any
    fun printContents(list: List<Any>) {
        println(list.joinToString())
    }
    printContents(listOf("ABC", "CDE"))

    // but that is only true in case the function does not modify the list
    fun addAnswer(list: MutableList<Any>) {
        list.add(42)
    }
    val strings = mutableListOf("ABC", "DEF")
    // this line will not compile! The function modifies the original collection, which can lead to type inconsistencies.
    // addAnswer(strings)



    // the out keyword on the herd class means two things:
    // 1. the subtyping is preserved -> Herd<Cat> is a subtype of Herd<Animal> and we can safely use it because:
    // 2. t can only be used in out positions
    // we preserve type safely and prevent adding anything other than animals being added to the herd.


}

open class Animal {
    fun feed() {
        println("feeding the animal")
    }
}

class Cat : Animal() {
    fun cleanLitter() {
        println("cleaning the litter")
    }
}

// out means the class can produce values of T, but not consume them
// we can use the type also in the constructor. If we declare the constructor argument a val, we only generate a getter
// under the hood, and can use the out type. A val however will also generate a setter, and thus we cannot use it! A way
// around it would be to make the var private (so they are not part of the public API of the class)
class Herd<out T : Animal>(val leadAnimal: T, vararg animals: T, private var eldest: T) {
    val members = listOf<T>()
    // the use of T in the in position is prohibited
    // fun cull(animal: T) {}
}

fun feedAll(animals: Herd<Animal>) {
    animals.members.forEach { it.feed() }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    cats.members.forEach {
        it.cleanLitter()
        it.feed()
    }
    // this line only works because of the preserved covariance in the herd. Otherwise the subtype relationship would have been erased
    feedAll(cats)
}

interface AnimalTransformer<T> {
    // the argument passed to transform is in the `in` position, the return value is in the `out` position.
    fun transform(t: T): T
}

// the type can also be used as an argument to a new generic class
interface ANewList<out T> : Collection<T> {
    fun sublist(fromIndex: Int, toIndex: Int): ANewList<T>
}

// A MutableList cannot be declared as covariant on it's type parameters, because it has functions that accept T's (add to the list) as well as returns them
interface ANewMutableList<T> : Collection<T> {
    fun add(element: T): Boolean
}