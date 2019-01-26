package Chapter6

fun main() {


    // the let function turns the object it is called on into the receiver of a lambda
    // Combined with a safe call ?. this can make passing nullable arguments to functions that expect a non nullable type
    // easier

    fun sendEmail(email: String) {
        println("email sent to $email")
    }

    var email: String? = "someemail@example.com"

    // these two expressions are equivalent
    email?.let { sendEmail(it) }
    if (email != null) sendEmail(email)


    // still works fine
    email = null
    email?.let { sendEmail(it) }

    // if we omit the safe call, we get a type mismatch error
    // email.let { sendEmail(it) }

}