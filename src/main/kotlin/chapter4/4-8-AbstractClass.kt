package chapter4

abstract class Animated {
    abstract fun animate()

    open fun stopAnimation() = println("stopping animation")

    fun animateTwice() {
        animate()
        animate()
    }
}

class AnimatedDog : Animated() {
    override fun animate() {
        println("walking and peeing on that hydrant over yonder")
    }

    override fun stopAnimation() = println("standing still with raised tail")
}