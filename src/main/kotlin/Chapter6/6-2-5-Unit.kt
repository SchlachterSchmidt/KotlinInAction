package Chapter6

fun main() {
    // unit retrun type is implicit:
    fun f1() {

    }

    // is equivalent
    fun f2(): Unit {

    }

    // unit is different from void in that Unit is a proper type:
    class NoResultProcessor : Processor<Unit> {
        override fun process() { // we do not need to return Unit explicitly
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }


}


interface Processor<T> {
    fun process(): T

}