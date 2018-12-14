package chapter4

interface SuperDuper {
    fun awesome(): String
}

open class RealSwell
class SweetStuff {

    fun addSuperDuperThings(realSwell: RealSwell) {
    }
}

class TopNotch {
    val somethingEpic = 1
    fun realCool(asd: SuperDuper) = asd.awesome()

    fun addTwo(sweetStuff: SweetStuff) {
        sweetStuff.addSuperDuperThings(object : RealSwell() { })
    }
}

fun main(args: Array<String>) {

    val top = TopNotch()
    // rather than creating an anonymous object, we can use the object notation here
    // they are not singletons, however
    println(top.realCool(
        object : SuperDuper {
            override fun awesome(): String = "Whooo"
        }
    ))
}
