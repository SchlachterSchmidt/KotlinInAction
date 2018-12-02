package chapter3

fun main(args: Array<String>) {

    println("123.456-789".split(Regex("\\.|-")))
    println("123.456-789".split(".", "-"))

    val path = "Users/pgieschen/kotlin/chapter1.kt"

    val directory = path.substringBeforeLast("/")
    val fileName = path.substringAfterLast("/").substringBefore(".")
    val extension = path.substringAfter(".")

    println("$directory -> $fileName -> $extension")

    val regex = """(.+)/(.+)\.(.+)""".toRegex()

    val matches = regex.matchEntire(path)
    if (matches != null) {
        val (dir, pth, ext) = matches.destructured
        println("$dir ~> $pth ~> $ext")
    }
}