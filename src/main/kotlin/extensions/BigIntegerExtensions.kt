package extensions

import sequences.progression
import sequences.range
import java.math.BigDecimal
import java.math.BigInteger

/**
 * Converts a [Number] to [BigInteger] using [Long], [Double], [BigDecimal] representations as intermediate or custom `[Number].toBigInteger()` method.
 *
 * @return Converted [BigInteger]
 * @receiver A [Number] to convert
 * @throws ArithmeticException When [Double] or [Float] input has non-null fractional part
 * @throws NoSuchMethodException When no method `[Number].toBigInteger()` found when needed or its result is not [BigInteger] instance
 * @throws NumberFormatException When conversation of [Float] and [Double] to [BigDecimal] is not possible because is `NaN` or infinite
 * @throws Throwable When method custom `[Number].toBigInteger()` throws it
 */
fun Number.toBigInteger(): BigInteger = when (this) {
    is BigInteger -> this
    is BigDecimal -> toBigIntegerExact()
    is Double -> BigDecimal.valueOf(this).toBigIntegerExact()
    is Float -> toDouble().toBigInteger()
    is Byte -> BigInteger.valueOf(this.toLong())
    is Short -> BigInteger.valueOf(this.toLong())
    is Int -> BigInteger.valueOf(this.toLong())
    is Long -> BigInteger.valueOf(this)
    else -> this::class.java.getMethod("toBigInteger").invoke(this) as? BigInteger
        ?: throw NoSuchMethodException("Bad return type: BigInteger expected")
}

/**
 * Redefines `plus` operator for [BigInteger] and [BigInteger] instances.
 * The purpose of the redefinition is to resolve `[BigInteger] + [BigInteger]` when `[BigInteger] + [Number]` and `[Number] + [BigInteger]` are defined.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.add
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.plus(number: BigInteger): BigInteger = this.toBigInteger().add(number)

/**
 * Sums [Number] and [BigInteger] as two [BigInteger] instances.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.add
 * @return Resulting [BigInteger]
 */
operator fun Number.plus(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.add(number)
    else -> this.toBigInteger().add(number)
}

/**
 * Sums [BigInteger] and [Number] as two [BigInteger] instances.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.add
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.plus(number: Number): BigInteger = when (number) {
    is BigInteger -> this.add(number)
    else -> this.add(number.toBigInteger())
}

/**
 * Redefines `minus` operator for two [BigInteger] instances.
 * The purpose of the redefinition is to resolve `[BigInteger] - [BigInteger]` when `[BigInteger] - [Number]` and `[Number] - [BigInteger]` are defined.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.minus
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.minus(number: BigInteger): BigInteger = this.toBigInteger().subtract(number)

/**
 * Subtracts [BigInteger] from [Number] interpreted as [BigInteger].
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.subtract
 * @return Resulting [BigInteger]
 */
operator fun Number.minus(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.subtract(number)
    else -> this.toBigInteger().subtract(number)
}

/**
 * Subtracts [Number] interpreted as [BigInteger] from [BigInteger].
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.subtract
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.minus(number: Number): BigInteger = when (number) {
    is BigInteger -> this.subtract(number)
    else -> this.subtract(number.toBigInteger())
}

/**
 * Redefines `times` operator for two [BigInteger] instances.
 * The purpose of the redefinition is to resolve `[BigInteger] * [BigInteger]` when `[BigInteger] * [Number]` and `[Number] * [BigInteger]` are defined.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.multiply
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.times(number: BigInteger): BigInteger = this.toBigInteger().multiply(number)

/**
 * Multiplies [Number] and [BigInteger] as two [BigInteger] instances.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.multiply
 * @return Resulting [BigInteger]
 */
operator fun Number.times(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.multiply(number)
    else -> this.toBigInteger().multiply(number)
}

/**
 * Multiplies [BigInteger] and [Number] as two [BigInteger] instances.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.multiply
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.times(number: Number): BigInteger = when (number) {
    is BigInteger -> this.multiply(number)
    else -> this.multiply(number.toBigInteger())
}

/**
 * Redefines `div` operator for [BigInteger] and [BigInteger] instances.
 * The purpose of the redefinition is to resolve `[BigInteger] / [BigInteger]` when `[BigInteger] / [Number]` and `[Number] / [BigInteger]` are defined.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.divide
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.div(number: BigInteger): BigInteger = this.toBigInteger().divide(number)

/**
 * Divides [Number] interpreted as [BigInteger] by [BigInteger].
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.divide
 * @return Resulting [BigInteger]
 */
operator fun Number.div(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.divide(number)
    else -> this.toBigInteger().divide(number)
}

/**
 * Divides [BigInteger] and [Number] interpreted as [BigInteger].
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.divide
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.div(number: Number): BigInteger = when (number) {
    is BigInteger -> this.divide(number)
    else -> this.divide(number.toBigInteger())
}

/**
 * Redefines `rem` operator for [BigInteger] and [BigInteger] instances.
 * The purpose of the redefinition is to resolve `[BigInteger] % [BigInteger]` when `[BigInteger] % [Number]` and `[Number] % [BigInteger]` are defined.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.remainder
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.rem(number: BigInteger): BigInteger = this.toBigInteger().remainder(number)

/**
 * Gets remainder of [Number] and [BigInteger] as of two [BigInteger] instances.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.remainder
 * @return Resulting [BigInteger]
 */
operator fun Number.rem(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.remainder(number)
    else -> this.toBigInteger().remainder(number)
}

/**
 * Gets remainder of [BigInteger] and [Number] as of two [BigInteger] instances.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.remainder
 * @return Resulting [BigInteger]
 */
operator fun BigInteger.rem(number: Number): BigInteger = when (number) {
    is BigInteger -> this.remainder(number)
    else -> this.remainder(number.toBigInteger())
}

/**
 * Compares [Number] and [BigInteger] as two [BigInteger] instances.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.compareTo
 * @return [Int] number with the same sign as `[this] - [number]`
 */
operator fun Number.compareTo(number: BigInteger): Int = when (this) {
    is BigInteger -> this.compareTo(number)
    else -> this.toBigInteger().compareTo(number)
}


/**
 * Compares [BigInteger] and [Number] as two [BigInteger] instances.
 * @receiver The first term
 * @param number The second term
 * @see BigInteger.compareTo
 * @return [Int] number with the same sign as `[this] - [number]`
 */
operator fun BigInteger.compareTo(number: Number): Int = when (number) {
    is BigInteger -> this.compareTo(number)
    else -> this.compareTo(number.toBigInteger())
}

/**
 * Defines `rangeTo` operator for [BigInteger] and [BigInteger] instances.
 * @receiver The greatest lower bound integer
 * @param number The least upper bound integer
 * @return Increasing [sequences.BigIntegerRange] from the [this] up to [number] (inclusive)
 * @see sequences.BigIntegerRange
 * @see IntRange
 * @see LongRange
 * @see Int.rangeTo
 * @see Long.rangeTo
 */
operator fun BigInteger.rangeTo(number: BigInteger) = range(this, number)

/**
 * Defines `rangeTo` operator for [Number] and [BigInteger] as for two [BigInteger] instances.
 * @receiver The greatest lower bound integer
 * @param number The least upper bound integer
 * @return Increasing [sequences.BigIntegerRange] from [this] up to [number] (inclusive)
 * @see IntRange
 * @see LongRange
 * @see Int.rangeTo
 * @see Long.rangeTo
 */
operator fun Number.rangeTo(number: BigInteger) = range(this, number)

/**
 * Defines `rangeTo` operator for [BigInteger] and [Number] as for two [BigInteger] instances.
 * @receiver The greatest lower bound integer
 * @param number The least upper bound integer
 * @return Increasing [sequences.BigIntegerRange] from [this] up to [number] (inclusive)
 * @see IntRange
 * @see LongRange
 * @see Int.rangeTo
 * @see Long.rangeTo
 */
operator fun BigInteger.rangeTo(number: Number) = range(this, number)

/**
 * Defines `until` infix function for [BigInteger] and [BigInteger] instances.
 * @receiver The greatest lower bound integer
 * @param number The least upper bound integer
 * @return Increasing [sequences.BigIntegerRange] from [this] up to [number] (exclusive)
 * @see sequences.BigIntegerRange
 * @see IntRange
 * @see LongRange
 * @see Int.until
 * @see Long.until
 */
infix fun BigInteger.until(number: BigInteger) = range(this, number.toBigInteger().dec())

/**
 * Defines `until` infix function for [Number] and [BigInteger] as for two [BigInteger] instances.
 * @receiver The greatest lower bound integer
 * @param number The least upper bound integer
 * @return Increasing [sequences.BigIntegerRange] from [this] up to [number] (exclusive)
 * @see IntRange
 * @see LongRange
 * @see Int.rangeTo
 * @see Long.rangeTo
 */
infix fun Number.until(number: BigInteger) = range(this, number.toBigInteger().dec())

/**
 * Defines `until` infix function for [BigInteger] and [Number] as for two [BigInteger] instances.
 * @receiver The greatest lower bound integer
 * @param number The least upper bound integer
 * @return Increasing [sequences.BigIntegerRange] from [this] up to [number] (exclusive)
 * @see IntRange
 * @see LongRange
 * @see Int.rangeTo
 * @see Long.rangeTo
 */
infix fun BigInteger.until(number: Number) = range(this, number.toBigInteger().dec())

/**
 * Defines `downTo` infix function for [BigInteger] and [BigInteger] instances.
 * @receiver The least upper bound integer
 * @param number The greatest lower bound integer
 * @return Decreasing [sequences.BigIntegerProgression] from the [this] down to [number] (inclusive)
 * @see sequences.BigIntegerProgression
 * @see IntProgression
 * @see LongProgression
 * @see Int.downTo
 * @see Long.downTo
 */
infix fun BigInteger.downTo(number: BigInteger) = progression(this, number, -BigInteger.ONE)

/**
 * Defines `downTo` infix function for [BigInteger] and [Number] as for two [BigInteger] instances.
 * @receiver The least upper bound integer
 * @param number The greatest lower bound integer
 * @return Decreasing [sequences.BigIntegerRange] from [this] down to [number] (inclusive)
 * @see IntProgression
 * @see LongProgression
 * @see Int.downTo
 * @see Long.downTo
 */
infix fun Number.downTo(number: BigInteger) = progression(this, number, -BigInteger.ONE)

/**
 * Defines `downTo` infix function for [Number] and [BigInteger] as for two [BigInteger] instances.
 * @receiver The least upper bound integer
 * @param number The greatest lower bound integer
 * @return Decreasing [sequences.BigIntegerRange] from [this] down to [number] (inclusive)
 * @see IntProgression
 * @see LongProgression
 * @see Int.downTo
 * @see Long.downTo
 */
infix fun BigInteger.downTo(number: Number) = progression(this, number, -BigInteger.ONE)

/**
 * Represents mathematical positive infinity.
 */
@Suppress("UNUSED_PARAMETER")
object PositiveInfinity {
    /**
     * +(+∞) = +∞
     * @return [+∞][PositiveInfinity]
     */
    operator fun unaryPlus() = PositiveInfinity

    /**
     * -(+∞) = -∞
     * @return [-∞][NegativeInfinity]
     */
    operator fun unaryMinus() = NegativeInfinity

    /**
     * +∞
     */
    override fun toString() = "+$Infinity"

    /**
     * +∞ + +∞ = +∞
     */
    operator fun plus(positiveInfinity: PositiveInfinity) = PositiveInfinity

    /**
     * +∞ * +∞ = +∞
     */
    operator fun times(positiveInfinity: PositiveInfinity) = PositiveInfinity

    /**
     * +∞ * -∞ = -∞
     */
    operator fun times(negativeInfinity: NegativeInfinity) = NegativeInfinity

    /**
     * +∞ - -∞ = +∞
     */
    operator fun minus(negativeInfinity: NegativeInfinity) = PositiveInfinity
}

/**
 * Represents mathematical negative infinity
 */
@Suppress("UNUSED_PARAMETER")
object NegativeInfinity {
    /**
     * +(-∞) = -∞
     * @return [-∞][NegativeInfinity]
     */
    operator fun unaryPlus() = NegativeInfinity

    /**
     * -(-∞) = +∞
     * @return [+∞][PositiveInfinity]
     */
    operator fun unaryMinus() = PositiveInfinity

    /**
     * -∞
     */
    override fun toString() = "-$Infinity"

    /**
     * -∞ + -∞ = -∞
     */
    operator fun plus(negativeInfinity: NegativeInfinity) = NegativeInfinity
    /**
     * -∞ * +∞ = -∞
     */
    operator fun times(positiveInfinity: PositiveInfinity) = NegativeInfinity
    /**
     * -∞ * -∞ = +∞
     */
    operator fun times(negativeInfinity: NegativeInfinity) = PositiveInfinity
    /**
     * -∞ - +∞ = -∞
     */
    operator fun minus(positiveInfinity: PositiveInfinity) = NegativeInfinity
}

/**
 * Represents mathematical unsigned infinity.
 */
object Infinity {
    /**
     * +∞
     * @return [+∞][PositiveInfinity]
     */
    operator fun unaryPlus() = PositiveInfinity

    /**
     * -∞
     * @return [-∞][NegativeInfinity]
     */
    operator fun unaryMinus() = NegativeInfinity

    /**
     * ∞
     */
    override fun toString() = "∞"
}

/**
 * Infinite increasing [sequences.BigIntegerRange] starting with the given [number][this].
 * @receiver The first number
 * @see PositiveInfinity
 * @return [sequences.BigIntegerRange]
 */
infix fun Number.until(@Suppress("UNUSED_PARAMETER") infinity: Infinity) = range(this.toBigInteger(), null)

/**
 * Infinite increasing [sequences.BigIntegerRange] starting with the given [number][this].
 * @receiver The first number
 * @see PositiveInfinity
 * @return [sequences.BigIntegerRange]
 */
infix fun Number.until(@Suppress("UNUSED_PARAMETER") infinity: PositiveInfinity) = range(this.toBigInteger(), null)

/**
 * Infinite increasing [sequences.BigIntegerRange] starting with the given [number][this].
 * @receiver The first number
 * @see PositiveInfinity
 * @return [sequences.BigIntegerRange]
 */
operator fun Number.rangeTo(@Suppress("UNUSED_PARAMETER") infinity: Infinity) = range(this.toBigInteger(), null)

/**
 * Infinite increasing [sequences.BigIntegerRange] starting with the given [number][this].
 * @receiver The first number
 * @see PositiveInfinity
 * @return [sequences.BigIntegerRange]
 */
operator fun Number.rangeTo(@Suppress("UNUSED_PARAMETER") infinity: PositiveInfinity) = range(this.toBigInteger(), null)

/**
 * Infinite decreasing [sequences.BigIntegerRange] starting with the given [number][this].
 * @receiver The first number
 * @see NegativeInfinity
 * @return [sequences.BigIntegerProgression]
 */
infix fun Number.downTo(@Suppress("UNUSED_PARAMETER") negativeInfinity: NegativeInfinity) =
    progression(this, null, -BigInteger.ONE)

/**
 * Infinite decreasing [sequences.BigIntegerRange] starting with the given [number][this].
 * @receiver The first number
 * @see NegativeInfinity
 * @return [sequences.BigIntegerProgression]
 */
infix fun Number.downTo(@Suppress("UNUSED_PARAMETER") negativeInfinity: Infinity) =
    progression(this, null, -BigInteger.ONE)