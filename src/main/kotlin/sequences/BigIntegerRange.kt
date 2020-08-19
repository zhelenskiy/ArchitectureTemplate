package sequences

import extensions.*
import java.math.BigInteger

/**
 * Abstract function that constructs [BigIntegerRange] and its inheritors.
 * @return
 * * [EmptyRange] when built [BigIntegerRange] is empty
 * * [SingleRange] when it is single
 * * Pure [BigIntegerRange] otherwise
 *
 * @param first [Number] to be converted to [BigInteger] to be `first`
 * @param last
 * * When is null, Double.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, the [BigIntegerRange] is infinite
 * * Otherwise, it is a [Number] to be converted to [BigInteger] to be `first`
 * @see IntRange
 * @see LongRange
 */
fun range(first: Number, last: Number?): BigIntegerRange = range(
    first.toBigInteger(),
    last?.takeUnless { it == Double.POSITIVE_INFINITY || it == Float.POSITIVE_INFINITY }
        ?.toBigInteger()
)

/**
 * Abstract function that constructs [BigIntegerRange] and its inheritors.
 * @return
 * * [EmptyRange] when built [BigIntegerRange] is empty
 * * [SingleRange] when it is single
 * * Pure [BigIntegerRange] otherwise
 *
 * This function is the main way to construct instances of [BigIntegerRange] and its inheritors.
 *
 * @param first is to be `first`
 * @param last
 * * When is null, Double.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, the [BigIntegerRange] is infinite
 * * Otherwise, it is a [Number] to be converted to [BigInteger] to be `first`
 * @see IntRange
 * @see LongRange
 */
fun range(first: BigInteger, last: BigInteger?): BigIntegerRange = when {
    last != null && first > last -> EmptyRange
    first == last -> SingleRange(first)
    else -> BigIntegerRange(first, last)
}

/**
 * Constructs [BigIntegerProgression] with the `first` and the `last` of the [range].
 */
fun range(range: BigIntegerRange): BigIntegerRange = range

/**
 * Constructs [BigIntegerProgression] with the converted to [BigInteger] `first` and `last` of the [range].
 */
fun range(range: IntRange): BigIntegerRange = range(range.first, range.last)

/**
 * Constructs [BigIntegerProgression] with the converted to [BigInteger] `first` and `last` of the [range].
 */
fun range(range: LongRange): BigIntegerRange = range(range.first, range.last)

/**
 * [BigIntegerProgression] with `step` = 1.
 *
 * Undocumented methods are just to specify return type ([BigIntegerRange]) statically.
 * @constructor Should be used only used inside abstract function [range] because otherwise wrong instance (basic [BigIntegerRange] instead of its ancestor) may be used and the invariants brake
 * @property first The first value of the progression
 * @param toInclusive
 * * When is `null`, the progression is infinite
 * * The last number otherwise
 * @see IntRange
 * @see LongRange
 */
open class BigIntegerRange internal constructor(first: BigInteger, toInclusive: BigInteger?) :
    BigIntegerProgression(first, toInclusive, BigInteger.ONE), Comparable<BigIntegerRange> {

    /**
     * Finds maximal [BigIntegerRange] that is contained by both [BigIntegerRange]s yielding O(1) time capacity.
     */
    open infix fun intersect(other: BigIntegerRange): BigIntegerRange = range(
        maxOf(this.first, other.first),
        if (this.last != null && other.last != null) minOf(this.last, other.last) else this.last ?: other.last
    )

    /**
     * Finds maximal [BigIntegerRange] that is contained by both [BigIntegerRange]s yielding O(1) time capacity.
     *
     * The method is to specify return type statically.
     */
    infix fun intersect(@Suppress("UNUSED_PARAMETER") other: EmptyRange) = EmptyRange

    /**
     * Finds maximal [BigIntegerRange] that is contained by both [BigIntegerRange] and [IntRange] converted to [BigIntegerRange] yielding O(1) time capacity.
     */
    open infix fun intersect(other: IntRange): BigIntegerRange = intersect(range(other))

    /**
     * Finds maximal [BigIntegerRange] that is contained by both [BigIntegerRange] and [LongRange] converted to [BigIntegerRange] yielding O(1) time capacity.
     */
    open infix fun intersect(other: LongRange): BigIntegerRange = intersect(range(other))

    /**
     * Finds [Sequence] that contains such elements of the [range][BigIntegerRange] excluding those that are in [other] yielding O(1) time capacity.
     */
    operator fun minus(other: BigIntegerRange): Sequence<BigInteger> = when {
        this.isEmpty() || other.isEmpty() || other intersect this == EmptyRange -> this
        other.last == null -> this.first until if (this.last == null) other.first else minOf(this.last, other.first)
        this.last == null && this.first >= other.first -> other.last.inc()..Infinity
        this.last == null -> (this.first until other.first) + (other.last.inc()..Infinity)
        else -> asSequence() - other
    }

    /**
     * Finds [Sequence] that contains such elements of the [range][BigIntegerRange] excluding those that are in [other] yielding O(1) time capacity.
     */
    operator fun minus(other: IntRange) = minus(range(other))

    /**
     * Finds [Sequence] that contains such elements of the [range][BigIntegerRange] excluding those that are in [other] yielding O(1) time capacity.
     */
    operator fun minus(other: LongRange) = minus(range(other))

    /**
     * Finds [Sequence] that contains such elements of the [range][IntRange] excluding those that are in [other] yielding O(1) time capacity.
     */
    operator fun IntRange.minus(other: BigIntegerRange) = range(this) - other

    /**
     * Finds [Sequence] that contains such elements of the [range][LongRange] excluding those that are in [other] yielding O(1) time capacity.
     */
    operator fun LongRange.minus(other: BigIntegerRange) = range(this) - other

    /**
     * Finds [Sequence] that contains such elements of the [range][BigIntegerRange] excluding those that are in [other] yielding O(1) time capacity.
     */
    infix fun subtract(other: BigIntegerRange) = minus(other)

    /**
     * Finds [Sequence] that contains such elements of the [range][BigIntegerRange] excluding those that are in [other] yielding O(1) time capacity.
     */
    infix fun subtract(other: IntRange) = minus(range(other))

    /**
     * Finds [Sequence] that contains such elements of the [range][BigIntegerRange] excluding those that are in [other] yielding O(1) time capacity.
     */
    infix fun subtract(other: LongRange) = minus(range(other))

    /**
     * Compares this object with the specified object for order. Returns zero if this object is equal
     * to the specified [other] object, a negative number if it's less than [other], or a positive number
     * if it's greater than [other].
     *
     * Compares by `first`, then by `last` (assuming +∞ = +∞ > [BigInteger])
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