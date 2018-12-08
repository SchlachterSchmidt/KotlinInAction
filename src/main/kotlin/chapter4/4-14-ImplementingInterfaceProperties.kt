package chapter4

// there are a number of different ways to implement the interface property. Note - only the implementing classes contain state
// implementing classes must provide a way to access this field
interface User {
    val nickname: String
    // interfaces can contain properties with getters and setters as long as they have no backing field. Otherwise the interface would be storing state
    // nickname must be overridden, whereas firstname can just be inherited
    val firstName: String
        get() = nickname.substringBefore(".")
}

// ex 1 - as primary constructor property
class PrivateUser(override val nickname: String) : User

// ex 2 - with custom getter. this property doesn't have a backing field. the value is calculated anew on every invokation
class SubscribingUser(val email: String) : User {
    override val nickname: String
        get() = email.substringBefore("@")
}

// ex 3 - using a property initializer. Here, we do have a backing field storing the result of the (potentially expensive) call to get the name
class FacebookUser(val accountId: String) : User {
    override val nickname = getFacebookName(accountId)
}

fun getFacebookName(accountId: String): String = "some string or another"