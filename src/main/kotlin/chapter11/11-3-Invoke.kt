package chapter11

fun main() {
    // ex 1
    val bavarianGreeter = Greeter("Servus")
    // object callable as function. This is compiled to bavarianGreeter.invoke("Joe)
    bavarianGreeter("Joe")

    // ex 2
    val issue1 = Issue(id="IDEA-001", project ="IDEA", type=Type.BUG, priority=Priority.CRITICAL, description="Save settings failed")
    val issue2 = Issue(id="KT-002", project="KOTLIN", type=Type.FEATURE, priority=Priority.NORMAL, description = "convert to with/apply")
    val predicate = ImportantIssuesPredicate("IDEA")

    for (issue in listOf(issue1, issue2).filter(predicate)) {
        println(issue.id)
    }

    // ex 3 - building a DSL like gradle
    val dependencies = DependencyHander()

    dependencies.compile("org.jetbrains.kotlin:kotlin-stdlib:1.3.0")
    dependencies {
        compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
    }
 }

// Ex 1
class Greeter(private val greeting: String) {
    // overriding the invoke convention allows us to call the object as a function, similar to the get convention discussed in chapter 7
    operator fun invoke(name: String) {
        println("$greeting, $name")
    }
}

// Ex 2
data class Issue(val id: String, val project: String, val type: Type, val priority: Priority, val description: String)

// extending the function type like an interface requires us to provide the invoke method
// under the hood, this is how all lambdas work under the hood!
class ImportantIssuesPredicate(private val project: String) : (Issue) -> Boolean {
    override fun invoke(issue: Issue): Boolean {
        return issue.project == project && issue.isImportant()
    }

    private fun Issue.isImportant(): Boolean {
        return type == Type.BUG &&
                (priority == Priority.MAJOR || priority == Priority.CRITICAL)
    }
}

enum class Type {
    BUG, FEATURE
}

enum class Priority {
    LOW, NORMAL, MAJOR, CRITICAL
}

// Ex 3
class DependencyHander {
    fun compile(coordinate: String) {
        println("Added dependency on $coordinate")
    }

    operator fun invoke(body: DependencyHander.() -> Unit) {
        body()
    }
}

