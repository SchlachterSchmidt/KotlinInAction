package Chapter6

fun main(args: Array<String>) {

    var string = "I am not nullable"
    var nullableString: String? = "I am nullable"

    nullableString = null
    // this will not work
    // string = null

    fun stringLength(s: String) = s.length
    // this function expects a non-nullable String type, not a nullable String?, so this will fail at compile time!
    // stringLength(nullableString)

    // Safe version of the function accepts a String? as param
    // also introducing the null save ?. and ?: operators
    // if s is null, 0 is returned
    fun stringLengthSafe(s: String?) = s?.length ?: 0

    // the save call operator ?. is basically the same as this whole expression, combining null check and method call:
    if (nullableString != null ) nullableString.length else null
    // the result of such a call is the nullable type (String? in this case)
    nullableString?.length

    if (nullableString != null) {
        // the compiler 'remembers' the null check above, and allows you to pass a nullable argument to a function
        // expecting a non nullable type if the check passes
        stringLengthSafe(nullableString)
    }

    class Address(val street: String)

    class Company(val name: String, val address: Address?)

    class Employee(val name: String, val company: Company?)


    // quite verbose way of checking if we have a street
    fun Employee.getCompanyStreet(): String {
        val street = this.company?.address?.street
        return if (street != null) street else "Unknown"
    }

    // shorter version of the above using the elvis operator
    fun Employee.getCompanyStreetShort(): String {
        return this.company?.address?.street ?: "Unknown"
    }

    class Person(val firstName: String, val lastName: String) {
        // the safe cast operator allows to cast an object to a type or return null in case the object is not of the expected type
        override fun equals(other: Any?): Boolean {
            val otherPerson = other as? Person ?: return false

            return otherPerson.firstName == firstName &&
                    otherPerson.lastName == lastName
        }
    }

    // non null assertion if you know for a fact that a nullable value is not null:
    val sth: String? = "I am something"
    sth!!.length
    // if sth is null in this case, Kotlin will throw a NPE referring to the line of the non null assertion
}