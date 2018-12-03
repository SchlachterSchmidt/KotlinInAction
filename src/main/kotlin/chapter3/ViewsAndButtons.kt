package chapter3

open class View {
    open fun onClick() = println("view clicked")
}

class Button : View() {
    override fun onClick() = println("button clicked")
}

fun View.showOff() = println("view show off")
fun Button.showOff() = println("button show off")

fun main(args: Array<String>) {
    val view: View = Button()

    view.onClick()
    view.showOff()
}