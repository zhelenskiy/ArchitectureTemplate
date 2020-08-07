import java.math.BigInteger

fun range(first: Number, last: Number?): BigIntegerRange = range(first.toBigInteger(), last?.toBigInteger())
fun range(first: BigInteger, last: BigInteger?): BigIntegerRange = when {
    last != null && first > last -> EmptyRange
    first == last -> SingleRange(first)
    else -> BigIntegerRange(first, last)
}

fun range(r: IntRange): BigIntegerRange = range(r.first, r.last)
fun range(r: LongRange): BigIntegerRange = range(r.first, r.last)

open class BigIntegerRange internal constructor(first: BigInteger, last: BigInteger?) :
    BigIntegerProgression(first, last, BigInteger.ONE), Comparable<BigIntegerRange> {

    open infix fun intersect(other: BigIntegerRange): BigIntegerRange = range(
        maxOf(this.first, other.first),
        if (this.last != null && other.last != null) minOf(this.last, other.last) else this.last ?: other.last
    )

    infix fun intersect(@Suppress("UNUSED_PARAMETER") other: EmptyRange) = EmptyRange

    open infix fun intersect(other: IntRange): BigIntegerRange = intersect(range(other))

    open infix fun intersect(other: LongRange): BigIntegerRange = intersect(range(other))

    operator fun minus(other: BigIntegerRange): Sequence<BigInteger> = when {
        this.isEmpty() || other.isEmpty() || other intersect this == EmptyRange -> this
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

    override fun extend(n: Number) = super.extend(n) as BigIntegerRange
    override fun shl(number: Number) = super.shl(number) as BigIntegerRange
    override fun shr(number: Number) = super.shr(number) as BigIntegerRange
    override fun drop(n: Number) = super.drop(n) as BigIntegerRange
    override fun take(n: Number) = super.take(n) as BigIntegerRange
}