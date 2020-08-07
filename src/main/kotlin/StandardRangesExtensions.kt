import java.math.BigDecimal
import java.math.BigInteger

@Suppress("unused")
fun IntProgression.isInfinite() = false
@Suppress("unused")
fun LongProgression.isInfinite() = false
@Suppress("unused")
fun IntProgression.isFinite() = true
@Suppress("unused")
fun LongProgression.isFinite() = true
fun IntProgression.isNotEmpty() = !this.isEmpty()
fun LongProgression.isNotEmpty() = !this.isEmpty()

fun IntProgression.count(): Long = progression(this).count()!!.toLong()
fun LongProgression.count(): BigInteger = progression(this).count()!!
fun IntProgression.isSingle(): Boolean = first == last
fun LongProgression.isSingle(): Boolean = first == last
fun IntProgression.average(): BigDecimal? =
    if (this.isNotEmpty()) (first.toBigDecimal() + last.toBigDecimal()).divide(BigDecimal.valueOf(2L)) else null

fun LongProgression.average(): BigDecimal? =
    if (this.isNotEmpty()) (first.toBigDecimal() + last.toBigDecimal()).divide(BigDecimal.valueOf(2L)) else null
fun IntProgression.sum(): BigInteger = progression(this).sum() as BigInteger
fun LongProgression.sum(): BigInteger = progression(this).sum() as BigInteger

operator fun IntProgression.contains(subProgression: IntProgression): Boolean = when {
    subProgression.isEmpty() -> true
    subProgression.first !in this -> false
    subProgression.last !in this -> false
    subProgression.isSingle() -> true
    else -> subProgression.step % this.step == 0
}

operator fun IntProgression.contains(subProgression: LongProgression): Boolean =
    LongProgression.fromClosedRange(first.toLong(), last.toLong(), step.toLong()).contains(subProgression)

operator fun IntProgression.contains(subProgression: BigIntegerProgression): Boolean =
    progression(first, last, step).contains(subProgression)


operator fun LongProgression.contains(subProgression: LongProgression): Boolean = when {
    subProgression.isEmpty() -> true
    subProgression.first !in this -> false
    subProgression.last !in this -> false
    subProgression.isSingle() -> true
    else -> subProgression.step % this.step == 0L
}

operator fun LongProgression.contains(subProgression: IntProgression): Boolean = contains(
    LongProgression.fromClosedRange(
        subProgression.first.toLong(),
        subProgression.last.toLong(),
        subProgression.step.toLong()
    )
)

operator fun LongProgression.contains(subProgression: BigIntegerProgression): Boolean =
    progression(first, last, step).contains(subProgression)

fun IntProgression.drop(n: Number): IntProgression {
    val big = progression(this).drop(n)
    return if (big is BigIntegerRange) big.first.toInt()..big.last!!.toInt() else IntProgression.fromClosedRange(big.first.toInt(), big.last!!.toInt(), big.step.toInt())
}

fun LongProgression.drop(n: Number): LongProgression {
    val big = progression(this).drop(n)
    return if (big is BigIntegerRange) big.first.toLong()..big.last!!.toLong() else LongProgression.fromClosedRange(big.first.toLong(), big.last!!.toLong(), big.step.toLong())
}

fun IntRange.drop(n: Number): IntRange {
    val big = progression(this).drop(n)
    return big.first.toInt()..big.last!!.toInt()
}

fun LongRange.drop(n: Number): LongRange {
    val big = progression(this).drop(n)
    return big.first.toLong()..big.last!!.toLong()
}
fun IntProgression.take(n: Number): IntProgression {
    val big = progression(this).take(n)
    return if (big is BigIntegerRange) big.first.toInt()..big.last!!.toInt() else IntProgression.fromClosedRange(big.first.toInt(), big.last!!.toInt(), big.step.toInt())
}

fun LongProgression.take(n: Number): LongProgression {
    val big = progression(this).take(n)
    return if (big is BigIntegerRange) big.first.toLong()..big.last!!.toLong() else LongProgression.fromClosedRange(big.first.toLong(), big.last!!.toLong(), big.step.toLong())
}

fun IntRange.take(n: Number): IntRange {
    val big = progression(this).take(n)
    return big.first.toInt()..big.last!!.toInt()
}

fun LongRange.take(n: Number): LongRange {
    val big = progression(this).take(n)
    return big.first.toLong()..big.last!!.toLong()
}

fun IntProgression.elementAtOrNull(index: Number): BigInteger? = progression(this).elementAtOrNull(index)
fun IntProgression.elementAtOrElse(index: Number, defaultValue: (Number) -> Int): BigInteger =
    progression(this).elementAtOrElse(index) { defaultValue(index).toBigInteger() }

fun IntProgression.elementAt(index: Number): BigInteger = progression(this).elementAt(index)
fun IntProgression.indexOf(element: Number): BigInteger = progression(this).indexOf(element)
fun IntProgression.lastIndexOf(element: Number): BigInteger = indexOf(element)
fun IntProgression.last(): Int = if (isEmpty()) throw NoSuchElementException("Empty range") else last
fun IntProgression.lastOrNull(): Int? = last.takeUnless { this.isEmpty() }

fun LongProgression.elementAtOrNull(index: Number): BigInteger? = progression(this).elementAtOrNull(index)
fun LongProgression.elementAtOrElse(index: Number, defaultValue: (Number) -> Long): BigInteger =
    progression(this).elementAtOrElse(index) { defaultValue(index).toBigInteger() }

fun LongProgression.elementAt(index: Number): BigInteger = progression(this).elementAt(index)
fun LongProgression.indexOf(element: Number): BigInteger = progression(this).indexOf(element)
fun LongProgression.lastIndexOf(element: Number): BigInteger = indexOf(element)
fun LongProgression.last(): Long = if (isEmpty()) throw NoSuchElementException("Empty range") else last
fun LongProgression.lastOrNull(): Long? = last.takeUnless { this.isEmpty() }

fun IntProgression.max(): Int? = when {
    isEmpty() -> null
    step > 0 -> last
    else -> first
}

fun IntProgression.min(): Int? = when {
    isEmpty() -> null
    step > 0 -> first
    else -> last
}

fun LongProgression.max(): Long? = when {
    isEmpty() -> null
    step > 0 -> last
    else -> first
}

fun LongProgression.min(): Long? = when {
    isEmpty() -> null
    step > 0 -> first
    else -> last
}

fun IntProgression.extend(n: Number): BigIntegerProgression = progression(this).extend(n)
fun LongProgression.extend(n: Number): BigIntegerProgression = progression(this).extend(n)

infix fun IntProgression.shl(n: Number): BigIntegerProgression = progression(this) shl n
infix fun LongProgression.shl(n: Number): BigIntegerProgression = progression(this) shl n
infix fun IntProgression.shr(n: Number): BigIntegerProgression = progression(this) shr n
infix fun LongProgression.shr(n: Number): BigIntegerProgression = progression(this) shr n

operator fun IntProgression.component1(): Int = elementAt(0).toInt()
operator fun IntProgression.component2(): Int = elementAt(1).toInt()
operator fun IntProgression.component3(): Int = elementAt(2).toInt()
operator fun IntProgression.component4(): Int = elementAt(3).toInt()
operator fun IntProgression.component5(): Int = elementAt(4).toInt()

operator fun LongProgression.component1(): Long = elementAt(0).toLong()
operator fun LongProgression.component2(): Long = elementAt(1).toLong()
operator fun LongProgression.component3(): Long = elementAt(2).toLong()
operator fun LongProgression.component4(): Long = elementAt(3).toLong()
operator fun LongProgression.component5(): Long = elementAt(4).toLong()