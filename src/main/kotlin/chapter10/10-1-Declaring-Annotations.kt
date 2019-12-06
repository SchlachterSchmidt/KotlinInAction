package chapter10

fun main() {
    // intellij will not only show the use of a deprecated function, but also be able to fix automatically!
    remove(2)

    val person = Person("Alice", 22)
}

class TestTarget {
    companion object {
        // to use a property as annotation argument, it must be known at compile time. It must be a constant.
        const val TEST_TIMEOUT = 100L
    }

    @Test(timeout = TEST_TIMEOUT)
    fun myAnnotatedTestFunction() { /*...*/}

    /* there are many more annotation site targets like
    - property: Java annotation can't be applied with this use site target
    - field: the field generated for this property
    - get: the getter
    - set: the setter
    - receiver: the receiver param of an extension function or property
    - param: constructor param
    - setparam: property setter param
    - delegate: field storing the delegate instance for a delegated property
    - file: class that contains top level functions and properties declared in this file
    this annotation will only be applied to the getter */
    @get:GetterAnnotation
    val someVal: Int = 0

    /* There are also annotation that control the bytecode generation
    - @JvmName: changes the name of a java method or field generated from Kotlin
    - @JvmStatic: exposes methods of an object as static Java methods
    - @JvmOverloads: generates overloads for functions that have default params
    - @JvmField: expose the underlying field as public Java field without getter or setter
    */
}

// declaring an annotation class
annotation class GetterAnnotation


// declaring an annotation class with argument with meta annottation (annotation of an annotation class -> here defining where the annotation can be applied
@Target(allowedTargets = [AnnotationTarget.FUNCTION])
annotation class Test(val timeout: Long)

@Deprecated("Deprecated. Use removeAt(index) instead", ReplaceWith("removeAt(index)"))
fun remove(index: Int) { /*...*/ }

fun removeAt(index: Int) { /*...*/ }

data class Person(val name: String, val age: Int)
