package Chapter6

import Chapter6.java.DataParser
import Chapter6.java.FileContentProcessor
import Chapter6.java.Person
import java.io.File

// when working with collections in Java / Kotlin projects, we need to be aware of a few things.
// We are responsible for checking the correct collection type. Specifically
// - Is the collection nullable?
// - Is the collection mutable?
// - Are the elements in the collection mutable?

// see in the two implementations of an interface defined in Java how these considerations apply
class FileIndexer : FileContentProcessor {
    override fun processContents(path: File, binaryContent: ByteArray?, textContent: List<String>?) {
    }
}

class PersonParser : DataParser<Person> {
    override fun parseData(input: String, output: MutableList<Person>, errors: MutableList<String?>) {
    }
}