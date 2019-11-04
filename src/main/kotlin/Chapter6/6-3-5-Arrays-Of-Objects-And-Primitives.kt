package Chapter6

// An array in Kotlin is a class with type parameter:
fun main(args: Array<String>) {
    // iterate over an array
    for (i in args.indices) {
        println("Argument at $i is ${args[i]}")
    }

    fun printWithVararg(vararg values: Any) {
        for (value in values) {
            println(value)
        }
    }

    // we can also use any of the following methods
    val array1 = arrayOf(1, 2, 3)
    val array2 = arrayOfNulls<Int>(30)
    // the lambda takes the index of the array element and returns the value to be placed in the array at that index
    val array3 = Array<String>(26) { i -> ('a' + i).toString() }

    // using vararg and spread operator
    printWithVararg(*array3)

    val list1 = listOf(1, 2, 3)
    val arrayFromList1 = list1.toTypedArray()
    println(arrayFromList1.javaClass)

    // all array types are Object types, if we want a primitive array for interop, we need to use one of the special primitive type arrays
    val intArray1 = IntArray(1)
    val intArray2 = intArrayOf(1, 2, 3, 4)
    val intArray3 = IntArray(5) { i -> (i+1) * (i+1)  }
    val intArray4 = list1.toIntArray()
    println(intArray3.joinToString())

    // we also have the same set of extension functions available on arrays as we do have on collections
    intArray2.map { it * it }.filter { it > 5 }


}