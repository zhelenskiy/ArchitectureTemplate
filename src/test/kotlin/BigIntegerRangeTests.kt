import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

class BigIntegerRangeTests {
    @Test
    fun `range-fun`() {
        val infinite = range(1, null)
        assertTrue(infinite !is EmptyRange)
        assertTrue(infinite !is SingleRange)
        assertNull(infinite.last)
        assertEquals(BigInteger.ONE, infinite.first)
        assertTrue(range(BigInteger.TEN, BigInteger.ZERO) is EmptyRange)
        assertTrue(range(1, 1) is SingleRange)
        assertEquals(BigIntegerRange(BigInteger.ONE, BigInteger.ONE), range(1, 1))
        assertEquals(range(1, 1), range(1..1))
        assertEquals(range(1, 1), range(1L..1L))
        assertEquals(BigInteger.ONE, range(1, 1).step)
    }

    @Test
    fun intersect() {
        for (start1 in 1..10)
            for (finish1 in 1..10)
                for (start2 in 1..10)
                    for (finish2 in 1..10)
                        assertIterableEquals(
                            (start1..finish1 intersect start2..finish2).map { it.toBigInteger() },
                            (range(start1, finish1) intersect range(start2, finish2)).asIterable()
                        )
        for (start1 in 1..10)
            for (start2 in 1..10)
                for (finish2 in 1..10)
                    assertIterableEquals(
                        (start1..1000 intersect start2..finish2).map { it.toBigInteger() },
                        (range(start1, null) intersect range(start2, finish2)).takeWhile { it <= 1000 }.asIterable()
                    )
        for (start1 in 1..10)
            for (finish1 in 1..10)
                for (start2 in 1..10)
                    assertIterableEquals(
                        (start1..finish1 intersect start2..1000).map { it.toBigInteger() },
                        (range(start1, finish1) intersect range(start2, null)).takeWhile { it <= 1000 }.asIterable()
                    )

        assertSame(EmptyRange, range(1, 10) intersect EmptyRange)
        assertEquals(range(1, 10) intersect range(2, 12), range(1, 10) intersect range(2, 12))
        assertEquals(range(1, 10) intersect range(2, 12), range(1, 10) intersect 2..12)
        assertEquals(range(1, 10) intersect range(2, 12), range(1, 10) intersect 2L..12L)
    }

    @Test
    fun minus() {
        for (start1 in 1..10)
            for (finish1 in 1..10)
                for (start2 in 1..10)
                    for (finish2 in 1..10)
                        assertIterableEquals(
                            ((start1..finish1) - (start2..finish2)).map { it.toBigInteger() },
                            (range(start1, finish1) - range(start2, finish2)).asIterable(),
                            "${start1..finish1}, ${start2..finish2}"
                        )
        for (start1 in 1..10)
            for (start2 in 1..10)
                for (finish2 in 1..10)
                    assertIterableEquals(
                        ((start1..1000) - (start2..finish2)).map { it.toBigInteger() },
                        (range(start1, null) - range(start2, finish2)).takeWhile { it <= 1000 }.asIterable(),
                        "${start1..Infinity}, ${start2..finish2}"
                    )
        for (start1 in 1..10)
            for (finish1 in 1..10)
                for (start2 in 1..10)
                    assertIterableEquals(
                        ((start1..finish1) - (start2..1000)).map { it.toBigInteger() },
                        (range(start1, finish1) - range(start2, null)).takeWhile { it <= 1000 }.asIterable()
                    )

        assertEquals((2..Infinity) - (3..Infinity), (2..Infinity) subtract (3..Infinity))
        assertIterableEquals(((2..Infinity) - (3..5)).take(1000).asIterable(), ((2..Infinity) subtract (3..5)).take(1000).asIterable())
        assertIterableEquals(((2..Infinity) - (3L..5L)).take(1000).asIterable(), ((2..Infinity) subtract (3L..5L)).take(1000).asIterable())
    }

    @Test
    fun compareTo() {
        assertTrue(range(1, 100) < range(2, 50))
        assertTrue(range(1, 100) > range(1, 50))
        assertTrue(range(1, null) > range(1, 50))
        assertTrue(range(1, 50) < range(1, null))
        assertTrue(range(1, null).compareTo(range(1, null)) == 0)
        assertTrue(range(1, 100).compareTo(range(1, 100)) == 0)
    }
}