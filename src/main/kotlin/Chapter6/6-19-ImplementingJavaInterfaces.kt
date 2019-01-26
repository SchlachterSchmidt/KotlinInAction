package Chapter6

import Chapter6.java.StringProcessor


// Both implementations of the interface defined in Java are valid
class StringPrinter : StringProcessor {
    override fun process(value: String) {
        println(value)
    }
}

class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        if (value != null) println(value)
    }
}
