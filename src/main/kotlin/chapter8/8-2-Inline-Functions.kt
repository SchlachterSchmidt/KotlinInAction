package chapter8

import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

fun main() {
    // lambdas are compiled to instances of anonymous classes under the hood. So every time we use a lambda, there is a certain overhead associated with it.
    // we can reduce this overhead by using inline functions, which are inserted directly as is into the bytecode

    val lock = ReentrantLock()
    synchronized(lock) {
        println("Action")
    }
    // in this example, the lambda will be inserted into the bytecode of the synchronized function directly (not only the function marked as inline!)

    // If the lambda's code is not available at the site where the inlined function is called it cannot be inlined and is called through the usual anonymous class functionality
    class LockOwner(val lock: Lock) {
        fun funUnderLock(body: () -> Unit) {
            synchronized(lock, body)
        }
    }
    // in this example, the lambda is not inlined.
    // Not every function with lambdas can be inlined. If the parameter is stored somewhere we cannot inline it, since there is no object to store it. The compiler will complain

    // Kotlin has a standard library function called withLock that allows us to use the same concise lambda syntax. It's implementation is similar to the synchronized example above
    lock.withLock {
        println("i am under a lock")
    }

    // Similar to the try-with-resources in Java, we have the use function available in the standard library
    fun readFirstLineFromFile(path: String): String {
        BufferedReader(FileReader(path)).use { br ->
            return br.readLine()
        }
    }
    // use can be called on a closable and ensures that the resource is closed after use.

}