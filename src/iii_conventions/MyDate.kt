package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return (year.compareTo(other.year) * 100) +
                (month.compareTo(other.month) * 10) +
                (dayOfMonth.compareTo(other.dayOfMonth))
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    operator fun contains(d: MyDate): Boolean {
        return d > start && d <= endInclusive
    }

    override operator fun iterator(): Iterator<MyDate> = MyDateIterator(this)
}

class MyDateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current = dateRange.start
    override fun hasNext(): Boolean = current <= dateRange.endInclusive

    override fun next(): MyDate {
        val next = current
        current = current.addTimeIntervals(TimeInterval.DAY, 1)
        return next
    }

}
