package Chapter6

import Chapter6.java.Person

fun main() {

    // the Person class is defined in Java. The Kotlin compiler has no information about the
    // nullability of the name property. Kotlin let's you treat this property as nullable or non-nullable, and it is
    // up to the developer to handle any possible null values, but does not enforce the use of ?. or !!.
    fun yellAt(person: Person) {
        println(person.name.toUpperCase() + "!!!")
    }


    // Kotlin will however use available annotations to infer nullability if possible.
    // age is annotated with @NotNull and lastName with @Nullable
    val person: Person = Person(null, 22, null)
    // yellAt(person)

    // a safe version of the yellAt method would be
    fun yellAtSafe(person: Person) {
        println((person.name ?: "Anyone").toUpperCase() + "!!!")
    }

    yellAtSafe(person)
}