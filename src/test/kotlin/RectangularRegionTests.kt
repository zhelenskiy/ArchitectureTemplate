import extensions.Infinity
import extensions.PositiveInfinity
import extensions.rangeTo
import extensions.toBigInteger
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import regions.*
import sequences.EmptyRange
import sequences.range
import java.math.BigInteger

class RectangularRegionTests {
    @Test
    fun allIndexes() {
        assertEquals(0..Infinity, allIndexes)
    }

    @Test
    fun region() {
        assertEquals(Cell(BigInteger.ONE, BigInteger.TWO), region(1, 2))

        assertEquals(EmptyRegion, region(range(2, -2), 2))
        assertEquals(Cell(3, 2), region(range(3, 3), 2))
        assertEquals(Column(2), region(range(0, null), 2))
        assertEquals(ColumnPiece(0..100, 2), region(range(0, 100), 2))
        assertEquals(ColumnPiece(0..100, 2), region(0..100, 2))
        assertEquals(ColumnPiece(0..100, 2), region(0L..100L, 2))
        assertEquals(ColumnPiece(1..Infinity, 2), region(range(1, null), 2))

        assertEquals(EmptyRegion, region(2, range(2, -2)))
        assertEquals(Cell(2, 3), region(2, range(3, 3)))
        assertEquals(Row(2), region(2, range(0, null)))
        assertEquals(RowPiece(2, 0..100), region(2, range(0, 100)))
        assertEquals(RowPiece(2, 0..100), region(2, 0..100))
        assertEquals(RowPiece(2, 0..100), region(2, 0L..100L))
        assertEquals(RowPiece(2, 1..Infinity), region(2, range(1, null)))

        assertEquals(EmptyRegion, region(EmptyRange, EmptyRange))
        assertEquals(EmptyRegion, region(EmptyRange, 0..0))
        assertEquals(EmptyRegion, region(0..0, EmptyRange))

        assertEquals(Cell(3, 2), region(range(3, 3), 2))
        assertEquals(Column(2), region(range(0, null), 2..2))
        assertEquals(ColumnPiece(0..100, 2), region(range(0, 100), 2..2))
        assertEquals(ColumnPiece(0..100, 2), region(0..100, 2..2))
        assertEquals(ColumnPiece(0..100, 2), region(0L..100L, 2..2))
        assertEquals(ColumnPiece(1..Infinity, 2), region(range(1, null), 2..2))

        assertEquals(Cell(2, 3), region(2, range(3, 3)))
        assertEquals(Row(2), region(2, range(0, null)))
        assertEquals(RowPiece(2, 0..100), region(2, range(0, 100)))
        assertEquals(RowPiece(2, 0..100), region(2, 0..100))
        assertEquals(RowPiece(2, 0..100), region(2, 0L..100L))
        assertEquals(RowPiece(2, 1..Infinity), region(2, range(1, null)))

        assertEquals(Cell(2, 3), region(2..2, range(3, 3)))
        assertEquals(Row(2), region(2..2, range(0, null)))
        assertEquals(RowPiece(2, 0..100), region(2..2, range(0, 100)))
        assertEquals(RowPiece(2, 0..100), region(2..2, 0..100))
        assertEquals(RowPiece(2, 0..100), region(2..2, 0L..100L))
        assertEquals(RowPiece(2, 1..Infinity), region(2..2, range(1, null)))

        assertEquals(region(1..2, 1..2), region(1..2, 1..2))
        assertEquals(region(1..2, 1..2), region(1L..2L, 1..2))
        assertEquals(region(1..2, 1..2), region(range(1..2), 1..2))
        assertEquals(region(1..2, 1..2), region(1..2, 1L..2L))
        assertEquals(region(1..2, 1..2), region(1L..2L, 1L..2L))
        assertEquals(region(1..2, 1..2), region(range(1..2), 1L..2L))
        assertEquals(region(1..2, 1..2), region(1..2, range(1..2)))
        assertEquals(region(1..2, 1..2), region(1L..2L, range(1..2)))
        assertEquals(region(1..2, 1..2), region(range(1..2), range(1..2)))
    }

    @Test
    fun cell() {
        assertEquals(Row(2), Cell(2, 3).rowInstance)
        assertEquals(Column(3), Cell(2, 3).columnInstance)
        assertEquals(Cell(2, 3), Cell(Row(2), Column(3)))
        assertEquals(Cell(2, 3), Cell(2, Column(3)))
        assertEquals(Cell(2, 3), Cell(Row(2), 3))
        assertEquals(Cell(2.toBigInteger(), 3.toBigInteger()), Cell(2, 3))

        for (row1 in 0..5)
            for (column1 in 0..5)
                for (row2 in 0..5)
                    for (column2 in 0..5)
                        assertEquals(region(row1..row2, column1..column2), Cell(row1, column1)..Cell(row2, column2))
        for (row1 in 0..5)
            for (column1 in 0..5)
                for (row2 in 0..5)
                    for (column2 in 0..5)
                        assertEquals(
                            Cell(row1, column1)..Cell(row2, column2),
                            Cell(row2, column2) downTo Cell(row1, column1)
                        )
        for (row1 in 0..5)
            for (column1 in 0..5)
                for (row2 in 0..5)
                    for (column2 in 0..5)
                        assertEquals(
                            region(row1 until row2, column1 until column2),
                            Cell(row1, column1) until Cell(row2, column2)
                        )

        assertEquals(region(2..Infinity, 3..Infinity), Cell(2, 3)..Infinity)
        assertEquals(region(2..Infinity, 3..Infinity), Cell(2, 3)..PositiveInfinity)
        assertEquals(region(2..Infinity, 3..Infinity), Cell(2, 3) until Infinity)
        assertEquals(region(2..Infinity, 3..Infinity), Cell(2, 3) until PositiveInfinity)

        assertEquals("Cell(row=2, column=3)", Cell(2, 3).toString())
    }

    @Test
    fun `rectangular region`() {
        `invalid region`()
        finiteness()
        emptiness()
        count()
        equality()
        `hash code`()
        get()
        contains()
        intersect()
        borders()
        `to string`()
    }

    private fun `to string`() {
        assertEquals("Cell(row=2, column=3)..+∞", (Cell(2, 3)..Infinity).toString())
        assertEquals("Cell(row=2, column=0)..+∞", (Row(2)..Infinity).toString())
        assertEquals("Cell(row=0, column=2)..+∞", (Column(2)..Infinity).toString())
        assertEquals("RowPiece(row=2, columns=[3..4])..+∞", (RowPiece(2, 3..4)..Infinity).toString())
        assertEquals("ColumnPiece(rows=[3..4], column=2)..+∞", (ColumnPiece(3..4, 2)..Infinity).toString())
        assertEquals("Row(number=2)", region(2, 0..Infinity).toString())
        assertEquals("Column(number=2)", region(0..Infinity, 2).toString())
        assertEquals("Cell(row=0, column=2)", region(0..0, 2).toString())
        assertEquals("Cell(row=2, column=3)..Cell(row=4, column=5)", region(2..4, 3..5).toString())
        assertEquals("SheetRegion", region(allIndexes, allIndexes).toString())
        assertEquals("∅", region(1..0.toBigInteger(), allIndexes).toString())
    }

    private fun borders() {
        assertEquals(2.toBigInteger(), region(2..3, 4..5).top)
        assertEquals(4.toBigInteger(), region(2..3, 4..5).left)
        assertEquals(3.toBigInteger(), region(2..3, 4..5).bottom)
        assertNull(region(2..Infinity, 4..5).bottom)
        assertEquals(5.toBigInteger(), region(2..3, 4..5).right)
        assertNull(region(2..3, 4..Infinity).right)
    }

    private fun intersect() {
        assertEquals(Cell(3, 5), region(2..3, 4..5) intersect region(3..4, 5..6))
        assertEquals(EmptyRegion, region(2..3, 4..4) intersect region(3..4, 5..6))
        assertEquals(EmptyRegion, EmptyRegion intersect region(3..4, 5..6))
        assertEquals(EmptyRegion, region(3..4, 5..6) intersect EmptyRegion)
        assertEquals(region(3..4, 4), region(2..Infinity, 3..4) intersect region(3..4, 4..Infinity))
        assertEquals(region(3..Infinity, 4), region(2..Infinity, 3..4) intersect region(3..Infinity, 4..Infinity))
    }

    private fun contains() {
        assertTrue(region(2..3, 3..4) in region(2..3, 3..4))
        assertTrue(Row(2) !in region(2..3, 3..4))
        assertTrue(Row(1) !in region(2..3, 3..4))
        assertTrue(Cell(2, 5) !in region(2..3, 3..4))
        assertTrue(Cell(2, 4) in region(2..3, 3..4))
        assertTrue(region(2, 4..5) !in region(2..3, 3..4))
        assertTrue(region(2..4, 4) !in region(2..3, 3..4))
        assertTrue(region(2..4, 4..5) !in region(2..3, 3..4))
    }

    private fun get() {
        val region = region(5..10, 10..15)
        for (subregion in listOf<RectangularRegion.(Int, Int, Int, Int) -> RectangularRegion?>(
            { a, b, c, d -> this[a..b, c..d] },
            { a, b, c, d -> this[a..b, c.toLong()..d.toLong()] },
            { a, b, c, d -> this[a..b, range(c..d)] },
            { a, b, c, d -> this[a.toLong()..b.toLong(), c..d] },
            { a, b, c, d -> this[a.toLong()..b.toLong(), c.toLong()..d.toLong()] },
            { a, b, c, d -> this[a.toLong()..b.toLong(), range(c..d)] },
            { a, b, c, d -> this[range(a..b), c..d] },
            { a, b, c, d -> this[range(a..b), c.toLong()..d.toLong()] },
            { a, b, c, d -> this[range(a..b), range(c..d)] }
        ))
            for (a in -5..7)
                for (b in -5..7)
                    for (c in -5..7)
                        for (d in -5..7)
                            assertEquals(
                                (region((a + 5)..(b + 5), (c + 10)..(d + 10)).takeIf { it in region }),
                                region.subregion(a, b, c, d)
                            )
        for (subregion in listOf<RectangularRegion.(Int, Int, Int) -> RectangularRegion?>(
            { a, b, c -> this[a, b..c] },
            { a, b, c -> this[a, b.toLong()..c.toLong()] },
            { a, b, c -> this[a, range(b..c)] }
        ))
            for (a in -5..7)
                for (b in -5..7)
                    for (c in -5..7)
                        assertEquals(
                            (region(a + 5, (b + 10)..(c + 10)).takeIf { it in region }),
                            region.subregion(a, b, c)
                        )

        for (subregion in listOf<RectangularRegion.(Int, Int, Int) -> RectangularRegion?>(
            { a, b, c -> this[a..b, c] },
            { a, b, c -> this[a.toLong()..b.toLong(), c] },
            { a, b, c -> this[range(a..b), c] }
        ))
            for (a in -5..7)
                for (b in -5..7)
                    for (c in -5..7)
                        assertEquals(
                            (region((a + 5)..(b + 5), c + 10).takeIf { it in region }),
                            region.subregion(a, b, c)
                        )

        for (a in -5..7)
            for (b in -5..7)
                assertEquals(region(a + 5, b + 10).takeIf { it in region }, region[a, b])
    }

    private fun `hash code`() {
        assertTrue(EmptyRegion.hashCode() == EmptyRegion.hashCode())
        assertTrue(region(2..2, 3..0.toBigInteger()).hashCode() == region(3..0.toBigInteger(), 2..Infinity).hashCode())
        assertTrue(region(2..2, 3..0.toBigInteger()).hashCode() == EmptyRegion.hashCode())
        assertTrue(region(2..3, 4..5).hashCode() == region(2..3, 4..5).hashCode())
        assertFalse(region(2..3, 4..6).hashCode() == region(2..3, 4..5).hashCode())
        assertFalse(region(2..3, 4..6).hashCode() == region(2..3, 4..5).hashCode())
        assertFalse(region(2..4, 4..5).hashCode() == region(2..3, 4..5).hashCode())
        assertFalse(region(2..4, 4..6).hashCode() == region(2..3, 4..5).hashCode())
    }

    private fun equality() {
        @Suppress("ReplaceCallWithBinaryOperator")
        assertFalse(EmptyRegion.equals(Unit))
        @Suppress("ReplaceCallWithBinaryOperator")
        assertFalse(EmptyRegion.equals(null))
        assertTrue(EmptyRegion == EmptyRegion)
        assertTrue(region(2..2, 3..0.toBigInteger()) == region(3..0.toBigInteger(), 2..Infinity))
        assertSame(EmptyRegion, region(2..2, 3..0.toBigInteger()))
        assertTrue(region(2..3, 4..5) == region(2..3, 4..5))
        assertFalse(region(2..3, 4..6) == region(2..3, 4..5))
        assertFalse(region(2..3, 4..6) == region(2..3, 4..5))
        assertFalse(region(2..4, 4..5) == region(2..3, 4..5))
        assertFalse(region(2..4, 4..6) == region(2..3, 4..5))
    }

    private fun count() {
        assertEquals(BigInteger.ZERO, EmptyRegion.count())
        assertNull(Row(3).count())
        assertNull(Column(3).count())
        assertEquals(6.toBigInteger(), (Cell(0, 0) until Cell(2, 3)).count())
    }

    private fun emptiness() {
        assertTrue(region(EmptyRange, EmptyRange).isEmpty())
        assertTrue(region(allIndexes, EmptyRange).isEmpty())
        assertTrue(region(EmptyRange, allIndexes).isEmpty())
        assertFalse(region(allIndexes, allIndexes).isEmpty())

        assertFalse(region(EmptyRange, EmptyRange).isNotEmpty())
        assertFalse(region(allIndexes, EmptyRange).isNotEmpty())
        assertFalse(region(EmptyRange, allIndexes).isNotEmpty())
        assertTrue(region(allIndexes, allIndexes).isNotEmpty())
    }

    private fun finiteness() {
        assertTrue(Row(1).isInfinite())
        assertTrue(RowPiece(1, 2..Infinity).isInfinite())
        assertFalse(RowPiece(1, 2..3).isInfinite())
        assertTrue(Column(1).isInfinite())
        assertTrue(ColumnPiece(2..Infinity, 1).isInfinite())
        assertFalse(ColumnPiece(2..3, 1).isInfinite())
        assertTrue(SheetRegion.isInfinite())
        assertFalse(EmptyRegion.isInfinite())

        assertFalse(Row(1).isFinite())
        assertTrue(Cell(2, 1).isFinite())
    }

    private fun `invalid region`() {
        assertThrows(IllegalArgumentException::class.java) { region(-1, -2) }
        assertThrows(IllegalArgumentException::class.java) { region(0, -2) }
        assertThrows(IllegalArgumentException::class.java) { region(-1, 0) }
    }

    @Test
    fun `row piece`() {
        assertEquals(RowPiece(BigInteger.ONE, range(2, 10)), RowPiece(1, range(2, 10)))
        assertEquals(RowPiece(BigInteger.ONE, range(2, 10)), RowPiece(1, 2..10))
        assertEquals(RowPiece(BigInteger.ONE, range(2, 10)), RowPiece(1, 2L..10L))
        assertEquals(region(2..Infinity, 3..4), RowPiece(2, 3..4)..Infinity)
        assertEquals(region(2..Infinity, 3..4), RowPiece(2, 3..4) until Infinity)
        assertEquals(Cell(2, 4), RowPiece(2, 3..5) intersect ColumnPiece(1..100, 4))
        assertNull(RowPiece(0, 3..5) intersect ColumnPiece(1..100, 4))
        assertEquals("RowPiece(row=2, columns=[3..4])", RowPiece(2, 3..4).toString())
    }

    @Test
    fun `column piece`() {
        assertEquals(ColumnPiece(range(2, 10), BigInteger.ONE), ColumnPiece(range(2, 10), 1))
        assertEquals(ColumnPiece(range(2, 10), BigInteger.ONE), ColumnPiece(2..10, 1))
        assertEquals(ColumnPiece(range(2, 10), BigInteger.ONE), ColumnPiece(2L..10L, 1))
        assertEquals(region(3..4, 2..Infinity), ColumnPiece(3..4, 2)..Infinity)
        assertEquals(region(3..4, 2..Infinity), ColumnPiece(3..4, 2) until Infinity)
        assertEquals(Cell(4, 2), ColumnPiece(3..5, 2) intersect RowPiece(4, 1..100))
        assertNull(ColumnPiece(3..5, 0) intersect RowPiece(4, 1..100))
        assertEquals("ColumnPiece(rows=[3..4], column=2)", ColumnPiece(3..4, 2).toString())
    }

    @Test
    fun row() {
        assertEquals(Row(BigInteger.ONE), Row(1))
        assertEquals(Cell(3, 2), Row(3) intersect Column(2))

        assertEquals(EmptyRegion, Row(2)..Row(1))
        assertEquals(Row(2), Row(2)..Row(2))
        assertEquals(region(2..3, allIndexes), Row(2)..Row(3))
        assertEquals(region(2..4, allIndexes), Row(2)..Row(4))

        assertEquals(EmptyRegion, Row(2) until Row(1))
        assertEquals(EmptyRegion, Row(2) until Row(2))
        assertEquals(Row(2), Row(2) until Row(3))
        assertEquals(region(2..3, allIndexes), Row(2) until Row(4))

        assertEquals(EmptyRegion, Row(1) downTo Row(2))
        assertEquals(Row(2), Row(2) downTo Row(2))
        assertEquals(region(2..3, allIndexes), Row(3) downTo Row(2))
        assertEquals(region(2..4, allIndexes), Row(4) downTo Row(2))
    }

    @Test
    fun column() {
        assertEquals(Column(BigInteger.ONE), Column(1))
        assertEquals(Cell(2, 3), Column(3) intersect Row(2))

        assertEquals(EmptyRegion, Column(2)..Column(1))
        assertEquals(Column(2), Column(2)..Column(2))
        assertEquals(region(allIndexes, 2..3), Column(2)..Column(3))
        assertEquals(region(allIndexes, 2..4), Column(2)..Column(4))

        assertEquals(EmptyRegion, Column(2) until Column(1))
        assertEquals(EmptyRegion, Column(2) until Column(2))
        assertEquals(Column(2), Column(2) until Column(3))
        assertEquals(region(allIndexes, 2..3), Column(2) until Column(4))

        assertEquals(EmptyRegion, Column(1) downTo Column(2))
        assertEquals(Column(2), Column(2) downTo Column(2))
        assertEquals(region(allIndexes, 2..3), Column(3) downTo Column(2))
        assertEquals(region(allIndexes, 2..4), Column(4) downTo Column(2))
    }

    @Test
    fun `empty region`() {
        assertEquals("∅", EmptyRegion.toString())
    }

    @Test
    fun `sheet region`() {
        assertEquals("SheetRegion", SheetRegion.toString())
    }
}