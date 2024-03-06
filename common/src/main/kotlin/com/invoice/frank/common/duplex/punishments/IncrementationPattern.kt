package com.invoice.frank.common.duplex.punishments

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

enum class IncrementationPattern (
    val times: Array<Duration>
) {
    NONE(arrayOf()),
    TRIVIAL(arrayOf(
        minutes(15),
        minutes(30),
        hours(1),
        hours(5),
        hours(12),
        days(1),
        days(5),
        days(10),
        weeks(3),
        months(1),
        months(3),
        years(1)
    )),
    LOW(arrayOf(
        hours(1),
        hours(3),
        hours(6),
        days(1),
        days(3),
        days(7),
        weeks(2),
        weeks(4),
        months(1),
        months(3),
        years(1)
    )),
    MEDIUM(arrayOf(
        days(1),
        days(3),
        days(7),
        weeks(2),
        weeks(4),
        months(1),
        months(3),
        years(1)
    )),
    HIGH(arrayOf(
        days(7),
        weeks(2),
        weeks(4),
        months(1),
        months(3),
        years(1)
    )),
    SEVERE(arrayOf(
        months(1),
        months(3),
        years(1),
        years(3)
    )),
    EXTREME(arrayOf(
        years(365)
    ))
}

fun minutes(minutes: Long): Duration {
    return minutes.toDuration(DurationUnit.MINUTES)
}
fun hours(hours: Long): Duration {
    return hours.toDuration(DurationUnit.HOURS)
}
fun days(days: Long): Duration {
    return days.toDuration(DurationUnit.DAYS)
}
fun weeks(weeks: Long): Duration {
    return (weeks * 7).toDuration(DurationUnit.DAYS)
}
fun months(months: Long): Duration {
    return (months * 30).toDuration(DurationUnit.DAYS)
}
fun years(years: Long): Duration {
    return (years * 365).toDuration(DurationUnit.DAYS)
}