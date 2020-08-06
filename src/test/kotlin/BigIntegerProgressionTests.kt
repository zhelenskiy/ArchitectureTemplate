import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.IllegalArgumentException
import kotlin.comparisons.naturalOrder

class BigIntegerProgressionTests {
    @Test
    fun `empty progression`() {
        assertTrue(EmptyRange.isEmpty())
        assertFalse(EmptyRange.isNotEmpty())
        assertTrue((1..Infinity).isNotEmpty())
        assertTrue((1..5.toBigInteger()).isNotEmpty())
        assertTrue((1..1.toBigInteger()).isNotEmpty())
        assertTrue((1..0.toBigInteger()).isEmpty())
        assertFalse((1..0.toBigInteger()).isNotEmpty())

        assertTrue((1..0.toBigInteger()) is EmptyRange)
        assertTrue((1..1.toBigInteger()) !is EmptyRange)
        assertTrue((1..2.toBigInteger()) !is EmptyRange)
    }

    @Test
    fun `right instance`() {
        assertTrue(1..1.toBigInteger() step 3 is BigIntegerRange)
        assertTrue(1..1.toBigInteger() step 3 is SingleRange)
        assertTrue(1..1.toBigInteger() step 3 !is EmptyRange)
        assertTrue(1..2.toBigInteger() step 3 is BigIntegerRange)
        assertTrue(1..2.toBigInteger() step 3 is SingleRange)
        assertTrue(1..2.toBigInteger() step 3 !is EmptyRange)
        assertTrue(1..0.toBigInteger() step 3 is BigIntegerRange)
        assertTrue(1..0.toBigInteger() step 3 !is SingleRange)
        assertTrue(1..0.toBigInteger() step 3 is EmptyRange)
        assertTrue(1..0.toBigInteger() is EmptyRange)
        assertTrue(1..2.toBigInteger() step 1 is BigIntegerRange)
        assertTrue(1..2.toBigInteger() step 1 !is SingleRange)
        assertTrue(1..2.toBigInteger() step 1 !is EmptyRange)
        assertTrue(1..2.toBigInteger() step 2 is BigIntegerRange)
        assertTrue(1..2.toBigInteger() step 2 is SingleRange)
        assertTrue(1..10.toBigInteger() step 2 !is BigIntegerRange)
    }

    @Test
    fun `last element`() {
        assertNull((1..Infinity).last)
        assertNotNull((1..3.toBigInteger()).last)
        assertThrows(
            IllegalArgumentException::class.java,
            { progression(1, null, 0) },
            "Step must be non-zero."
        )
        assertEquals(progression(1, -100, 1), progression(1, -200, 1))
        assertEquals(BigInteger.ZERO, progression(1, -100, 2).last)
        assertEquals(BigInteger.ZERO, progression(-1, 100, -2).last)
        assertEquals(BigInteger.valueOf(5L), (1..5.toBigInteger()).last)
        assertEquals(BigInteger.valueOf(5L), (1 until 6.toBigInteger()).last)
        assertEquals(BigInteger.valueOf(1), (5L downTo 1.toBigInteger()).last)

        for (end in 0..6)
            assertEquals(0.toBigInteger(), progression(0, end, 7).last)
        for (end in 7..13)
            assertEquals(7.toBigInteger(), progression(0, end, 7).last)
        for (end in 14..20)
            assertEquals(14.toBigInteger(), progression(0, end, 7).last)

        for (end in 0..6)
            assertEquals(0.toBigInteger(), progression(0, -end, -7).last)
        for (end in 7..13)
            assertEquals((-7).toBigInteger(), progression(0, -end, -7).last)
        for (end in 14..20)
            assertEquals((-14).toBigInteger(), progression(0, -end, -7).last)

        assertEquals((1..10.toBigInteger() step 2).last, (1..10.toBigInteger() step 2).last())
        assertThrows(NoSuchElementException::class.java, { EmptyRange.last() }, "Empty range")
        assertThrows(NoSuchElementException::class.java, { (1..Infinity).last() }, "Infinite range")
    }

    @Test
    fun `(in)finiteness`() {
        assertTrue((1..Infinity).isInfinite())
        assertFalse((1..Infinity).isFinite())
        assertTrue((1 downTo -Infinity).isInfinite())
        assertFalse((1 downTo -Infinity).isFinite())

        assertFalse((1..5.toBigInteger()).isInfinite())
        assertTrue((1..5.toBigInteger()).isFinite())
        assertFalse((5..1.toBigInteger()).isInfinite())
        assertTrue((5..1.toBigInteger()).isFinite())
        assertFalse((1 downTo 5.toBigInteger()).isInfinite())
        assertTrue((1 downTo 5.toBigInteger()).isFinite())
        assertFalse((5 downTo 1.toBigInteger()).isInfinite())
        assertTrue((5 downTo 1.toBigInteger()).isFinite())
    }

    @Test
    fun count() {
        assertNull((1..Infinity).count())
        assertNull((1 downTo -Infinity).count())
        assertEquals(BigInteger.ZERO, (1..0.toBigInteger()).count())
        assertEquals(BigInteger.ONE, progression(0, 6, 7).count())
        assertEquals(BigInteger.TWO, progression(0, 7, 7).count())
        assertEquals(BigInteger.TWO, progression(0, 8, 7).count())
        assertEquals(BigInteger.ONE, progression(0, -6, -7).count())
        assertEquals(BigInteger.TWO, progression(0, -7, -7).count())
        assertEquals(BigInteger.TWO, progression(0, -8, -7).count())

        assertTrue((1..1.toBigInteger()).isSingle())
        assertFalse((1..2.toBigInteger()).isSingle())
        assertFalse((1..0.toBigInteger()).isSingle())
        assertTrue((1 downTo 1.toBigInteger()).isSingle())
        assertFalse((1 downTo 2.toBigInteger()).isSingle())
        assertFalse((1 downTo 0.toBigInteger()).isSingle())
        assertFalse((1..Infinity).isSingle())
        assertFalse((1 downTo -Infinity).isSingle())
    }

    @Test
    fun equality() {
        assertSame(EmptyRange, EmptyRange)
        assertEquals(EmptyRange, 1..-100.toBigInteger())
        assertNotEquals(EmptyRange, 1..1000.toBigInteger())
        assertNotEquals(1..100.toBigInteger(), 1..1000.toBigInteger())
        assertEquals(1..1000.toBigInteger(), 1..1000.toBigInteger())
        assertEquals(progression(1, 1000, 1), 1..1000.toBigInteger())
        assertEquals(progression(1, 1000, 10), progression(1, 1000, 10))
        assertNotEquals(progression(1, null, 10), progression(1, 1000, 10))
        assertEquals(progression(1, null, 10), progression(1, null, 10))
        assertNotEquals(progression(1, null, 10), progression(1, null, 100))
        assertEquals(1..Infinity, 1L..Infinity)
        assertNotEquals(1..Infinity, 2L..Infinity)
        assertEquals(1 downTo NegativeInfinity, 1L downTo -Infinity)
        assertNotEquals(1 downTo NegativeInfinity, 2L downTo -Infinity)

        assertEquals(1..100.toBigInteger() step 1000, 1..1000.toBigInteger() step 100000)
        assertEquals(1..100.toBigInteger() step 1000, 1 downTo (-1000).toBigInteger() step 100000)
    }

    @Test
    fun `secondary constructors`() {
        val res = progression(BigInteger.ONE, BigInteger.TEN, BigInteger.TWO)
        assertEquals(res, progression(1..10 step 2))
        assertEquals(res, progression(1L..10L step 2))
        assertEquals(res, progression(1.toBigInteger(), 10.toBigInteger(), 2L))

        val posInfRes = progression(BigInteger.ONE, null, BigInteger.TWO)
        val negInfRes = progression(BigInteger.ONE, null, -BigInteger.TWO)
        assertEquals(posInfRes, progression(posInfRes))

        assertEquals(posInfRes, progression(1.toBigInteger(), null, 2L))
        assertEquals(negInfRes, progression(1.toBigInteger(), null, -2L))

        assertEquals(posInfRes, progression(1.toBigInteger(), Double.POSITIVE_INFINITY, 2L))
        assertNotEquals(posInfRes, progression(1.toBigInteger(), Double.NEGATIVE_INFINITY, 2L))
        assertEquals(negInfRes, progression(1.toBigInteger(), Double.NEGATIVE_INFINITY, -2L))
        assertNotEquals(negInfRes, progression(1.toBigInteger(), Double.POSITIVE_INFINITY, -2L))

        assertEquals(posInfRes, progression(1.toBigInteger(), Float.POSITIVE_INFINITY, 2L))
        assertNotEquals(posInfRes, progression(1.toBigInteger(), Float.NEGATIVE_INFINITY, 2L))
        assertEquals(negInfRes, progression(1.toBigInteger(), Float.NEGATIVE_INFINITY, -2L))
        assertNotEquals(negInfRes, progression(1.toBigInteger(), Float.POSITIVE_INFINITY, -2L))
    }

    @Test
    fun `simple iterating`() {
        assertIterableEquals((1..3).map { it.toBigInteger() }, (1..3.toBigInteger()).asIterable())
        assertIterableEquals((1..3).map { it.toBigInteger() }, (1..Infinity).take(3).asIterable())
        assertIterableEquals(listOf<BigInteger>(), (1..0.toBigInteger()).asIterable())
        assertIterableEquals(listOf(3, 2, 1).map { it.toBigInteger() }, (3.toBigInteger() downTo 1.toBigInteger()).asIterable())
        assertIterableEquals(listOf(3, 2, 1).map { it.toBigInteger() }, (3 downTo -Infinity).take(3).asIterable())
        assertIterableEquals(listOf<BigInteger>(), (-1 downTo 0.toBigInteger()).asIterable())
    }

    @Test
    fun `average number`() {
        assertEquals(Double.POSITIVE_INFINITY, (1..Infinity).average())
        assertEquals(Double.NEGATIVE_INFINITY, (1 downTo -Infinity).average())
        assertEquals(Double.NaN, (1..0.toBigInteger()).average())
        assertEquals(BigDecimal.valueOf(2.5), (1..4.toBigInteger()).average())
        assertEquals(BigDecimal.valueOf(2), (0..4.toBigInteger()).average())
    }

    @Test
    fun `sum of the progression`() {
        assertEquals(Double.POSITIVE_INFINITY, (1..Infinity).sum())
        assertEquals(Double.NEGATIVE_INFINITY, (1 downTo -Infinity).sum())
        assertEquals(BigInteger.ZERO, (1..0.toBigInteger()).sum())
        assertEquals(BigInteger.TEN, (1..4.toBigInteger()).sum())
        assertEquals(BigInteger.TEN, (0..4.toBigInteger()).sum())
    }

    @Test
    fun `hash codes`() {
        assertEquals(progression(1, 2, 3).hashCode(), progression(1, 2, 3).hashCode())
        assertNotEquals(progression(1, 2, 3).hashCode(), progression(1, 2, -3).hashCode())
        assertNotEquals(progression(1, 2, 3).hashCode(), progression(1, -2, 3).hashCode())
        assertNotEquals(progression(1, 2, 3).hashCode(), progression(-1, 2, 3).hashCode())
        assertEquals(progression(1, null, 3).hashCode(), progression(1, null, 3).hashCode())
        assertNotEquals(progression(1, null, 3).hashCode(), progression(1, null, -3).hashCode())
        assertNotEquals(progression(1, null, 3).hashCode(), progression(-1, null, 3).hashCode())
        assertEquals(0, progression(-1, -2, 3).hashCode())
        assertEquals(0, progression(-1, -3, 3).hashCode())
        assertEquals(0, progression(-1, -4, 3).hashCode())
        assertEquals(0, progression(-1, -4, 30).hashCode())
        assertEquals(0, progression(-2, 4, -30).hashCode())

        assertEquals((1..100.toBigInteger() step 1000).hashCode(), (1..1000.toBigInteger() step 100000).hashCode())
        assertEquals((1..100.toBigInteger() step 1000).hashCode(), (1 downTo (-1000).toBigInteger() step 100000).hashCode())
    }

    @Test
    fun `string representation`() {
        assertTrue(1..0.toBigInteger() is EmptyRange)
        assertEquals("[]", (1..0.toBigInteger()).toString())
        assertEquals("[1..3]", (1..3.toBigInteger()).toString())
        assertEquals("[-1 downTo -3]", (-1 downTo (-3).toBigInteger()).toString())
        assertEquals("[0..2] step 2", progression(0, 3, 2).toString())
        assertEquals("[0 downTo -2] step 2", progression(0, -3, -2).toString())
        assertEquals("[0..+∞)", progression(0, null, 1).toString())
        assertEquals("[0 downTo -∞)", progression(0, null, -1).toString())
        assertEquals("[0..+∞) step 2", progression(0, null, 2).toString())
        assertEquals("[0 downTo -∞) step 2", progression(0, null, -2).toString())

        assertEquals("[5]", (5..5.toBigInteger()).toString())
        assertEquals("[5]", (5..5.toBigInteger() step 5).toString())
        assertEquals("[5]", (5 downTo 5.toBigInteger()).toString())
        assertEquals("[5]", (5 downTo 5.toBigInteger() step 5).toString())
    }

    @Test
    fun `operator in`() {
        assertFalse(0 in EmptyRange)

        val posRange = progression(2, 10, 4)
        val negRange = progression(10, 2, -4)
        for (n in -10..10) {
            when (n) {
                2, 6, 10 -> {
                    assertTrue(n in posRange)
                    assertTrue(n in negRange)
                }
                else -> {
                    assertTrue(n !in posRange)
                    assertTrue(n !in negRange)
                }
            }
        }
        val infNegRange = 0 downTo -Infinity step 5
        for (i in -100..100)
            assertEquals(i <= 0 && i % 5 == 0, i in infNegRange)
        val infPosRange = 0..Infinity step 5
        for (i in -100..100)
            assertEquals(i >= 0 && i % 5 == 0, i in infPosRange)


        assertTrue(EmptyRange in EmptyRange)
        assertTrue(2..0.toBigInteger() in EmptyRange)
        assertTrue(2..2 !in EmptyRange)
        assertTrue(2..2 in 2..2.toBigInteger())
        assertTrue(2..2 in 2..2.toBigInteger() step 2)
        assertTrue(2..2 step 2 in 2..2.toBigInteger())
        assertTrue(2..Infinity !in 2..100.toBigInteger())
        assertTrue(2..Infinity !in 2 downTo -Infinity)
        assertTrue(2..10.toBigInteger() !in 2..9.toBigInteger())
        assertTrue(2..10.toBigInteger() !in 3..10.toBigInteger())
        assertTrue(2..10.toBigInteger() step 2 in 2..10.toBigInteger())
        assertTrue(2..10.toBigInteger() step 2 in 2..10.toBigInteger() step 2)
        assertTrue(2..10.toBigInteger() !in 2..10.toBigInteger() step 2)
        assertTrue(2..10.toBigInteger() in 2..10.toBigInteger())
        assertTrue(0..30.toBigInteger() step 6 !in 0..30.toBigInteger() step 5)
        assertTrue(0..30.toBigInteger() step 5 !in 0..30.toBigInteger() step 6)
        assertTrue(0..30.toBigInteger() step 6 in 0..30.toBigInteger() step 6)
        assertTrue(0..30.toBigInteger() step 5 in 0..30.toBigInteger() step 5)

        for (start1 in -5..5)
            for (finish1 in (-5..5).map { it.toBigInteger() })
                for (step1 in 1..5)
                    for (start2 in -5..5)
                        for (finish2 in (-5..5).map { it.toBigInteger() })
                            for (step2 in 1..5) {
                                assertEquals(
                                    (start1..finish1 step step1).all { it in start2..finish2 step step2 },
                                    start1..finish1 step step1 in start2..finish2.toBigInteger() step step2
                                )
                                assertEquals(
                                    (start1..finish1 step step1).all { it in start2 downTo finish2 step step2 },
                                    start1..finish1 step step1 in start2 downTo finish2.toBigInteger() step step2
                                )
                                assertEquals(
                                    (start1 downTo finish1 step step1).all { it.toInt() in start2..finish2 step step2 },
                                    start1 downTo finish1 step step1 in start2..finish2.toBigInteger() step step2
                                )
                                assertEquals(
                                    (start1 downTo finish1 step step1).all { it in start2 downTo finish2 step step2 },
                                    start1 downTo finish1 step step1 in start2 downTo finish2.toBigInteger() step step2
                                )
                            }

        for (start1 in -5..5)
            for (step1 in 1..5)
                for (start2 in -5..5)
                    for (step2 in 1..5) {
                        assertEquals(
                            (start1..Infinity step step1).take(100).all { it in start2..Infinity step step2 },
                            start1..Infinity step step1 in start2..Infinity step step2
                        )
                        assertEquals(
                            (start1..Infinity step step1).take(100).all { it in start2 downTo -Infinity step step2 },
                            start1..Infinity step step1 in start2 downTo -Infinity step step2
                        )
                        assertEquals(
                            (start1 downTo -Infinity step step1).take(100)
                                .all { it.toInt() in start2..Infinity step step2 },
                            start1 downTo -Infinity step step1 in start2..Infinity step step2
                        )
                        assertEquals(
                            (start1 downTo -Infinity step step1).take(100)
                                .all { it in start2 downTo -Infinity step step2 },
                            start1 downTo -Infinity step step1 in start2 downTo -Infinity step step2
                        )
                    }
    }

    @Test
    fun distinct() {
        val a = progression(1, 200, 3)
        assertIterableEquals(a.asIterable(), a.distinct())
    }

    @Test
    fun drop() {
        assertThrows(IllegalArgumentException::class.java) { EmptyRange.drop(-1) }
        assertSame(EmptyRange, EmptyRange.drop(4))
        assertSame(EmptyRange, EmptyRange.drop(4.toBigInteger()))
        assertEquals(4..Infinity step 2, (0..Infinity step 2).drop(2))
        assertEquals(0 downTo -Infinity step 2, (4 downTo -Infinity step 2).drop(2))

        assertEquals((4..10.toBigInteger() step 2), (0..10.toBigInteger() step 2).drop(2))
        assertEquals((6 downTo 0.toBigInteger() step 2), (10 downTo 0.toBigInteger() step 2).drop(2))
        assertTrue((0..10.toBigInteger() step 2).drop(20).isEmpty())
        assertTrue((10.toBigInteger() downTo 0.toBigInteger() step 2).drop(20).isEmpty())
    }

    @Test
    fun `elementAt(Or(Else|Null))`() {
        for (method in listOf<BigIntegerProgression.(Int) -> BigInteger>(
            { this.elementAtOrElse(it) { BigInteger.ZERO } },
            { this.elementAtOrNull(it)!! },
            { this.elementAt(it) })
        ) {
            val range = 1..Infinity step 10
            assertThrows(IllegalArgumentException::class.java) { range.method(-10) }
            for (i in 0..100)
                assertEquals((1 + 10 * i).toBigInteger(), range.method(i))
        }
        val els = BigInteger.valueOf(12)
        assertSame(els, EmptyRange.elementAtOrElse(0) { els })
        assertNull(EmptyRange.elementAtOrNull(0))
        assertThrows(NoSuchElementException::class.java) { EmptyRange.elementAt(0) }
    }

    @Test
    fun `(last)IndexOf`() {
        val myRange = 0..100.toBigInteger() step 5
        val range = 0..100 step 5
        for (i in -20..200) {
            assertEquals(range.indexOf(i).toBigInteger(), myRange.indexOf(i))
            assertEquals(range.indexOf(i).toBigInteger(), myRange.indexOf(i.toBigInteger()))
            assertEquals(myRange.indexOf(i), myRange.lastIndexOf(i))
        }
    }

    @Test
    fun lastOrNull() {
        assertEquals(Double.POSITIVE_INFINITY, (1..Infinity).lastOrNull())
        assertEquals(Double.NEGATIVE_INFINITY, (1 downTo -Infinity).lastOrNull())
        assertNull(EmptyRange.lastOrNull())
        assertEquals(-BigInteger.TEN, (0 downTo (-11).toBigInteger() step 2).lastOrNull())
    }

    @Test
    fun `minimum, maximum`() {
        assertNull(EmptyRange.min())
        assertNull(EmptyRange.max())
        assertEquals(BigInteger.TWO, (2..10.toBigInteger()).min())
        assertEquals(BigInteger.TEN, (2..10.toBigInteger()).max())
        assertEquals(BigInteger.TWO, (10 downTo 2.toBigInteger()).min())
        assertEquals(BigInteger.TEN, (10 downTo 2.toBigInteger()).max())
    }

    @Test
    fun `+`() {
        assertEquals(1..Infinity, (1..Infinity) + 3)
        assertEquals(1..Infinity, (1..Infinity) + arrayOf(2, 3))
        assertEquals(1..Infinity, (1..Infinity) + listOf(2, 3))
        assertEquals(1..Infinity, (1..Infinity) + sequenceOf(2, 3))

        val range = 1..5.toBigInteger() step 2
        assertIterableEquals(range.asIterable() + 3.toBigInteger(), (range + 3).asIterable())
        assertIterableEquals(
            range.asIterable() + arrayOf(2, 3).map { it.toBigInteger() },
            (range + arrayOf(2, 3)).asIterable()
        )
        assertIterableEquals(
            range.asIterable() + listOf(2, 3).map { it.toBigInteger() },
            (range + listOf(2, 3)).asIterable()
        )
        assertIterableEquals(
            range.asIterable() + sequenceOf(2, 3).map { it.toBigInteger() },
            (range + sequenceOf(2, 3)).asIterable()
        )

        assertIterableEquals(range.asIterable().plusElement(3.toBigInteger()), range.plusElement(3).asIterable())
    }

    @Test
    fun reversed() {
        assertEquals(EmptyRange, EmptyRange.reversed())
        assertNull((1..Infinity step 5).reversed())
        assertNull((1 downTo -Infinity step 5).reversed())
        assertEquals(50 downTo 0.toBigInteger() step 5, (0..50.toBigInteger() step 5).reversed())
        assertEquals(0..50.toBigInteger() step 5, (50 downTo 0.toBigInteger() step 5).reversed())
    }

    @Test
    fun flip() {
        assertNull(EmptyRange.flip())
        assertEquals(1 downTo -Infinity step 5, (1..Infinity step 5).flip())
        assertEquals(1..Infinity step 5, (1 downTo -Infinity step 5).flip())
        assertNull((0..50.toBigInteger() step 5).flip())
        assertNull((50 downTo 0.toBigInteger() step 5).flip())
    }

    @Test
    fun step() {
        assertThrows(IllegalArgumentException::class.java, { 1..Infinity step -1 }, "Step must be positive, was: -1.")
        assertThrows(IllegalArgumentException::class.java, { 1..Infinity step 0 }, "Step must be positive, was: 0.")
        assertEquals(progression(0, 300, 6), progression(0, 300, 30) step 6)
        assertEquals(progression(0, 300, -6), progression(0, 300, -30) step 6)
    }

    @Test
    fun take() {
        assertThrows(
            IllegalArgumentException::class.java,
            { (1..Infinity).take(-5) },
            "Can not take less than 0 elements"
        )
        val posRange = 1..5.toBigInteger() step 2
        val negRange = 5 downTo 1.toBigInteger() step 2
        val infPosRange = 1..Infinity step 2
        val infNegRange = 5 downTo -Infinity step 2

        assertSame(posRange, posRange.take(200))
        assertSame(negRange, negRange.take(200))
        assertEquals(1..1.toBigInteger() step 2, posRange.take(1))
        assertEquals(5 downTo 5.toBigInteger() step 2, negRange.take(1))
        assertEquals(1..1.toBigInteger() step 2, infPosRange.take(1))
        assertEquals(5 downTo 5.toBigInteger() step 2, infNegRange.take(1))
    }

    @Test
    fun extend() {
        val single = 5..5.toBigInteger() step 100
        assertEquals(single, single step 2)
        assertEquals(single, 5 downTo 5.toBigInteger())

        assertNotEquals(15..15.toBigInteger(), single.extend(10))

        val infPosRange = 1..Infinity step 2
        val infNegRange = 5 downTo -Infinity step 2

        assertThrows(
            IllegalArgumentException::class.java,
            { (1..5.toBigInteger()).extend(-3) },
            "Can not extend by less than 0 elements"
        )

        assertSame(EmptyRange, EmptyRange.extend(10))
        assertSame(EmptyRange, EmptyRange.extend(10.toBigInteger()))

        assertSame(infNegRange, infNegRange.extend(10))
        assertSame(infPosRange, infPosRange.extend(10))

        assertEquals(0..10.toBigInteger() step 2, (0..4.toBigInteger() step 2).extend(3))
        assertEquals(10 downTo 0.toBigInteger() step 2, (10 downTo 6.toBigInteger() step 2).extend(3))
    }

    @Test
    fun `shl, shr`() {
        assertSame(EmptyRange, EmptyRange shl 10)
        assertSame(EmptyRange, EmptyRange shr 10)
        assertEquals(range(5, 5), range(0, 0) shr 5)
        assertEquals(range(0, 0), range(5, 5) shl 5)
        assertEquals(2 downTo -Infinity step 5, 7 downTo -Infinity step 5 shr 1)
        assertEquals(2 downTo -Infinity step 5, -3 downTo -Infinity step 5 shl 1)
        assertEquals(2 downTo (-8).toBigInteger() step 5, 7 downTo (-3).toBigInteger() step 5 shr 1)
        assertEquals(2 downTo (-8).toBigInteger() step 5, -3 downTo (-13).toBigInteger() step 5 shl 1)
    }

    @Test
    fun `Forbidden toCollection's`() {
        for (voidMethodCall in listOf<(BigIntegerProgression) -> Unit>(
            { it.toCollection(mutableListOf()) },
            { it.toList() },
            { it.toMutableList() },
            { it.toHashSet() },
            { it.toMutableSet() },
            { it.toSet() },
            { it.toSortedSet() },
            { it.toSortedSet(naturalOrder()) }
        )) {
            assertThrows(InfiniteRangeException::class.java) { voidMethodCall(1..Infinity) }
        }
    }

    @Test
    fun components() {
        val (a1, a2, a3, a4, a5) = 1..Infinity
        assertIterableEquals((1..5).map { it.toBigInteger() }, listOf(a1, a2, a3, a4, a5))
    }

    @Suppress("USELESS_IS_CHECK") //should highlight all when no suppress
    @Test
    fun `empty range`() {
        assertTrue(EmptyRange intersect 1..1 is EmptyRange)
        assertTrue(EmptyRange intersect ((1..1) as IntProgression) is EmptyRange)
        assertTrue(EmptyRange intersect 1L..1L is EmptyRange)
        assertTrue(EmptyRange intersect ((1L..1L) as LongProgression) is EmptyRange)
        assertTrue(EmptyRange intersect range(1, 1) is EmptyRange)
        assertTrue(EmptyRange intersect (range(1, 1) as BigIntegerProgression) is EmptyRange)
        assertTrue(range(1, 1) intersect EmptyRange is EmptyRange)
        assertTrue(EmptyRange.extend(1) is EmptyRange)
        assertTrue(EmptyRange.shl(1) is EmptyRange)
        assertTrue(EmptyRange.shr(1) is EmptyRange)
        assertTrue(EmptyRange.drop(1) is EmptyRange)
        assertTrue(EmptyRange.step(1) is EmptyRange)
        assertTrue(EmptyRange.take(1) is EmptyRange)

        assertEquals("[]", EmptyRange.toString())
    }

    @Suppress("USELESS_IS_CHECK")
    @Test
    fun `single range`() {
        assertEquals(SingleRange(5.toBigInteger()), SingleRange(5))

        assertEquals("[5]", SingleRange(5).toString())

        assertTrue(SingleRange(5) shl 5 is SingleRange)
        assertEquals(SingleRange(0), SingleRange(5) shl 5)
        assertTrue(SingleRange(5) shr 5 is SingleRange)
        assertEquals(SingleRange(10), SingleRange(5) shr 5)
        assertTrue(SingleRange(5) step 5 is SingleRange)
        assertEquals(SingleRange(5), SingleRange(5) step 5)

    }
}
