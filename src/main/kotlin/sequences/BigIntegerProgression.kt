package sequences

import extensions.*
import sequences.EmptyRange.toString
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger
import java.util.SortedSet
import kotlin.Comparator
import kotlin.NoSuchElementException
import kotlin.collections.HashSet
import kotlin.math.sign

/**
 * Abstract function that constructs [BigIntegerProgression] and its inheritors.
 * @return
 * * [EmptyRange] when built [BigIntegerProgression] is empty
 * * [SingleRange] when it is single
 * * [BigIntegerRange] when `step` is 1 and there are more than 1 elements
 * * Pure [BigIntegerProgression] otherwise
 *
 * @param first The first value of the progression (unless it is empty)
 * @param step Nonnull value to be added to calculate the next element
 * @param toInclusive
 * * When is `null`, the progression is infinite
 * * When [step] is positive, the top border number
 * * When [step] is negative, the bottom border number
 * @throws IllegalArgumentException When [step] is 0
 * @see IntProgression
 * @see LongProgression
 *///todo add samples
fun progression(first: BigInteger, toInclusive: BigInteger?, step: BigInteger = BigInteger.ONE): BigIntegerProgression {
    val simple = BigIntegerProgression(first, toInclusive, step)
    return when {
        simple.isEmpty() -> EmptyRange
        simple.isSingle() -> SingleRange(simple.first)
        step == BigInteger.ONE -> BigIntegerRange(first, toInclusive)
        else -> simple
    }
}

/**
 * Constructs [BigIntegerProgression] with the same `first`, `last` and `step` as the [progression].
 */
fun progression(progression: BigIntegerProgression): BigIntegerProgression = progression

/**
 * Constructs [BigIntegerProgression] with the converted to [BigInteger] `first`, `last` and `step` of the [progression].
 */
fun progression(progression: IntProgression): BigIntegerProgression = progression(
    progression.first.toBigInteger(),
    progression.last.toBigInteger(),
    progression.step.toBigInteger()
)

/**
 * Constructs [BigIntegerProgression] with the converted to [BigInteger] `first`, `last` and `step` of the [progression].
 */
fun progression(progression: LongProgression): BigIntegerProgression = progression(
    progression.first.toBigInteger(),
    progression.last.toBigInteger(),
    progression.step.toBigInteger()
)

/**
 * Abstract function that constructs [BigIntegerProgression] and its inheritors.
 * @return
 * * [EmptyRange] when built [BigIntegerProgression] is empty
 * * [SingleRange] when it is single
 * * [BigIntegerRange] when `step` is 1 and there are more than 1 elements
 * * Pure [BigIntegerProgression] otherwise
 *
 * This function is the main way to construct instances of [BigIntegerProgression] and its inheritors.
 *
 * @param first The first value (after conversation to [BigInteger]) of the progression (unless it is empty)
 * @param step Nonnull value (after conversation to [BigInteger]) to be added to calculate the next element
 * @param toInclusive
 * * When is `null`, the progression is infinite
 * * When is `Double.POSITIVE_INFINITY` or `Float.POSITIVE_INFINITY` and [step] is positive, the progression is infinite
 * * When is `Double.NEGATIVE_INFINITY` or `Float.NEGATIVE_INFINITY` and [step] is negative, the progression is infinite
 * * When converted to [BigInteger] [step] is positive, the top border number
 * * When converted to [BigInteger] [step] is negative, the bottom border number
 * @throws IllegalArgumentException When [step] is 0
 * @see IntProgression
 * @see LongProgression
 *///todo add samples
fun progression(first: Number, toInclusive: Number?, step: Number): BigIntegerProgression = progression(
    first.toBigInteger(),
    toInclusive
        .takeUnless {
            it is Double && it.isInfinite() && it.sign.toInt() == step.toBigInteger().signum()
                    || it is Float && it.isInfinite() && it.sign.toInt() == step.toBigInteger().signum()
        }
        ?.toBigInteger(),
    step.toBigInteger()
)

/**
 * Arithmetic progression of [BigInteger]s with nonnull `step`.
 * @constructor Should be used only used inside abstract function [progression] because otherwise wrong instance (basic [BigIntegerProgression] instead of its ancestor) may be used and the invariants brake
 * @property first The first value of the progression
 * @property step Nonnull value to be added to calculate the next element
 * @param toInclusive
 * * When is `null`, the progression is infinite
 * * When `step` is positive, the top border number
 * * When `step` is negative, the bottom border number
 * @throws IllegalArgumentException When `step` is 0
 * @see IntProgression
 * @see LongProgression
 */
open class BigIntegerProgression internal constructor(
    val first: BigInteger,
    toInclusive: BigInteger?,
    val step: BigInteger
) : Serializable, Sequence<BigInteger> {
    init {
        if (step == BigInteger.ZERO) throw IllegalArgumentException("Step must be non-zero.")
    }

    /**
     * The last element when possible.
     *
     * More precisely:
     * * `null` if the the [progression][BigIntegerProgression] is infinite
     * * element before `first`, when the [progression][BigIntegerProgression] is empty
     * * the last element otherwise
     */
    val last: BigInteger? = when {
        toInclusive == null -> null
        step > 0 && toInclusive < first -> first - step
        step < 0 && toInclusive > first -> first - step
        step > 0 -> first + (toInclusive - first) / step * step
        step < 0 -> first + (first - toInclusive) / -step * step
        else -> throw IllegalStateException("Can not happen")
    }

    /**
     * Indicates if the [BigIntegerProgression] is empty.
     */
    fun isEmpty(): Boolean = last == first - step

    /**
     * Indicates if the [BigIntegerProgression] is infinite.
     */
    fun isInfinite(): Boolean = last == null

    /**
     * Indicates if the [BigIntegerProgression] is finite.
     */
    fun isFinite(): Boolean = !isInfinite()

    /**
     * Indicates if the [BigIntegerProgression] is not empty.
     */
    fun isNotEmpty(): Boolean = !isEmpty()

    /**
     * Finds count of the sequence (yields O(1) time capacity assuming [BigInteger] arithmetic operations to take O(1) time).
     * @return
     * * `null` when the [sequence][BigIntegerProgression] is infinite
     * * nonnull number of elements otherwise
     */
    fun count(): BigInteger? = when {
        isInfinite() -> null
        isEmpty() -> BigInteger.ZERO
        else -> (last!! - first) / step + 1
    }

    /**
     * Indicates if the [BigIntegerProgression] contains exactly 1 element.
     */
    fun isSingle(): Boolean = count() == BigInteger.ONE

    /**
     * Two [BigIntegerProgression]s are equal when they are equal as sequences.
     *
     * This means:
     * * If [other] is not [BigIntegerProgression], equality is checked as for [Sequence]
     * * Empty [BigIntegerProgression]s are equal only to each other
     * * Single ranges are equal when their only elements are equal
     * * Other ones are equal when the [sequences][BigIntegerProgression] have equal `first`, `last`, `step`.
     */
    override fun equals(other: Any?): Boolean = when {
        other !is BigIntegerProgression -> super.equals(other)
        this.isEmpty() && other.isEmpty() -> true
        this.isSingle() && other.isSingle() -> this.first == other.first
        else -> this.first == other.first
                && this.last == other.last
                && this.step == other.step
    }


    /**
     * Returns an [Iterator] that returns the values from the sequence.
     *
     * Throws an exception if the sequence is constrained to be iterated once and `iterator` is invoked the second time.
     */
    override fun iterator() = object : Iterator<BigInteger> {
        var cur = first

        /**
         * Returns `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean = when {
            isInfinite() -> true
            isEmpty() -> false
            step > 0 -> cur <= last
            else -> cur >= last
        }

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): BigInteger =
            if (hasNext()) cur.also { cur += step } else throw NoSuchElementException()
    }

    /**
     * Finds average element of the sequence (yields O(1) time capacity assuming [BigDecimal] arithmetic operations to take O(1) time).
     * @return
     * * `Double.POSITIVE_INFINITY` (`step > 0`) or `Double.NEGATIVE_INFINITY` (`step < 0`) when the [sequence][BigIntegerProgression] is infinite
     * * `Double.NaN` when the [sequence][BigIntegerProgression] is empty
     * * [BigDecimal] average element otherwise
     */
    fun average(): Number = when {
        isInfinite() -> if (step > 0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
        isEmpty() -> Double.NaN
        else -> BigDecimal(first + last!!).divide(BigDecimal.valueOf(2L))
    }

    /**
     * Finds sum of the sequence (yields O(1) time capacity assuming [BigInteger] arithmetic operations to take O(1) time).
     * @return
     * * `Double.POSITIVE_INFINITY` (`step > 0`) or `Double.NEGATIVE_INFINITY` (`step < 0`) when the [sequence][BigIntegerProgression] is infinite
     * * `BigInteger.ZERO` when the [sequence][BigIntegerProgression] is empty
     * * [BigInteger] sum otherwise
     */
    fun sum(): Number = when {
        isInfinite() -> if (step > 0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
        isEmpty() -> BigInteger.ZERO
        else -> (first + last!!) * count()!! / 2
    }

    override fun hashCode(): Int = when {
        isEmpty() -> 0
        isSingle() -> first.hashCode()
        else -> {
            var result = first.hashCode()
            result = 31 * result + step.hashCode()
            result = 31 * result + (last?.hashCode() ?: 0)
            result
        }
    }

    /**
     * [String] representation for the [BigIntegerProgression].
     * @return
     * * "[]" when empty
     * * "[" + [the only element][SingleRange.only] + "]" + when is single
     * * "[" + `first` + ".." + `last` + "]" when is finite ascending progression with `step` = 1
     * * "[" + `first` + ".." + `last` + "] step `step`" when is finite ascending progression with `step` ≠ 1
     * * "[" + `first` + " downTo " + `last` + "]" when is finite descending progression with `step` = 1
     * * "[" + `first` + " downTo " + `last` + "] step `step`" when is finite descending progression with `step` ≠ 1
     * * "[" + `first` + "..+∞)" when is infinite ascending progression with `step` = 1
     * * "[" + `first` + "..+∞) step `step`" when is infinite ascending progression with `step` ≠ 1
     * * "[" + `first` + " downTo -∞)" when is infinite descending progression with `step` = 1
     * * "[" + `first` + " downTo -∞) step `step`" when is infinite descending progression with `step` ≠ 1
     */
    override fun toString() = when {
        isEmpty() -> "[]"
        isSingle() -> "[$first]"
        else -> {
            val stepAbs = step.abs()
            val bracketPart =
                "[$first${if (step > 0) ".." else " downTo "}${
                    last?.toString()
                        ?.plus("]") ?: if (step < 0) "-∞)" else "+∞)"
                }"
            if (stepAbs == BigInteger.ONE) bracketPart else "$bracketPart step $stepAbs"
        }
    }

    /**
     * Indicates if the [sequence][BigIntegerProgression] contains the [BigInteger] yielding O(1) time capacity.
     */
    operator fun contains(number: BigInteger): Boolean = when {
        isEmpty() -> false
        step > 0 -> first <= number
                && (last == null || number <= last)
                && (number - first) % step == BigInteger.ZERO
        else -> (last == null || last <= number)
                && number <= first
                && (first - number) % -step == BigInteger.ZERO
    }

    /**
     * Indicates if the [sequence][BigIntegerProgression] contains the [number] (converted to [BigInteger]) yielding O(1) time capacity.
     */
    operator fun contains(number: Number): Boolean = contains(number.toBigInteger())

    /**
     * Indicates if the [sequence][BigIntegerProgression] contains all [Int]s (converted to [BigInteger]) of [subProgression] yielding O(1) time capacity.
     *
     * Empty [sequence][IntProgression] is contained by any [BigIntegerProgression].
     */
    operator fun contains(subProgression: IntProgression): Boolean = contains(progression(subProgression))

    /**
     * Indicates if the [sequence][BigIntegerProgression] contains all [Long]s (converted to [BigInteger]) of [subProgression] yielding O(1) time capacity.
     *
     * Empty [sequence][BigIntegerProgression] is contained by any [LongProgression].
     */
    operator fun contains(subProgression: LongProgression): Boolean = contains(progression(subProgression))

    /**
     * Indicates if the [sequence][BigIntegerProgression] contains all [BigInteger]s of [subProgression] yielding O(1) time capacity.
     *
     * Empty [sequence][BigIntegerProgression] is contained by any [BigIntegerProgression].
     */
    operator fun contains(subProgression: BigIntegerProgression): Boolean = when {
        subProgression.isEmpty() -> true
        subProgression.isInfinite() && this.isFinite() -> false
        subProgression.isInfinite() && this.isInfinite() && subProgression.step.signum() != this.step.signum() -> false
        subProgression.last != null && subProgression.last !in this -> false
        subProgression.first !in this -> false
        subProgression.isSingle() -> true
        else -> subProgression.step % this.step == BigInteger.ZERO
    }

    /**
     * Gets actual last element or throws [NoSuchElementException] if it does not exist.
     *
     * @throws NoSuchElementException When the [progression][BigIntegerProgression] is empty or infinite.
     */
    fun last(): BigInteger = when {
        isEmpty() -> throw NoSuchElementException("Empty range")
        isInfinite() -> throw NoSuchElementException("Infinite range")
        else -> last!!
    }

    /**
     * Gets [List] of distinct elements.
     *
     * Returns just [toList] because all elements are always unique.
     *
     * @throws InfiniteProgressionException when the [List] would be infinite
     * @see toList
     */
    fun distinct() = toList()

    /**
     * Skips [n] first elements.
     *
     * Yields O(1) time capacity.
     * @throws IllegalArgumentException When [n] is negative
     */
    fun drop(n: BigInteger): BigIntegerProgression = when {
        n < 0 -> throw IllegalArgumentException()
        isEmpty() -> this
        else -> progression(first + step * n, last, step)
    }

    /**
     * Skips [n] (converted to [BigInteger]) first elements.
     *
     * Yields O(1) time capacity.
     * @throws IllegalArgumentException When [n] is negative
     */
    open fun drop(n: Number): BigIntegerProgression = drop(n.toBigInteger())

    /**
     * Gets element by the specified [index] (since 0) (converted to [BigInteger]) or null if the [index] is out of range.
     *
     * Null is returned if the [index] is negative or if it is not less than [count].
     *
     * Yields O(1) time capacity.
     */
    fun elementAtOrNull(index: Number): BigInteger? = elementAtOrNull(index.toBigInteger())

    /**
     * Gets element by the specified [index] (since 0) or null if the index is out of range.
     *
     * Null is returned if the [index] is negative or if it is not less than [count].
     *
     * Yields O(1) time capacity.
     */
    fun elementAtOrNull(index: BigInteger): BigInteger? = if (index < 0) null else {
        val dropped = drop(index)
        dropped.first.takeUnless { dropped.isEmpty() }
    }

    /**
     * Gets element by the specified [index] (since 0) (converted to [BigInteger]) or calls [defaultValue] with [index] if the [index] is out of range.
     *
     * The [defaultValue] is be called if the [index] is negative or if it is not less than [count].
     *
     * Yields O(1) time capacity.
     */
    fun elementAtOrElse(index: Number, defaultValue: (Number) -> BigInteger): BigInteger =
        elementAtOrNull(index) ?: defaultValue(index)

    /**
     * Gets element by the specified [index] (since 0) (converted to [BigInteger]) or throws [IndexOutOfBoundsException] if the [index] is out of range.
     *
     * Yields O(1) time capacity.
     * @throws IndexOutOfBoundsException When the [index] is negative or if it is not less than [count].
     */
    fun elementAt(index: Number): BigInteger = elementAtOrElse(index) { throw IndexOutOfBoundsException() }

    /**
     * Returns index (since 0) of the [element] in the [progression][BigIntegerProgression] or -1 if the progression does not contain the [element].
     *
     * Yields O(1) time capacity.
     */
    fun indexOf(element: BigInteger): BigInteger = if (element in this) (element - first) / step else -BigInteger.ONE

    /**
     * Returns index (since 0) of the [element] (converted to [BigInteger]) in the [progression][BigIntegerProgression] or -1 if the progression does not contain the [element].
     *
     * Yields O(1) time capacity.
     */
    fun indexOf(element: Number): BigInteger = indexOf(element.toBigInteger())

    //    infix fun intersect(other: BigIntProgression) = when {
//        isEmpty() || other.isEmpty() -> empty
//    } TODO implement, for Ints, Longs
    /**
     * Returns last index of the element that is always equal to [BigIntegerProgression.indexOf] because all elements are unique in the sequence.
     * @see BigIntegerProgression.indexOf
     */
    fun lastIndexOf(element: Number): BigInteger = indexOf(element)

    /**
     * Returns
     * * null when the [progression][BigIntegerProgression] is empty
     * *`Double.POSITIVE_INFINITY` if the sequence is infinite and ascending
     * *`Double.NEGATIVE_INFINITY` if the sequence is infinite and descending
     * * The `last` element otherwise
     *
     * Yields O(1) time capacity.
     */
    fun lastOrNull(): Number? = when {
        isEmpty() -> null
        isInfinite() -> if (step > 0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
        else -> last!!
    }

    /**
     * Gets max element yielding O(1) time capacity.
     *
     * @return
     * * null when the [progression][BigIntegerProgression] is empty
     * * `Double.POSITIVE_INFINITY` when the [progression][BigIntegerProgression] is infinite and ascending
     * * `last` when the [progression][BigIntegerProgression] is finite and ascending
     * * `first` otherwise
     */
    fun max(): Number? = when {
        isEmpty() -> null
        step > 0 -> last ?: Double.POSITIVE_INFINITY
        else -> first
    }

    /**
     * Gets min element yielding O(1) time capacity.
     *
     * @return
     * * null when the [progression][BigIntegerProgression] is empty
     * * `Double.NEGATIVE_INFINITY` when the [progression][BigIntegerProgression] is infinite and descending
     * * `last` when the [progression][BigIntegerProgression] is finite and descending
     * * `first` otherwise
     */
    fun min(): Number? = when {
        isEmpty() -> null
        step > 0 -> first
        else -> last ?: Double.NEGATIVE_INFINITY
    }

    //TODO minus, minusElement, for Ints, Longs

    /**
     * Concatenates the [BigIntegerProgression] with the [other] if the [BigIntegerProgression] is finite.
     */
    operator fun plus(other: Number): Sequence<BigInteger> =
        if (this.isInfinite()) this else this.asSequence() + other.toBigInteger()

    /**
     * Concatenates the [BigIntegerProgression] with the [other] if the [BigIntegerProgression] is finite.
     */
    operator fun plus(other: Array<out Number>): Sequence<BigInteger> =
        if (this.isInfinite()) this else this.asSequence() + other.map { it.toBigInteger() }

    /**
     * Concatenates the [BigIntegerProgression] with the [other] if the [BigIntegerProgression] is finite.
     */
    operator fun plus(other: Iterable<Number>): Sequence<BigInteger> =
        if (this.isInfinite()) this else this.asSequence() + other.map { it.toBigInteger() }

    /**
     * Concatenates the [BigIntegerProgression] with the [other] if the [BigIntegerProgression] is finite.
     */
    operator fun plus(other: Sequence<Number>): Sequence<BigInteger> =
        if (this.isInfinite()) this else this.asSequence() + other.map { it.toBigInteger() }

    /**
     * Adds the [other] to the end of the sequence if it is finite.
     */
    fun plusElement(other: Number): Sequence<BigInteger> = this + other

    /**
     * Reverses order of the sequence if possible.
     *
     * @return null when this is infinite, reversed [BigIntegerProgression] otherwise
     */
    fun reversed(): BigIntegerProgression? = if (last == null) null else progression(last, first, -step)

    /**
     * Changes direction of the infinite [BigIntegerProgression] to the opposite one.
     *
     * Example: [1..+∞) step 3 ⇆ [1 downTo -∞) step 3
     *
     * @return null when this is finite, flipped [BigIntegerProgression] otherwise
     */
    fun flip(): BigIntegerProgression? = if (last == null) progression(first, null, -step) else null

    /**
     * Adds [n] elements to the end of the [progression][BigIntegerProgression] according to its `step` if it has at least 2 elements.
     *
     * The input [progression][BigIntegerProgression] is returned if it can not be extended (it is empty or infinite).
     * The `step` for all progressions of length 1 is assumed to be 1 (they are instances of [SingleRange]).
     * @throws IllegalArgumentException When [n] is negative
     */
    open fun extend(n: BigInteger): BigIntegerProgression = when {
        n < 0 -> throw IllegalArgumentException("Can not extend by less than 0 elements")
        isEmpty() || isInfinite() -> this
        else -> progression(first, last!! + step * n, step)
    }

    /**
     * Adds [n] (converted to [BigInteger]) elements to the end of the [progression][BigIntegerProgression] according to its `step` if it has at least 2 elements.
     *
     * The input [progression][BigIntegerProgression] is returned if it can not be extended (it is empty or infinite).
     * The `step` for all progressions of length 1 is assumed to be 1 (they are instances of [SingleRange]).
     * @throws IllegalArgumentException When [n] is negative
     */
    open fun extend(n: Number): BigIntegerProgression = extend(n.toBigInteger())

    /**
     * Makes [number] steps forward for each item.
     *
     * Adds `step * [number]` to `first` and to `last` if it is not `null`.
     */
    open infix fun shr(number: Number) = progression(first + step * number, last?.let { it + step * number }, step)

    /**
     * Makes [number] steps backward for each item.
     *
     * Subtracts `step * [number]` from `first` and from `last` if it is not `null`.
     */
    open infix fun shl(number: Number) = shr(number.toBigInteger().negate())

    /**
     * Changes `step` not changing direction of progression.
     *
     * The last element may change: `|last - first|` may reduce.
     * @param step positive number to be new property `step`
     * @throws IllegalArgumentException When suggested `step` is not positive
     */
    open infix fun step(step: Number): BigIntegerProgression {
        val otherStep = step.toBigInteger()
        checkStepIsPositive(otherStep)
        return progression(first, last, if (this.step > 0) otherStep else -otherStep)
    }

    private fun checkStepIsPositive(step: BigInteger) {
        if (step <= 0) throw IllegalArgumentException("Step must be positive, was: $step.")
    }

    //TODO implement infix fun subtract, for Ints, Longs
    /**
     * Limits to [n] (converted to [BigInteger]) first elements.
     *
     * @return [progression][BigIntegerProgression] of not more than [n] elements
     * @throws IllegalArgumentException When [n] is negative
     */
    open fun take(n: Number): BigIntegerProgression = take(n.toBigInteger())

    /**
     * Limits to [n] first elements.
     *
     * @return [progression][BigIntegerProgression] of not more than [n] elements
     * @throws IllegalArgumentException When [n] is negative
     */
    fun take(n: BigInteger): BigIntegerProgression = when {
        n < 0 -> throw IllegalArgumentException("Can not take less than 0 elements")
        isFinite() && count()!! <= n -> this
        else -> progression(first, first + (n - 1) * step, step)
    }

    /**
     * Appends all elements to the given [destination] collection.
     * @throws InfiniteProgressionException When progression is infinite so it can not be added fully
     */
    inline fun <reified C : MutableCollection<BigInteger>> toCollection(destination: C): C =
        if (isInfinite())
            throw InfiniteProgressionException("Addition of all elements of $this to ${C::class.java.name} is infinite")
        else
            asSequence().toCollection(destination)

    /**
     * Converts the progression into the [List].
     *
     * @throws InfiniteProgressionException When progression is infinite so it can not be added fully
     */
    fun toList(): List<BigInteger> = if (isInfinite()) infiniteConversation() else asSequence().toList()

    /**
     * Converts the progression into the [MutableList].
     *
     * @throws InfiniteProgressionException When progression is infinite so it can not be added fully
     */
    fun toMutableList(): MutableList<BigInteger> =
        if (isInfinite()) infiniteConversation() else asSequence().toMutableList()


    /**
     * Converts the progression into the [HashSet].
     *
     * @throws InfiniteProgressionException When progression is infinite so it can not be added fully
     */
    fun toHashSet(): HashSet<BigInteger> = if (isInfinite()) infiniteConversation() else asSequence().toHashSet()

    /**
     * Converts the progression into the [MutableSet].
     *
     * @throws InfiniteProgressionException When progression is infinite so it can not be added fully
     */
    fun toMutableSet(): MutableSet<BigInteger> =
        if (isInfinite()) infiniteConversation() else asSequence().toMutableSet()

    /**
     * Converts the progression into the [Set].
     *
     * @throws InfiniteProgressionException When progression is infinite so it can not be added fully
     */
    fun toSet(): Set<BigInteger> = if (isInfinite()) infiniteConversation() else asSequence().toSet()

    /**
     * Converts the progression into the [SortedSet].
     *
     * @throws InfiniteProgressionException When progression is infinite so it can not be added fully
     */
    fun toSortedSet(): SortedSet<BigInteger> = if (isInfinite()) infiniteConversation() else asSequence().toSortedSet()

    /**
     * Converts the progression into the [SortedSet] with specified [comparator].
     *
     * @throws InfiniteProgressionException When progression is infinite so it can not be added fully
     */
    fun toSortedSet(comparator: Comparator<in BigInteger>): SortedSet<BigInteger> =
        if (isInfinite()) infiniteConversation() else asSequence().toSortedSet(comparator)

    private inline fun <reified T> infiniteConversation(): T =
        throw InfiniteProgressionException("Conversation of $this to ${T::class.java.name} is infinite")

    open operator fun component1(): BigInteger = elementAt(0)
    operator fun component2(): BigInteger = elementAt(1)
    operator fun component3(): BigInteger = elementAt(2)
    operator fun component4(): BigInteger = elementAt(3)
    operator fun component5(): BigInteger = elementAt(4)
}

/**
 * Exception that is thrown when the called operation does not work fine only for infinite operations and it can not be stopped stop its execution (by some other [Exception]).
 */
class InfiniteProgressionException(message: String = "The function evaluation is infinite") :
    UnsupportedOperationException(message)

/**
 * The singleton represents empty [BigIntegerProgression], [sequences.BigIntegerRange].
 *
 * All methods here are just to specify return type ([EmptyRange]) statically.
 *
 * Method [toString] works as it is defined in [BigIntegerProgression].
 */
@Suppress("UNUSED_PARAMETER")
object EmptyRange : BigIntegerRange(BigInteger.ONE, BigInteger.ZERO) {
    infix fun intersect(other: BigIntegerProgression) = EmptyRange
    override infix fun intersect(other: BigIntegerRange) = EmptyRange
    infix fun intersect(other: IntProgression) = EmptyRange
    override infix fun intersect(other: IntRange) = EmptyRange
    infix fun intersect(other: LongProgression) = EmptyRange
    override infix fun intersect(other: LongRange) = EmptyRange

    override fun extend(n: Number) = super.extend(n) as EmptyRange
    override fun shl(number: Number) = super.shl(number) as EmptyRange
    override fun shr(number: Number) = super.shr(number) as EmptyRange
    override fun drop(n: Number) = super.drop(n) as EmptyRange
    override fun step(step: Number) = super.step(step) as EmptyRange
    override fun take(n: Number) = super.take(n) as EmptyRange
}

/**
 * The class represents [BigIntegerProgression], [sequences.BigIntegerRange] with exact 1 element.
 *
 * All methods here are just to specify return type ([EmptyRange]) statically.
 *
 * Method [toString] works as it is defined in [BigIntegerProgression].
 *
 * `Step` equals to 1 for all instances of [SingleRange].
 * @property only the single element of the progression
 */
data class SingleRange(val only: BigInteger) : BigIntegerRange(only, only) {
    constructor(only: Number) : this(only.toBigInteger())

    override fun toString(): String = super.toString()

    override fun shl(number: Number) = super.shl(number) as SingleRange
    override fun shr(number: Number) = super.shr(number) as SingleRange
    override fun step(step: Number) = super.step(step) as SingleRange
}

