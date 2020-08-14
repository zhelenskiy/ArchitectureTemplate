import extensions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sequences.progression
import sequences.range
import java.math.BigInteger

class BigIntegerExtensionsTests {
    @Test
    fun `'to'-functions`() {
        val big = BigInteger.ONE
        assertSame(big, big.toBigInteger())
        assertEquals(big, 1.toBigInteger().toBigInteger())
    }

    @Test
    fun `+`() {
        assertEquals(BigInteger.TWO, 1 + BigInteger.ONE)
        assertEquals(BigInteger.TWO, BigInteger.ONE + 1)
        assertEquals(BigInteger.TWO, BigInteger.ONE + BigInteger.ONE)
        assertNotEquals(BigInteger.TWO, 1 + 1)
    }

    @Test
    fun `-`() {
        assertEquals(BigInteger.TWO, 2 - BigInteger.ZERO)
        assertEquals(BigInteger.TWO, BigInteger.TWO - 0)
        assertEquals(BigInteger.TWO, BigInteger.TWO - BigInteger.ZERO)
        assertNotEquals(BigInteger.TWO, 2 - 0)
    }

    @Test
    fun `*`() {
        assertEquals(BigInteger.TWO, 2 * BigInteger.ONE)
        assertEquals(BigInteger.TWO, BigInteger.ONE * 2)
        assertEquals(BigInteger.TWO, BigInteger.TWO * BigInteger.ONE)
        assertNotEquals(BigInteger.TWO, 2 * 1)
    }

    @Test
    fun div() {
        assertEquals(BigInteger.TWO, 2 / BigInteger.ONE)
        assertEquals(BigInteger.TWO, BigInteger.TWO / 1)
        assertEquals(BigInteger.TWO, BigInteger.TWO / BigInteger.ONE)
        assertNotEquals(BigInteger.TWO, 2 / 1)
    }

    @Test
    fun `%`() {
        assertEquals(BigInteger.ONE, 1 % BigInteger.TWO)
        assertEquals(BigInteger.ONE, BigInteger.ONE % 2)
        assertEquals(BigInteger.ONE, BigInteger.ONE % BigInteger.TWO)
        assertNotEquals(BigInteger.ONE, 1 % 2)
    }

    @Test
    fun comparing() {
        val expected = BigInteger.ONE.compareTo(BigInteger.TWO)
        assertEquals(expected, 1.compareTo(BigInteger.TWO))
        assertEquals(expected, BigInteger.ONE.compareTo(2))
        assertEquals(expected, BigInteger.ONE.compareTo(BigInteger.TWO))
    }

    @Test
    fun `operators and infix generating functions`() {
        assertEquals(range(1, null), 1..Infinity)
        assertEquals(range(1, null), 1..+Infinity)
        assertEquals(range(1, null), 1..-NegativeInfinity)
        assertEquals(range(1, null), 1..Infinity)
        assertEquals(progression(1, null, 1), 1..Infinity)
        assertEquals(progression(1, null, 1), 1..Infinity)
        assertEquals(progression(1, 5, 1), 1..5.toBigInteger())
        assertEquals(progression(1, 5, 1), 1.toBigInteger()..5)

        assertEquals(range(1, null), 1 until Infinity)
        assertEquals(progression(1, null, 1), 1 until Infinity)
        assertEquals(progression(1, null, 1), 1 until Infinity)
        assertEquals(progression(1, 5, 1), 1 until 6.toBigInteger())
        assertEquals(progression(1, 5, 1), 1.toBigInteger() until 6)

        assertEquals(progression(5, 1, -1), 5L downTo 1.toBigInteger())
        assertEquals(progression(5, 1, -1), 5L.toBigInteger() downTo 1)
        assertEquals(progression(5, null, -1), 5L downTo NegativeInfinity)
        assertEquals(progression(5, null, -1), 5L downTo +NegativeInfinity)
        assertEquals(progression(5, null, -1), 5L downTo -Infinity)
    }
}