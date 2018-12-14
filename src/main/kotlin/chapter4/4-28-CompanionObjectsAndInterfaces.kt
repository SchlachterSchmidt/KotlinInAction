package chapter4

interface JSONFactory<T> {
    fun fromJSON(jsonString: String): T
}

class Animal(val name: String) {
    companion object : JSONFactory<Animal> {
        override fun fromJSON(jsonString: String): Animal {
            return Animal(name = jsonString)
        }
    }
}

fun main(args: Array<String>) {
    Animal.fromJSON("Pete")

    Animal.doStuff()
}

fun Animal.Companion.doStuff() = println("You can define extension functions on objects as well")