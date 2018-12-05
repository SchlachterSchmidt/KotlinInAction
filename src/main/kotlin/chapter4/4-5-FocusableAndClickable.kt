package chapter4

class FocusableAndClickable : Focusable, Clickable {
    override fun click() = println("click")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}