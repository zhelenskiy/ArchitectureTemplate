import extensions.Infinity
import extensions.rangeTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import regions.*

class RegionTests {
    @Test
    fun rectangles() {
        assertTrue(Region(EmptyRegion, EmptyRegion).rectangles.isEmpty())
        assertIterableEquals(listOf(SheetRegion), Region(EmptyRegion, SheetRegion, SheetRegion, EmptyRegion).rectangles)
        assertIterableEquals(Region(SheetRegion).rectangles, Region(SheetRegion, Row(2)).rectangles)
        assertIterableEquals(Region(SheetRegion).rectangles, Region(Row(2), SheetRegion).rectangles)
        val left = Cell(1, 2)..Cell(5, 2)// like letter 'H'
        val right = Cell(1, 6)..Cell(5, 6)
        val center = Cell(3, 2)..Cell(3, 6)
        val point1 = Cell(3, 2)
        val point2 = Cell(3, 6)
        val square = region(2..3, 2..3)
        val arr = arrayOf(left, right, center, point1, point2, square)
        for (item1 in 0 until 6)
            for (item2 in 0 until 6)
                for (item3 in 0 until 6)
                    for (item4 in 0 until 6)
                        for (item5 in 0 until 6)
                            for (item6 in 0 until 6) {
                                val items = arrayOf(item1, item2, item3, item4, item5, item6)
                                if (items.distinct().size == arr.size) {
                                    assertIterableEquals(
                                        listOf(left, right, square, center),
                                        Region(*items.map { arr[it] }.toTypedArray()).rectangles
                                    )
                                }
                            }
        assertIterableEquals(
            listOf(Row(1), region(1..5, 0), region(1..5, 1), region(1..5, 2), Row(2)),
            Region(Row(2), Row(1), region(1..5, 0), region(1..5, 2), region(1..5, 1)).rectangles
        )
    }

    @Test
    fun `to string`() {
        assertEquals("Region{${Cell(1, 3)..Cell(3, 4)}, ${Cell(5, 5)}}", Region(Cell(5, 5), Cell(1, 3)..Cell(3, 4)).toString())
    }

    @Test
    fun finiteness() {
        assertTrue(Region(Cell(5, 5), Cell(1, 3)..Cell(3, 4)).isFinite())
        assertFalse(Region(Cell(5, 5), Cell(1, 3)..Cell(3, 4)).isInfinite())
        assertFalse(Region(Cell(5, 5), Cell(1, 3)..Cell(3, 4), region(2, 100..Infinity)).isFinite())
        assertTrue(Region(Cell(5, 5), Cell(1, 3)..Cell(3, 4), region(2, 100..Infinity)).isInfinite())
    }

    @Test
    fun emptiness() {
        assertTrue(Region().isEmpty())
        assertFalse(Region().isNotEmpty())
        assertTrue(Region(EmptyRegion).isEmpty())
        assertFalse(Region(EmptyRegion).isNotEmpty())
        assertTrue(Region(EmptyRegion, EmptyRegion).isEmpty())
        assertFalse(Region(EmptyRegion, EmptyRegion).isNotEmpty())
        assertFalse(Region(Cell(2, 3), EmptyRegion).isEmpty())
        assertTrue(Region(Cell(2, 3), EmptyRegion).isNotEmpty())
        assertFalse(Region(Row(2), EmptyRegion).isEmpty())
        assertTrue(Region(Row(2), EmptyRegion).isNotEmpty())
    }
}