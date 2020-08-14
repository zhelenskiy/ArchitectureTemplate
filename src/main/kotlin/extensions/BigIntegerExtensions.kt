package extensions

import sequences.progression
import sequences.range
import java.math.BigInteger

fun Number.toBigInteger(): BigInteger = if (this is BigInteger) this else BigInteger.valueOf(this.toLong())


operator fun BigInteger.plus(number: BigInteger): BigInteger = this.toBigInteger().add(number)

operator fun Number.plus(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.add(number)
    else -> this.toBigInteger().add(number)
}

operator fun BigInteger.plus(number: Number): BigInteger = when (number) {
    is BigInteger -> this.add(number)
    else -> this.add(number.toBigInteger())
}


operator fun BigInteger.minus(number: BigInteger): BigInteger = this.toBigInteger().subtract(number)

operator fun Number.minus(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.subtract(number)
    else -> this.toBigInteger().subtract(number)
}

operator fun BigInteger.minus(number: Number): BigInteger = when (number) {
    is BigInteger -> this.subtract(number)
    else -> this.subtract(number.toBigInteger())
}


operator fun BigInteger.times(number: BigInteger): BigInteger = this.toBigInteger().multiply(number)

operator fun Number.times(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.multiply(number)
    else -> this.toBigInteger().multiply(number)
}

operator fun BigInteger.times(number: Number): BigInteger = when (number) {
    is BigInteger -> this.multiply(number)
    else -> this.multiply(number.toBigInteger())
}


operator fun BigInteger.div(number: BigInteger): BigInteger = this.toBigInteger().divide(number)

operator fun Number.div(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.divide(number)
    else -> this.toBigInteger().divide(number)
}

operator fun BigInteger.div(number: Number): BigInteger = when (number) {
    is BigInteger -> this.divide(number)
    else -> this.divide(number.toBigInteger())
}


operator fun BigInteger.rem(number: BigInteger): BigInteger = this.toBigInteger().remainder(number)

operator fun Number.rem(number: BigInteger): BigInteger = when (this) {
    is BigInteger -> this.remainder(number)
    else -> this.toBigInteger().remainder(number)
}

operator fun BigInteger.rem(number: Number): BigInteger = when (number) {
    is BigInteger -> this.remainder(number)
    else -> this.remainder(number.toBigInteger())
}


operator fun Number.compareTo(number: BigInteger): Int = when (this) {
    is BigInteger -> this.compareTo(number)
    else -> this.toBigInteger().compareTo(number)
}

operator fun BigInteger.compareTo(number: Number): Int = when (number) {
    is BigInteger -> this.compareTo(number)
    else -> this.compareTo(number.toBigInteger())
}


operator fun Number.rangeTo(number: BigInteger) = range(this, number)
operator fun BigInteger.rangeTo(number: Number) = range(this, number)
operator fun BigInteger.rangeTo(number: BigInteger) = range(this, number)

infix fun Number.until(number: BigInteger) = range(this, number.toBigInteger().dec())
infix fun BigInteger.until(number: Number) = range(this, number.toBigInteger().dec())
infix fun BigInteger.until(number: BigInteger) = range(this, number.toBigInteger().dec())

infix fun Number.downTo(number: BigInteger) = progression(this, number, -BigInteger.ONE)
infix fun BigInteger.downTo(number: Number) = progression(this, number, -BigInteger.ONE)
infix fun BigInteger.downTo(number: BigInteger) = progression(this, number, -BigInteger.ONE)


object Infinity {
    operator fun unaryPlus() = Infinity
    operator fun unaryMinus() = NegativeInfinity
}

object NegativeInfinity {
    operator fun unaryPlus() = NegativeInfinity
    operator fun unaryMinus() = Infinity
}

infix fun Number.until(@Suppress("UNUSED_PARAMETER") infinity: Infinity) = range(this.toBigInteger(), null)

operator fun Number.rangeTo(@Suppress("UNUSED_PARAMETER") infinity: Infinity) = range(this.toBigInteger(), null)

infix fun Number.downTo(@Suppress("UNUSED_PARAMETER") infinity: NegativeInfinity) =
    progression(this, null, -BigInteger.ONE)