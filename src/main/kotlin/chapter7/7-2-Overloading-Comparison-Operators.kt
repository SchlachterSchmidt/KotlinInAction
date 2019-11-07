package chapter7

class Line(val start: Point, val end: Point) : Comparable<Line> {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Line) return false
        return this.start == other.start && this.end == other.end
    }

    override fun compareTo(other: Line): Int {
        return compareValuesBy(this, other, Line::start, Line::end)
    }
}

fun main() {
    val p1 = Point(5, 5)
    val p2 = Point(10, 10)
    val p3 = Point(15, 15)

    val line1 = Line(p1, p2)
    val line2 = Line(p1, p2)
    val line3 = Line(p1, p3)

    // by implementing the equals method, we gain access to the == operator
    println(line1 == line2)

    // as well as the != operator. Unlike arithmetic operators, we cannot define equality checks as extension functions
    println(line1 != line2)

    // we can also implement the compareTo function which must return Int. Unlike in Java, this means we can use <, > etc on non-primitives
    println(line1 < line3)
}