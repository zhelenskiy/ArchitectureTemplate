import java.math.BigInteger

fun IntProgression.isInfinite() = false
fun LongProgression.isInfinite() = false
fun IntProgression.isFinite() = true
fun LongProgression.isFinite() = true
fun IntProgression.isNotEmpty() = !this.isEmpty()
fun LongProgression.isNotEmpty() = !this.isEmpty()

fun IntProgression.count(): Int = progression(this).count()!!.toInt()
fun LongProgression.count(): Long = progression(this).count()!!.toLong()
fun IntProgression.isSingle(): Boolean = first == last
fun LongProgression.isSingle(): Boolean = first == last
fun IntProgression.average(): Int? = if (this.isNotEmpty()) first + (last - first) / 2 else null
fun LongProgression.average(): Long? = if (this.isNotEmpty()) first + (last - first) / 2 else null
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
    return IntProgression.fromClosedRange(big.first.toInt(), big.last!!.toInt(), big.step.toInt())
}

fun LongProgression.drop(n: Number): LongProgression {
    val big = progression(this).drop(n)
    return LongProgression.fromClosedRange(big.first.toLong(), big.last!!.toLong(), big.step.toLong())
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

operator fun IntProgression.component1(): BigInteger = elementAt(0)
operator fun IntProgression.component2(): BigInteger = elementAt(1)
operator fun IntProgression.component3(): BigInteger = elementAt(2)
operator fun IntProgression.component4(): BigInteger = elementAt(3)
operator fun IntProgression.component5(): BigInteger = elementAt(4)

operator fun LongProgression.component1(): BigInteger = elementAt(0)
operator fun LongProgression.component2(): BigInteger = elementAt(1)
operator fun LongProgression.component3(): BigInteger = elementAt(2)
operator fun LongProgression.component4(): BigInteger = elementAt(3)
operator fun LongProgression.component5(): BigInteger = elementAt(4)