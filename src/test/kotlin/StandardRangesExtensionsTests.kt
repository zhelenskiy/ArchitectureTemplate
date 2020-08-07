import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigInteger

class StandardRangesExtensionsTests {
    @Test
    fun finiteness() {
        assertFalse((1..5).isInfinite())
        assertFalse((1L..5L).isInfinite())
        assertTrue((1..5).isFinite())
        assertTrue((1L..5L).isFinite())
    }

    @Suppress("EmptyRange")
    @Test
    fun emptiness() {
        assertTrue((1..0).isEmpty())
        assertFalse((1..0).isNotEmpty())
        assertFalse((1..1).isEmpty())
        assertTrue((1..1).isNotEmpty())
        assertTrue((1L..0L).isEmpty())
        assertFalse((1L..0L).isNotEmpty())
        assertFalse((1L..1L).isEmpty())
        assertTrue((1L..1L).isNotEmpty())
    }

    @Suppress("EmptyRange")
    @Test
    fun count() {
        assertEquals(0L, (1..0).count())
        assertEquals(1L, (1..1).count())
        assertEquals(4L, (1..4).count())
        assertEquals(Int.MIN_VALUE.toLong() * -2, (Int.MIN_VALUE..Int.MAX_VALUE).count())
        assertEquals(0.toBigInteger(), (1L..0L).count())
        assertEquals(1.toBigInteger(), (1L..1L).count())
        assertEquals(4.toBigInteger(), (1L..4L).count())
        assertEquals(Long.MIN_VALUE.toBigInteger() * -2, (Long.MIN_VALUE..Long.MAX_VALUE).count())
    }

    @Test
    fun `is single`() {
        for (i in 1..5) {
            when (i) {
                2 -> {
                    assertTrue((2..i).isSingle())
                    assertTrue((2.toLong()..i.toLong()).isSingle())
                }
                else -> {
                    assertFalse((2..i).isSingle())
                    assertFalse((2.toLong()..i.toLong()).isSingle())
                }
            }
        }
    }

    @Suppress("EmptyRange")
    @Test
    fun average() {
        assertNull((2..0).average())
        assertEquals(BigDecimal.valueOf(2L), (2..2).average())
        assertEquals(BigDecimal.valueOf(2.5), (2..3).average())
        assertEquals(BigDecimal.valueOf(3L), (2..4).average())
        assertEquals(BigDecimal.valueOf(3.5), (2..5).average())

        assertNull((2L..0L).average())
        assertEquals(BigDecimal.valueOf(2L), (2L..2L).average())
        assertEquals(BigDecimal.valueOf(2.5), (2L..3L).average())
        assertEquals(BigDecimal.valueOf(3L), (2L..4L).average())
        assertEquals(BigDecimal.valueOf(3.5), (2L..5L).average())
    }

    @Suppress("EmptyRange")
    @Test
    fun sum() {
        assertEquals(BigInteger.ZERO, (2..0).sum())
        assertEquals(BigInteger.TEN, (1..4).sum())
        assertEquals(Int.MIN_VALUE.toBigInteger(), (Int.MIN_VALUE..Int.MAX_VALUE).sum())

        assertEquals(BigInteger.ZERO, (2L..0L).sum())
        assertEquals(BigInteger.TEN, (1L..4L).sum())
        assertEquals(Int.MIN_VALUE.toBigInteger(), (Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()).sum())
    }

    @Suppress("EmptyRange")
    @Test
    fun contains() {
        for (start1 in -5..5)
            for (finish1 in -5..5)
                for (step1 in 1..5)
                    for (start2 in -5..5)
                        for (finish2 in -5..5)
                            for (step2 in 1..5) {
                                val intProgression1 = IntProgression.fromClosedRange(start1, finish1, step1)
                                val intProgression2 = IntProgression.fromClosedRange(start2, finish2, step2)
                                val longProgression1 =
                                    LongProgression.fromClosedRange(start1.toLong(), finish1.toLong(), step1.toLong())
                                val longProgression2 =
                                    LongProgression.fromClosedRange(start2.toLong(), finish2.toLong(), step2.toLong())
                                val bigIntegerProgression1 = progression(start1, finish1, step1)
                                val bigIntegerProgression2 = progression(start2, finish2, step2)
                                assertEquals(
                                    bigIntegerProgression1 in bigIntegerProgression2,
                                    intProgression1 in longProgression2
                                )
                                assertEquals(
                                    bigIntegerProgression1 in bigIntegerProgression2,
                                    longProgression1 in longProgression2
                                )
                                assertEquals(
                                    bigIntegerProgression1 in bigIntegerProgression2,
                                    bigIntegerProgression1 in longProgression2
                                )
                                assertEquals(
                                    bigIntegerProgression1 in bigIntegerProgression2,
                                    intProgression1 in intProgression2
                                )
                                assertEquals(
                                    bigIntegerProgression1 in bigIntegerProgression2,
                                    longProgression1 in intProgression2
                                )
                                assertEquals(
                                    bigIntegerProgression1 in bigIntegerProgression2,
                                    bigIntegerProgression1 in intProgression2
                                )
                            }
    }

    @Test
    fun drop() {
        assertTrue((Int.MAX_VALUE.dec()..Int.MAX_VALUE).drop(2).isEmpty())
        assertTrue((Long.MAX_VALUE.dec()..Long.MAX_VALUE).drop(2).isEmpty())

        assertEquals(6..10 step 2, (0..10 step 2).drop(3))
        assertEquals(6L..10L step 2, (0L..10L step 2L).drop(3))

        assertEquals(Int.MAX_VALUE..Int.MAX_VALUE, ((-Int.MAX_VALUE)..Int.MAX_VALUE step 2).drop(Int.MAX_VALUE))
        assertEquals(Long.MAX_VALUE..Long.MAX_VALUE, ((-Long.MAX_VALUE)..Long.MAX_VALUE step 2).drop(Long.MAX_VALUE))

        assertEquals(0..Int.MAX_VALUE, ((-Int.MAX_VALUE)..Int.MAX_VALUE).drop(Int.MAX_VALUE))
        assertEquals(0L..Long.MAX_VALUE, ((-Long.MAX_VALUE)..Long.MAX_VALUE).drop(Long.MAX_VALUE))
    }

    @Test
    fun take() {
        assertEquals(2L, (Int.MAX_VALUE.dec()..Int.MAX_VALUE).take(3).count())
        assertEquals(2.toBigInteger(), (Long.MAX_VALUE.dec()..Long.MAX_VALUE).take(3).count())

        assertEquals(0..4 step 2, (0..10 step 2).take(3))
        assertEquals(0L..4L step 2, (0L..10L step 2L).take(3))

        assertEquals(
            (-Int.MAX_VALUE) until Int.MAX_VALUE step 2,
            ((-Int.MAX_VALUE)..Int.MAX_VALUE step 2).take(Int.MAX_VALUE)
        )
        assertEquals(
            (-Long.MAX_VALUE) until Long.MAX_VALUE step 2,
            ((-Long.MAX_VALUE)..Long.MAX_VALUE step 2).take(Long.MAX_VALUE)
        )

        assertEquals((-Int.MAX_VALUE) until 0, ((-Int.MAX_VALUE)..Int.MAX_VALUE).take(Int.MAX_VALUE))
        assertEquals((-Long.MAX_VALUE) until 0L, ((-Long.MAX_VALUE)..Long.MAX_VALUE).take(Long.MAX_VALUE))
    }

    @Test
    fun `elementAt(Or(Null|Else)|)`() {
        for (start in 0..10)
            for (finish in 0..10)
                for (step in 1..10)
                    for (index in -10..10) {
                        assertEquals(
                            kotlin.runCatching { progression(start, finish, step).elementAt(index) }
                                .getOrElse { it::class },
                            kotlin.runCatching { IntProgression.fromClosedRange(start, finish, step).elementAt(index) }
                                .getOrElse { it::class })
                        assertEquals(
                            kotlin.runCatching { progression(start, finish, step).elementAt(index) }
                                .getOrElse { it::class },
                            kotlin.runCatching {
                                LongProgression.fromClosedRange(start.toLong(), finish.toLong(), step.toLong())
                                    .elementAt(index)
                            }.getOrElse { it::class })
                        assertEquals(
                            progression(start, finish, step).elementAtOrNull(index),
                            IntProgression.fromClosedRange(start, finish, step).elementAtOrNull(index)
                        )
                        assertEquals(
                            progression(start, finish, step).elementAtOrNull(index),
                            LongProgression.fromClosedRange(start.toLong(), finish.toLong(), step.toLong())
                                .elementAtOrNull(index)
                        )
                        assertEquals(
                            progression(start, finish, step).elementAtOrElse(index) { -BigInteger.TEN },
                            IntProgression.fromClosedRange(start, finish, step)
                                .elementAtOrElse(index) { -10 }
                        )
                        assertEquals(
                            progression(start, finish, step).elementAtOrElse(index) { -BigInteger.TEN },
                            LongProgression.fromClosedRange(start.toLong(), finish.toLong(), step.toLong())
                                .elementAtOrElse(index) { -10L }
                        )
                    }

    }

    @Suppress("EmptyRange")
    @Test
    fun `last(OrNull|)`() {
        assertThrows(NoSuchElementException::class.java) { (1..0).last() }
        assertEquals(Int.MAX_VALUE, (1..Int.MAX_VALUE).last())
        assertThrows(NoSuchElementException::class.java) { (1L..0L).last() }
        assertEquals(Long.MAX_VALUE, (1L..Long.MAX_VALUE).last())

        assertNull((1..0).lastOrNull())
        assertEquals(Int.MAX_VALUE, (1..Int.MAX_VALUE).lastOrNull())
        assertNull((1L..0L).lastOrNull())
        assertEquals(Long.MAX_VALUE, (1L..Long.MAX_VALUE).lastOrNull())
    }

    @Test
    fun `(last) index of`() {
        for (x in 1..8)
            when (x) {
                2, 4, 6 -> assertEquals((x / 2).dec().toBigInteger(), (2..6 step 2).indexOf(x))
                else -> assertEquals((-1).toBigInteger(), (2..6 step 2).indexOf(x))
            }

        for (x in 1..8)
            when (x) {
                2, 4, 6 -> assertEquals((x / 2).dec().toBigInteger(), (2..6 step 2).lastIndexOf(x))
                else -> assertEquals((-1).toBigInteger(), (2..6 step 2).lastIndexOf(x))
            }

        for (x in 1..8)
            when (x) {
                2, 4, 6 -> assertEquals((x / 2).dec().toBigInteger(), (2L..6L step 2).indexOf(x))
                else -> assertEquals((-1).toBigInteger(), (2L..6L step 2).indexOf(x))
            }

        for (x in 1..8)
            when (x) {
                2, 4, 6 -> assertEquals((x / 2).dec().toBigInteger(), (2L..6L step 2).lastIndexOf(x))
                else -> assertEquals((-1).toBigInteger(), (2L..6L step 2).lastIndexOf(x))
            }
    }

    @Suppress("EmptyRange")
    @Test
    fun `min, max`() {
        assertEquals(0, (0..Int.MAX_VALUE).min())
        assertEquals(Int.MAX_VALUE, (0..Int.MAX_VALUE).max())
        assertEquals(0L, (0L..Long.MAX_VALUE).min())
        assertEquals(Long.MAX_VALUE, (0L..Long.MAX_VALUE).max())

        assertEquals(0, (Int.MAX_VALUE downTo 0).min())
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE downTo 0).max())
        assertEquals(0L, (Long.MAX_VALUE downTo 0L).min())
        assertEquals(Long.MAX_VALUE, (Long.MAX_VALUE downTo 0L).max())

        assertEquals(0, (0 downTo 0).min())
        assertEquals(0, (0..0).min())
        assertEquals(0, (0 downTo 0).max())
        assertEquals(0, (0..0).max())
        assertEquals(0L, (0L downTo 0L).min())
        assertEquals(0L, (0L..0L).min())
        assertEquals(0L, (0L downTo 0L).max())
        assertEquals(0L, (0L..0L).max())

        assertNull((0 downTo 1).min())
        assertNull((0..-1).min())
        assertNull((0 downTo 1).max())
        assertNull((0..-1).max())
        assertNull((0L downTo 1L).min())
        assertNull((0L..-1L).min())
        assertNull((0L downTo 1L).max())
        assertNull((0L..-1L).max())
    }

    @Suppress("EmptyRange")
    @Test
    fun extend() {
        assertTrue((1..0).extend(1) is EmptyRange)
        assertTrue((1L..0L).extend(1) is EmptyRange)

        assertEquals(range(1..2), (1..1).extend(1))
        assertEquals(range(1..2), (1L..1L).extend(1))

        assertEquals(range(1..5), (1..4).extend(1))
        assertEquals(range(1..5), (1L..4L).extend(1))

        assertEquals(progression(4 downTo 0), (4 downTo 1).extend(1))
        assertEquals(progression(4 downTo 0), (4L downTo 1L).extend(1))
    }

    @Suppress("EmptyRange")
    @Test
    fun shifts() {
        assertTrue(1..0 shl 1 is EmptyRange)
        assertTrue(1L..0L shl 1 is EmptyRange)

        assertEquals(range(0..0), 1..1 shl 1)
        assertEquals(range(0..0), 1L..1L shl 1)

        assertEquals(range(0..3), 1..4 shl 1)
        assertEquals(range(0..3), 1L..4L shl 1)

        assertEquals(progression(5 downTo 2), 4 downTo 1 shl 1)
        assertEquals(progression(5 downTo 2), 4L downTo 1L shl 1)


        assertTrue(1..0 shr 1 is EmptyRange)
        assertTrue(1L..0L shr 1 is EmptyRange)

        assertEquals(range(2..2), 1..1 shr 1)
        assertEquals(range(2..2), 1L..1L shr 1)

        assertEquals(range(2..5), 1..4 shr 1)
        assertEquals(range(2..5), 1L..4L shr 1)

        assertEquals(progression(3 downTo 0), 4 downTo 1 shr 1)
        assertEquals(progression(3 downTo 0), 4L downTo 1L shr 1)
    }

    @Test
    fun components() {
        run {
            val (a, b, c, d, e) = 0..10 step 2
            assertIterableEquals(listOf(0, 2, 4, 6, 8), listOf(a, b, c, d, e))
        }
        run {
            val (a, b, c, d, e) = 0L..10L step 2
            assertIterableEquals(listOf(0L, 2L, 4L, 6L, 8L), listOf(a, b, c, d, e))
        }
    }
}