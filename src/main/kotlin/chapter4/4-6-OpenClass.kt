package chapter4

open class RichButton : Clickable {

    final override fun click() = println("You can't override further, even though override implies open")

    fun disabled() = println("I am final by default. You can't override me even if my class is open")

    open fun animate() = println("I am open, override me")
}

class RicherButton : RichButton() {

    override fun animate() = println("I am the only method of RichButton you can override")
}