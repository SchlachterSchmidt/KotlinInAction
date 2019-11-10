package chapter8

fun main() {

    val people = listOf(
        Person("Alice", "Allision", null),
        Person("Bob", "Bobson", null),
        Person("Charlie", "Charlston", null)
    ).shuffled()

    // when using the return keyword, we not only return from the lambda, but also the enclosing function!
    // compare the use of the return statement in this function:
    fun findAlice(people: List<Person>) {
        for (person in people) {
            if (person.firstName == "Alice") {
                println("Alice found!")
                return
            }
        }
        println("Who the fuck is Alice?")

    }

    // with the use of the return statement here
    fun lookForAlice(people: List<Person>) {
        people.forEach {
            if (it.firstName == "Alice") {
                println("Found Alice")
                return
            }
        }
        println("Who the fuck is Alice")
    }
    // in both cases the second println is not executed. This is because in the lambda, the return is 'non-local', meaning
    // it returns from not only it's local context, but the enclosing one as well. This is ONLY possible if the function taking
    // the lambda is inlined (like forEach in this case) because in the resulting bytecode, the return statement will be inlined as well
    findAlice(people)
    lookForAlice(people)

    // we can use labels to circumvent this behaviour (which becomes ugly and cumbersome quickly) or use anonymous functions
    // in this case the return statement refers to it's enclosing scope - the anonymous function!
    fun iveBeenLookingForAlice(people: List<Person>) {
        people.forEach(fun (person) {
            if (person.firstName == "Alice") return
            println("${person.firstName} is not Alice")
        })
    }
    iveBeenLookingForAlice(people)

    // anonymous functions with block body require the return type to be specified
    println(people.map(fun (person): Boolean {
        println(person.firstName)
        return person.phoneNumber != null
    }))
}