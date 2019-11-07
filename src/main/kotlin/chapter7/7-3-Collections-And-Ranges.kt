package chapter7

import java.time.LocalDate

operator fun Point.get(index: Int): Int {
    return when(index) {
        0 -> x
        1 -> y
        else ->  throw IndexOutOfBoundsException("Invalid point coordinate")
    }
}

operator fun Line.get(x: Int, y: Int): Point {
    return Point(x, y)
}

data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else -> throw java.lang.IndexOutOfBoundsException("invalid point index")
    }
}

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(point: Point): Boolean {
    return point.x in upperLeft.x until lowerRight.x &&
            point.y in upperLeft.y until lowerRight.y
}

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object : Iterator<LocalDate> {
        var current = start
        override fun hasNext(): Boolean =
            current <= endInclusive

        override fun next() = current.apply {
            current = plusDays(1)
        }
    }

fun main() {
    val p1 = Point(3, 4)

    // by supplying the get method, we gain access to the indexing operator []
    println(p1[1])

    val p2 = Point(5, 6)
    val line1 = Line(p1, p2)
    // we can also define a get method with multiple parameters (disregarding that this operation make no logical sense.. just to demo what can be done)
    println(line1[2, 3])

    val mp1 = MutablePoint(4, 5)
    // can also assign values by implementing a set method which takes two arguments: the index and the value, which are automatically taken from the
    // index in [] and the value from the right hand side of the assignment operator. This also works with more than 2 parameters where the last is always the new value
    mp1[1] = 9
    println(mp1)

    val rectangle = Rectangle(Point(0, 0), Point(50, 50))
    // the object on the right hand side of in is what the contains function is called onl and the left becomes the argument
    println(Point(25, 25) in rectangle)

    //anything that implements Comparable can be used to create a range using the .. operator.
    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)
    println(now.plusWeeks(1) in vacation)

    // to be able to use the concise for(x in y) syntax, we just need to implement the iterator method, and override the next and hasNext methods
    val newYear = LocalDate.ofYearDay(2017, 1)
    val daysOff = newYear.minusDays(1)..newYear
    for (dayOff in daysOff) { println(dayOff) }
}