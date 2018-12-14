package chapter5

data class Person(val name: String, val age: Int)

fun topLevel() = println("top!")

fun main(args: Array<String>) {

    val people = listOf(Person("Bob", 27), Person("Alice", 22), Person("Chad", 43))
    // max by lambda expression using default argument name 'it'
    println(people.maxBy { it.age })

    // reference using member reference. some problem with this
    // println(people.maxBy { Person::age })

    // assigning lambda to a variable and calling it like a function
    val sum = { x: Int, y: Int -> x + y }
    println(sum(2, 3))

    // running a lambda directly
    val x = 43
    run { println(x) }

    // lambda as named argument
    val names = people.joinToString(separator = " ", transform = { it.name })
    println(names)

    // the lambda can be moved out of the parenthesis of the function accepting it, if it is the last argument
    val names2 = people.reversed().joinToString(separator = " ") { it.name }
    println(names2)

    // last line of a lambda is the implicit return value
    val sumWithLogging = { x: Int, y: Int ->
        println("I am adding $x and $y")
        // this is the implicit return value
        x + y
    }
    println(sumWithLogging(2, 3))

    // lambdas have access to the scope of the surrounding method like the prefix in this case
    fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
        messages.forEach {
            println("$prefix: $it")
        }
    }
    printMessagesWithPrefix(listOf("403 FORBIDDEN", "404 NOT FOUND"), "ERROR")

    // in Kotlin, you can also modify variables (non-final, unlike Java)
    // these variables are _captured_ by the lambda
    fun countErrorTypes(responses: Collection<String>) {
        var clientErrors: Int = 0
        var serverErrors: Int = 0
        responses.forEach {
            when {
                it.startsWith("4") -> clientErrors++
                it.startsWith("5") -> serverErrors++
            }
        }
        println("client errors: $clientErrors, server errors: $serverErrors")
    }

    countErrorTypes(listOf("200 OK", "404 NOT FOUND", "501 INTERNAL SERVER ERROR"))
    //  top level function using member referencing
    run(::topLevel)

    // can be used as shorthand if a lambda delegates to another method
    fun sendEmail(recipient: String, message: String) = println("sending $message to $recipient")
    // long version
    val longAction = { recipient: String, message: String -> sendEmail(recipient, message) }
    // concise version
    val action = ::sendEmail
    action("joe", "hello")

    // delay creating instance of a class using constructor reference
    val createPerson = ::Person
    createPerson("Dyllan", 22)
}