import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import kotlin.Comparator
import kotlin.NoSuchElementException
import kotlin.math.sign

fun progression(first: BigInteger, toInclusive: BigInteger?, step: BigInteger = BigInteger.ONE): BigIntegerProgression {
    val simple = BigIntegerProgression(first, toInclusive, step)
    return when {
        simple.isEmpty() -> EmptyRange
        simple.isSingle() -> SingleRange(simple.first)
        step == BigInteger.ONE -> BigIntegerRange(first, toInclusive)
        else -> simple
    }
}

fun progression(progression: BigIntegerProgression): BigIntegerProgression = progression

fun progression(progression: IntProgression): BigIntegerProgression = progression(
    progression.first.toBigInteger(),
    progression.last.toBigInteger(),
    progression.step.toBigInteger()
)

fun progression(progression: LongProgression): BigIntegerProgression = progression(
    progression.first.toBigInteger(),
    progression.last.toBigInteger(),
    progression.step.toBigInteger()
)

fun progression(fromInclusive: Number, toInclusive: Number?, step: Number): BigIntegerProgression = progression(
    fromInclusive.toBigInteger(),
    toInclusive
        .takeUnless {
            it is Double && it.isInfinite() && it.sign.toInt() == step.toBigInteger().signum()
                    || it is Float && it.isInfinite() && it.sign.toInt() == step.toBigInteger().signum()
        }
        ?.toBigInteger(),
    step.toBigInteger()
)

open class BigIntegerProgression internal constructor(
    val first: BigInteger,
    toInclusive: BigInteger?,
    val step: BigInteger
) :
    Serializable, Sequence<BigInteger> {
    init {
        if (step == BigInteger.ZERO) throw IllegalArgumentException("Step must be non-zero.")
    }

    val last: BigInteger? = when {
        toInclusive == null -> null
        step > 0 && toInclusive < first -> first - step
        step < 0 && toInclusive > first -> first - step
        step > 0 -> first + (toInclusive - first) / step * step
        step < 0 -> first + (first - toInclusive) / -step * step
        else -> throw IllegalStateException("Can not happen")
    }

    fun isEmpty(): Boolean = last == first - step

    fun isInfinite(): Boolean = last == null

    fun isFinite(): Boolean = !isInfinite()

    fun isNotEmpty(): Boolean = !isEmpty()

    fun count(): BigInteger? = when {
        isInfinite() -> null
        isEmpty() -> BigInteger.ZERO
        else -> (last!! - first) / step + 1
    }

    fun isSingle(): Boolean = count() == BigInteger.ONE

    override fun equals(other: Any?): Boolean = when {
        other !is BigIntegerProgression -> false
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

    fun average(): Number = when {
        isInfinite() -> if (step > 0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
        isEmpty() -> Double.NaN
        else -> BigDecimal(first + last!!).divide(BigDecimal.valueOf(2L))
    }

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

    override fun toString() = when {
        isEmpty() -> "[]"
        isSingle() -> "[$first]"
        else -> {
            val stepAbs = step.abs()
            val bracketPart =
                "[$first${if (step > 0) ".." else " downTo "}${last?.toString()
                    ?.plus("]") ?: if (step < 0) "-∞)" else "+∞)"}"
            if (stepAbs == BigInteger.ONE) bracketPart else "$bracketPart step $stepAbs"
        }
    }

    operator fun contains(number: BigInteger): Boolean = when {
        isEmpty() -> false
        step > 0 -> first <= number
                && (last == null || number <= last)
                && (number - first) % step == BigInteger.ZERO
        else -> (last == null || last <= number)
                && number <= first
                && (first - number) % -step == BigInteger.ZERO
    }

    operator fun contains(number: Number): Boolean = contains(number.toBigInteger())

    operator fun contains(subProgression: IntProgression): Boolean = contains(progression(subProgression))
    operator fun contains(subProgression: LongProgression): Boolean = contains(progression(subProgression))
    operator fun contains(subProgression: BigIntegerProgression): Boolean = when {
        subProgression.isEmpty() -> true
        subProgression.isInfinite() && this.isFinite() -> false
        subProgression.isInfinite() && this.isInfinite() && subProgression.step.signum() != this.step.signum() -> false
        subProgression.last != null && subProgression.last !in this -> false
        subProgression.first !in this -> false
        subProgression.isSingle() -> true
        else -> subProgression.step % this.step == BigInteger.ZERO
    }

    fun last(): BigInteger = when {
        isEmpty() -> throw NoSuchElementException("Empty range")
        isInfinite() -> throw NoSuchElementException("Infinite range")
        else -> last!!
    }

    fun distinct() = toList()

    fun drop(n: BigInteger): BigIntegerProgression = when {
        n < 0 -> throw IllegalArgumentException()
        isEmpty() -> this
        else -> progression(first + step * n, last, step)
    }

    open fun drop(n: Number): BigIntegerProgression = drop(n.toBigInteger())

    fun elementAtOrNull(index: Number): BigInteger? {
        val dropped = drop(index)
        return dropped.first.takeUnless { dropped.isEmpty() }
    }

    fun elementAtOrElse(index: Number, defaultValue: (Number) -> BigInteger): BigInteger =
        elementAtOrNull(index) ?: defaultValue(index)

    fun elementAt(index: Number): BigInteger = elementAtOrElse(index) { throw NoSuchElementException() }

    fun indexOf(element: BigInteger): BigInteger = if (element in this) (element - first) / step else -BigInteger.ONE
    fun indexOf(element: Number): BigInteger = indexOf(element.toBigInteger())

    //    infix fun intersect(other: BigIntProgression) = when {
//        isEmpty() || other.isEmpty() -> empty
//    } TODO implement, for Ints, Longs
//
    fun lastIndexOf(element: Number): BigInteger = indexOf(element)

    fun lastOrNull(): Number? = when {
        isEmpty() -> null
        isInfinite() -> if (step > 0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
        else -> last!!
    }

    fun max(): Number? = when {
        isEmpty() -> null
        step > 0 -> last ?: Double.POSITIVE_INFINITY
        else -> first
    }

    fun min(): Number? = when {
        isEmpty() -> null
        step > 0 -> first
        else -> last ?: Double.NEGATIVE_INFINITY
    }

    //TODO minus, minusElement, for Ints, Longs

    operator fun plus(other: Number): Sequence<BigInteger> =
        if (this.isInfinite()) this else this.asSequence() + other.toBigInteger()

    operator fun plus(other: Array<out Number>): Sequence<BigInteger> =
        if (this.isInfinite()) this else this.asSequence() + other.map { it.toBigInteger() }

    operator fun plus(other: Iterable<Number>): Sequence<BigInteger> =
        if (this.isInfinite()) this else this.asSequence() + other.map { it.toBigInteger() }

    operator fun plus(other: Sequence<Number>): Sequence<BigInteger> =
        if (this.isInfinite()) this else this.asSequence() + other.map { it.toBigInteger() }

    fun plusElement(other: Number): Sequence<BigInteger> = this + other

    fun reversed(): BigIntegerProgression? = if (last == null) null else progression(last, first, -step)

    fun flip(): BigIntegerProgression? = if (last == null) progression(first, null, -step) else null

    open fun extend(n: Number): BigIntegerProgression = extend(n.toBigInteger())
    open fun extend(n: BigInteger): BigIntegerProgression = when {
        n < 0 -> throw IllegalArgumentException("Can not extend by less than 0 elements")
        isEmpty() || isInfinite() -> this
        else -> progression(first, last!! + step * n, step)
    }

    open infix fun shr(number: Number) = progression(first + step * number, last?.let { it + step * number }, step)
    open infix fun shl(number: Number) = shr(number.toBigInteger().negate())

    open infix fun step(step: Number): BigIntegerProgression {
        val otherStep = step.toBigInteger()
        checkStepIsPositive(otherStep)
        return progression(first, last, if (this.step > 0) otherStep else -otherStep)
    }

    private fun checkStepIsPositive(step: BigInteger) {
        if (step <= 0) throw IllegalArgumentException("Step must be positive, was: $step.")
    }
    //TODO implement infix fun subtract, for Ints, Longs

    open fun take(n: Int): BigIntegerProgression = when {
        n < 0 -> throw IllegalArgumentException("Can not take less than 0 elements")
        isFinite() && count()!! <= n -> this
        else -> progression(first, first + (n - 1) * step, step)
    }

    fun <C : MutableCollection<BigInteger>> toCollection(c: C): C =
        if (isInfinite()) badOperation() else asSequence().toCollection(c)

    fun toList(): List<BigInteger> = if (isInfinite()) badOperation() else asSequence().toList()
    fun toMutableList(): MutableList<BigInteger> = if (isInfinite()) badOperation() else asSequence().toMutableList()

    fun toHashSet(): HashSet<BigInteger> = if (isInfinite()) badOperation() else asSequence().toHashSet()
    fun toMutableSet(): MutableSet<BigInteger> = if (isInfinite()) badOperation() else asSequence().toMutableSet()
    fun toSet(): Set<BigInteger> = if (isInfinite()) badOperation() else asSequence().toSet()
    fun toSortedSet(): SortedSet<BigInteger> = if (isInfinite()) badOperation() else asSequence().toSortedSet()
    fun toSortedSet(comparator: Comparator<in BigInteger>): SortedSet<BigInteger> =
        if (isInfinite()) badOperation() else asSequence().toSortedSet(comparator)

    private fun <T> badOperation(): T = throw InfiniteRangeException()

    open operator fun component1(): BigInteger = elementAt(0)
    operator fun component2(): BigInteger = elementAt(1)
    operator fun component3(): BigInteger = elementAt(2)
    operator fun component4(): BigInteger = elementAt(3)
    operator fun component5(): BigInteger = elementAt(4)
}

class InfiniteRangeException : UnsupportedOperationException("The function evaluation is infinite")

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
    override fun take(n: Int) = super.take(n) as EmptyRange
}

data class SingleRange(val only: BigInteger) : BigIntegerRange(only, only) {
    constructor(only: Number) : this(only.toBigInteger())
    override fun toString(): String = super.toString()

    override fun shl(number: Number) = super.shl(number) as SingleRange
    override fun shr(number: Number) = super.shr(number) as SingleRange
    override fun step(step: Number) = super.step(step) as SingleRange
}

