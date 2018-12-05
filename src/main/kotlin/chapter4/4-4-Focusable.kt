package chapter4

interface Focusable {
    fun setFocus(focus: Boolean) =
        println("I ${if (focus) "got" else "lost"} focus")
    fun showOff() = println("I am focusable")
}