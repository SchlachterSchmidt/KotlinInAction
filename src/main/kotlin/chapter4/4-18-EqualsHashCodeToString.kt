package chapter4

class Client(val name: String, val code: Int) {

    override fun toString(): String = "$name, $code"

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) return false
        return name == other.name && code == other.code
    }

    override fun hashCode(): Int = name.hashCode() * 31 + code

    // this gets unwieldy quick
    fun copy(name: String = this.name, code: Int = this.code) = Client(name, code)
}

// all of the boilerplate can be done away with by using data classes
// all attributes declared in the primary constructor are considered in the `hashCode()` and `equals()` methods.
// values that are not declared in the primary constructor are not!
data class ClientDataClass(val name: String, val code: Int)

fun main(args: Array<String>) {
    val client1 = Client("Alice", 123)
    val client2 = Client("Alice", 123)

    println(client1.toString())

    // in Kotlin, `==` checks object equality by default and is translated to a call to the `equals()` method
    // `===` checks reference equality
    // prints true because we have overridden equals method to check structural equality
    println(client1 == client2) // false if equals method is not overridden

    val processed = hashSetOf(Client("Bob", 321))
    // even though these two clients would be considered equal with the overridden `equals()` method,
    // this will print false if we do not override `hashCode()`. Hash codes are different for two equal instances of the same class
    println(processed.contains(Client("Bob", 321))) // false if we do not override `hashCode()`

    val client3 = client2.copy(name = "bob")
    println(client3)
}