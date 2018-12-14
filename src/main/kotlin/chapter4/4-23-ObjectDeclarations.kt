package chapter4

import java.io.File

// objects are constructed at declaration
object CaseInsenitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

// objects can be nested inside classes. In that case, all instances of the class share access to the same object.
// See also companion object
data class Person(val name: String, val age: Int = 0) {
    object PersonComparator : Comparator<Person> {
        override fun compare(person1: Person, person2: Person): Int {
            return person1.name.compareTo(person2.name)
        }
    }

    fun someFun() = "I am having fun, but PersonComparator can't see me"
}

// Companion Objects are a place for static members and factory methods
// they have explicit access to private methods and members of their classes
class ImprovedUser private constructor(val name: String) {
    // companion objects may have a name, but it is not required
    companion object Creator {
        fun newFaceBookUser(id: Int) = ImprovedUser(id.toString())

        fun newEmailUser(email: String) = ImprovedUser(email.substringBefore("@"))
    }
}

fun main(args: Array<String>) {

    println(CaseInsenitiveFileComparator.compare(File("/User"), File("/user")))

    val files = listOf<File>(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsenitiveFileComparator))

    val people = listOf(Person("Alex"), Person("Chad"), Person("Bob"))
    println(people.sortedWith(Person.PersonComparator))

    val fbUser = ImprovedUser.Creator.newFaceBookUser(123456)
    val emailUser = ImprovedUser.newEmailUser("asd@dsa.com")
}