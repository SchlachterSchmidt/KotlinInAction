package chapter4

// if you use the default getter and setter implementations, a backing field is automatically generated
// if you are overriding them without using the `field` keyword, no backing field is generated
// if you are using the `field` keyword, a backing field will be generated
class UserClass(val name: String) {
    var address = "unspecified"
        get() {
            println("getting the address")
            return field
        }
        set(value) {
            println("""
                Address was changed for $name:
                "$field" -> "$value"
            """.trimIndent())
            field = value
        }
    // can only change the value of phone from inside the class
    var phone: String = "unknown"
        private set
}