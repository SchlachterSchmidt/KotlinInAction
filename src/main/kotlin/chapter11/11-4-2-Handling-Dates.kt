package chapter11

import java.time.LocalDate
import java.time.Period

fun main() {

    val yesterday = 1.days.ago
    val tomorrow = 1.days.fromNow
}

val Int.days: Period
    get() = Period.ofDays(this)

val Period.ago: LocalDate
    get() = LocalDate.now() - this

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this

