package chapter3

class User(val name: String, val address: String)

object UserRepo {
    fun save(user: User) {
    }
}

// EX 1 - repetitive code
fun save(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException()
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException()
    }
    UserRepo.save(user)
}

// EX 2 - local function
fun save2(user: User) {
    fun validate(user: User, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("cannot save ${user.name}, empty $fieldName")
        }
    }

    validate(user, user.name, "name")
    validate(user, user.address, "nddress")
    UserRepo.save(user)
}

// EX 3 - local functions have access to the enclosing scope
fun save3(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("cannot save ${user.name}, empty $fieldName")
        }
    }

    validate(user.name, "name")
    validate(user.address, "address")
    UserRepo.save(user)
}

// EX 4 - Extension function
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("cannot save ${this.name}, empty $fieldName")
        }
    }

    validate(name, "name")
    validate(address, "address")
}

fun save4(user: User) {
    user.validateBeforeSave()
    UserRepo.save((user))
}