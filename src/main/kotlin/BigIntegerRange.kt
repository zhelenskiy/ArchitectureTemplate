import java.math.BigInteger

fun range(first: Number, last: Number?): BigIntegerRange = range(first.toBigInteger(), last?.toBigInteger())
fun range(first: BigInteger, last: BigInteger?): BigIntegerRange =
    when {
        last != null && first > last -> EmptyRange
        first == last -> SingleRange(first)
        else -> BigIntegerRange(first, last)
    }

fun range(r: IntRange): BigIntegerRange = range(r.first, r.last)
fun range(r: LongRange): BigIntegerRange = range(r.first, r.last)

open class BigIntegerRange internal constructor(first: BigInteger, last: BigInteger?) :
    BigIntegerProgression(first, last, BigInteger.ONE), Comparable<BigIntegerRange> {

    infix fun intersect(other: BigIntegerRange): BigIntegerRange = range(
        maxOf(this.first, other.first),
        if (this.last != null && other.last != null) minOf(this.last, other.last) else this.last ?: other.last
    )

    infix fun intersect(other: EmptyRange) = EmptyRange

    infix fun intersect(other: IntRange): BigIntegerRange = when (val progression = progression(other)) {
        is BigIntegerRange -> intersect(progression)
        else -> EmptyRange
    }

    infix fun intersect(other: LongRange): BigIntegerRange = when (val progression = progression(other)) {
        is BigIntegerRange -> intersect(progression)
        else -> EmptyRange
    }

    infix fun IntRange.intersect(range: BigIntegerRange): BigIntegerRange =
        (progression(this) as? BigIntegerRange)?.intersect(range) ?: EmptyRange

    infix fun LongRange.intersect(range: BigIntegerRange): BigIntegerRange =
        (progression(this) as? BigIntegerRange)?.intersect(range) ?: EmptyRange

    infix fun union(other: BigIntegerRange): Sequence<BigInteger> = when {
        this.isInfinite() && other.isInfinite() -> BigIntegerRange(minOf(this.first, other.first), null)
        this.isInfinite() && other.last!! in this -> BigIntegerRange(minOf(this.first, other.first), null)
        other.isInfinite() && this.last!! in other -> BigIntegerRange(minOf(this.first, other.first), null)
        this.last != null && other.last != null && (this.last in other || other.last in this) -> BigIntegerRange(
            minOf(this.first, other.first), maxOf(this.last, other.last)
        )
        this.first < other.first -> this + other
        else -> other + first
    }

    infix fun union(other: IntRange): Sequence<BigInteger> = union(range(other))
    infix fun union(other: LongRange): Sequence<BigInteger> = union(range(other))
    infix fun IntRange.union(other: BigIntegerRange): Sequence<BigInteger> = range(this).union(other)
    infix fun LongRange.union(other: BigIntegerRange): Sequence<BigInteger> = range(this).union(other)

    operator fun minus(other: BigIntegerRange): Sequence<BigInteger> = when {
        other.last == null -> this.first until if (this.last == null) other.first else minOf(this.last, other.first)
        this.last == null && this.first >= other.first -> other.last.inc()..Infinity
        this.last == null -> (this.first until other.first) + (other.last.inc()..Infinity)
        else -> asSequence() - other
    }

    operator fun minus(other: IntRange) = minus(range(other))
    operator fun minus(other: LongRange) = minus(range(other))

    operator fun IntRange.minus(other: BigIntegerRange) = range(this) - other
    operator fun LongRange.minus(other: BigIntegerRange) = range(this) - other

    infix fun subtract(other: BigIntegerRange) = minus(other)
    infix fun subtract(other: IntRange) = minus(range(other))
    infix fun subtract(other: LongRange) = minus(range(other))
    infix fun IntRange.subtract(other: BigIntegerRange) = range(this) - other
    infix fun LongRange.subtract(other: BigIntegerRange) = range(this) - other

    /**
     * Compares this object with the specified object for order. Returns zero if this object is equal
     * to the specified [other] object, a negative number if it's less than [other], or a positive number
     * if it's greater than [other].
     */
    override fun compareTo(other: BigIntegerRange): Int =
        this.first.compareTo(other.first).takeIf { it != 0 } ?: when {
            this.last == null && other.last == null -> 0
            this.last == null -> 1
            other.last == null -> -1
            else -> this.last.compareTo(other.last)
        }
}