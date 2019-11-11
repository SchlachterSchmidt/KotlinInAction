package chapter9

import java.lang.Appendable

val <T> List<T>.penultimate: T
    get() = this[size - 2]

interface MyList<T> {
    operator fun get(index: Int): T
}

interface MyComparable<T> {
    fun compareTo(other: T): Int
}

fun main() {
    // for lists, we can specify the type either at the declaration of the variable or by specifying the type argument for the function creating the list
    // the type argument must always be supplied if it cannot be inferred by the compiler (unlike Java, where we can use raw types)
    val l1: List<String> = listOf()
    val l2 = listOf<String>()

    // writing a function that works with a generic list needs to provide it's type parameter
    // - fun <T>        - type parameter declaration
    // - List<T>.slice  - type parameter is used in receiver
    // - :List<T>       - type parameter is used as return value
    fun <T> List<T>.slice(indices: IntRange): List<T> = this.subList(indices.first, indices.last)

    val letters = ('a'..'z').toList()

    // we may supply the type parameter here, but it is not required if it can be inferred
    println(letters.slice<Char>(0..2))
    println(letters.slice(3..12))

    // we can also specify extension properties in the same fashion (but only on extension properties, not regular ones!
    println(letters.penultimate)

    // in classes and interfaces:
    class MyStringList : MyList<String> {
        override fun get(index: Int): String { //  we return the type parameter!
            return this[index]
        }
    }

    class MyArrayList<T> : MyList<T> { // now the type parameter is the argument for MyList
        override fun get(index: Int): T {
            return this[index]
        }
    }

    // a class declaration can also provide it's own type as in the example of Comparable:
    class MyString : Comparable<MyString> {
        override fun compareTo(other: MyString): Int {
            return 1
        }
    }

    // we can specify an upper bound to the type parameter
    fun <T: Number> oneHalf(value: T): Double {
        return value.toDouble() / 2.0
    }

    fun <T: Comparable<T>> max(first: T, second: T): T {
        return if (first > second) first else second
    }

    // to specify multiple constraints we use a different syntax
    fun <T> ensureTrailingPeriod(seq: T) where T: CharSequence, T: Appendable {
        if (!seq.endsWith('.')) seq.append('.')
    }

    // if we specify no upper bound, the implicit upper bound is Any? making the type argument nullable:
    class Processor<T> {
        fun process(value: T) {
            println(value?.hashCode())
        }
    }

    val nullableStringProcessor = Processor<String?>()
    nullableStringProcessor.process(null)

    // we must specify Any as upper bound if we do not want to accept nullable types
    class NonNullableProcessor<T: Any> {
        fun process(value: T) {
            println(value.hashCode())
        }
    }
    val nonNullableProcessor = NonNullableProcessor<String>()
    nonNullableProcessor.process("Process Me")
}
