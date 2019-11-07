package chapter7

import java.math.BigDecimal

data class Point(val x: Int, val y: Int) : Comparable<Point> {
    operator fun plus(other: Point) = Point(x+other.x, y+other.y)

    override fun compareTo(other: Point): Int {
        return compareValuesBy(this, other, Point::x, Point::y)
    }
}

operator fun Point.minus(other: Point) = Point(this.x-other.x, this.y-other.y)

operator fun Point.times(scale: Double) = Point((this.x * scale).toInt(), (this.y * scale).toInt())

operator fun <T> MutableCollection<T>.plusAssign(element: T) {
    this.add(element)
}

operator fun Point.unaryMinus() = Point((this.x * -1), (this.y * -1))

operator fun BigDecimal.inc() = this + BigDecimal.ONE

fun main() {
    val p1 = Point(1, 3)
    val p2 = Point(3, 5)

    // By defining the plus function with the operator keyword, we can us ethe + operarator. The + functions calls plus under the hood
    println(p1 + p2)

    // by defining operator functions as extension functions, we can also add them to existing code, such as Java
    println(p1 - p2)
    // we can define *, /, +, -, and %. the order of operation remains the same as in standard algebra

    // we can multiply like this, but be aware that in order to preserve commutativity, we must define another operator fun for Double
    println(p1 * 2.5)

    // to use compound assignment operators like += we don't need to do anything in particular
    var p3 = Point(5, 2)
    p3 += p1
    println(p3)

    // we may want to change this behaviour, for example to use += to add an element to a mutable collection. In that case we need to supply the plusAssign method,
    // as well as the minusAssign, timesAssign etc.. that returns Unit
    val mutableList = mutableListOf<Point>()
    mutableList += p1
    mutableList += p2
    mutableList += p3
    println(mutableList)

    // the kotlin standard library works in a way that +, - etc always returns a new collections, whereas +=, -= always operate on a mutable collection.
    val list = arrayListOf(1, 2)
    list += 3 // changes the list
    val newList = list + listOf(3, 4, 5) // returns a new list



    // We can overload unary operators such as -p1, !p1 as well:
    println(-p1)

    // ++ and -- support pre as well as post increment
    var bd = BigDecimal.ZERO
    println(bd++)
    println(++bd)
}