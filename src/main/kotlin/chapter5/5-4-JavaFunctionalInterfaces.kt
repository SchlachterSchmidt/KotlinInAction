package chapter5

import chapter5.java.Button
import chapter5.java.OnClickListener
import chapter5.java.PostponeComputation
import chapter5.java.View

// working with Button, OnClickListener and View as Java interfaces and Kotlin lambdas

fun main(args: Array<String>) {
    val button = Button()

    // using a lambda. this works because the OnClickListener interface has only one abstract method (SAM or Functional Interface)
    // --> Kotlin supports function types, so kotlin classes should be designed to take lambdas and not functional interfaces
    button.setOnClickListener { v: View? -> v }

    button.setOnClickListener(object : OnClickListener {
        override fun onClick(v: View?) {
            // this is essentially what the compiler can do for us! Instead of creating an object that implements the
            // OnClickListener interface and override the only method by hand, the lambda expression above leaves this to the compiler
        }
    })

    // this example works the same way. The compiler will convert the below to an instance of an anonymous class implementing
    // runnable and use the lambda as the method for the single, abstract method in runnable -> run()
    val postponeComputation = PostponeComputation()
    postponeComputation.postponeComputation(1000) { println(42) }

    // benefit of using lambdas over objects is that there is only one instance created if it doesn't access any variables
    // so a corresponding implementation using objects would be
    val runnable = Runnable { println(42) }
    postponeComputation.postponeComputation(1000, runnable)

    // using SAM constructor to wrap a lambda, for example when a method returns an instance of a functional interface, you can't
    // return a lambda directly
    fun createAllDoneRunnable(): Runnable {
        return Runnable { println("All Done") }
    }
    createAllDoneRunnable().run()

    // in this example, using a SAM constructor to reuse a listener for several buttons
    val listener = OnClickListener { v: View? ->
        val text = when (v?.id) {
            0 -> "button 0"
            1 -> "button 1"
            else -> "unknown"
        }
        println(text)
    }
}