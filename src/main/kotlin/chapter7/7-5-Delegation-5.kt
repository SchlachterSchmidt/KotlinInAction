package chapter7

class PersonWithVariableNumberOfAttributes {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    // this is one way of doing it
//    val name: String
//        get() = _attributes["name"]!!

    // and this another. This works because Kotlin supplies an extension to the getValue and setValue to the Map and MutableMap interfaces
    // where the name of the property is used as the key!
    val name: String by _attributes
}

fun main() {

    val person = PersonWithVariableNumberOfAttributes()

    val data = mapOf("name" to "Joe", "job" to "Clerk")
    for ((attrName, attrValue) in data) {
        person.setAttribute(attrName, attrValue)
    }
    println(person.name)


}