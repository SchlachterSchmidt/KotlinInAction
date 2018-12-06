package chapter4

import java.io.Serializable

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreCurrentState(state: State)
}

public class UIElement : View {

    override fun getCurrentState(): State = ElementState()

    override fun restoreCurrentState(state: State) {
        println("restring state")
    }

    // this is a nested class, it has no ref to the enclosing class
    class ElementState : State {
        // implementation of state
    }

    // this is an inner class, it has a ref to the enclosing class
    inner class ElementStateInner : State {
        fun getOuterRef(): UIElement = this@UIElement
    }
}