package chapter3

fun main(args: Array<String>) {

    val art = """
        .| //
        .|//
        .|/ \
    """.trimMargin(".")

    println(art)

    val price = """${'$'}99.99"""

    println(price)
}