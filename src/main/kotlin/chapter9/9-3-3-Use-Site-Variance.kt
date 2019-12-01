package chapter9

import java.lang.IllegalArgumentException
import java.util.*
import kotlin.reflect.KClass

fun main() {
    val data = mutableListOf<String>("1", "2", "3")
    val anyList = mutableListOf<Any>()

    // type mismatch, even though String is a subclass of Any
    // copyData1(data, anyList)

    // this now works as we are declaring the source to be a subtype of the elements in the destination.
    copyData2(data, anyList)

    // this also works, and is a bit easier to understand
    copyData3(data, anyList)

    // this works just as well
    copyData4(data, anyList)

    val numberList: MutableList<out Number> = mutableListOf<Number>()
    // this will not compile! we can restrict the usage of the type at the use site here as well!
    // list.add(43)

    // star projections are not the same as Any? Any? can be anything, but <*> means that the list contains elements of a
    // specific type, it is just not known what the type is
    val anyNullableList: MutableList<Any?> = mutableListOf('1', 2, "three")
    val charList = mutableListOf('1', 'b', 'c')
    val unknownElements: MutableList<*> = if (Random().nextBoolean()) anyNullableList else charList

    // in this case, unknownElements is called out-projected. It is safe to get elements from it, but not add to it

    // we can use the <*> projection, if we don't care about the specifics of the type parameter
    // isNotEmpty() doesnt use the generic type, and first() returns Any? now, but we don't care
    fun printFirst(list: List<*>) {
        if (list.isNotEmpty()) {
            println(list.first())
        }
    }



    val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
    validators[String::class] = DefaultStringValidator
    validators[Int::class] = DefaultIntValidator

    // this causes a type mismatch!
    // validators[String::class]!!.validate("")

    // a hacky way to fix this
    val stringValidator = validators[String::class] as DefaultStringValidator
    stringValidator.validate("")

    // but there is nothing that prevents us from doing this: getiing an IntValidator, and casting it as StringValidator
    // val stringValidator2 = validators[Int::class] as DefaultStringValidator
    // stringValidator2.validate("")

    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)

    println(Validators[String::class].validate(""))


}


// This implementation works find, until you try to copy a string to a list of any
fun <T> copyData1(source: MutableList<T>, destination: MutableList<T>) {
    for (item in source)  {
        destination.add(item)
    }
}

// works, but is pretty hard to understand
fun <T: R, R> copyData2(source: MutableList<T>, destination: MutableList<R>) {
    for (item in source)  {
        destination.add(item)
    }
}

// this is called type projection. in the source, <out T> means T is not a regular MutableList, but a restricted (projected) one.
// You can only call methods on it that have T in the out position.
fun <T> copyData3(source: MutableList<out T>, destination: MutableList<T>) {
    for (item in source)  {
        destination.add(item)
    }
}

// we can even declare that we expect an immutable List, as we are using soure only as producer.
fun <T> copyData4(source: List<T>, destination: MutableList<in T>) {
    source.forEach { destination.add(it) }
}

interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String): Boolean = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int) : Boolean = input >= 0
}

object Validators {
    private val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()

    fun <T: Any> registerValidator(kClass: KClass<T>, fieldValidator: FieldValidator<T>) {
        validators[kClass] = fieldValidator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T: Any> get(kClass: KClass<T>) : FieldValidator<T> =
        validators[kClass] as? FieldValidator<T> ?: throw IllegalArgumentException("No validator for $kClass")
}

