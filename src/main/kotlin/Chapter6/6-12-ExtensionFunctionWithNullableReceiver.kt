package Chapter6

fun main() {

    fun String?.isNullOrBlank(): Boolean = this == null || this.isBlank()

    var sth: String? = null

    // even if sth is null, the safe call operator is not needed in this case
    println(sth.isNullOrBlank())

    sth = ""
    println(sth.isNullOrBlank())
}