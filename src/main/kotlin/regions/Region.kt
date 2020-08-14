package regions

class Region(vararg rectangularRegions: RectangularRegion) {
    //todo optimize
    val rectangles = rectangularRegions
        .filter { it.isNotEmpty() }
        .distinct()
        .let {
            it.filter { pretender ->
                it.none { otherRectangle -> pretender !== otherRectangle && pretender in otherRectangle }
            }
        }
        .toSortedSet(compareBy({ it.rows }, { it.columns }))

    override fun toString(): String {
        return rectangles.joinToString(prefix = "Region{", postfix = "}")
    }

    //todo contains
    //todo count
    //todo others from rectangular

    fun isInfinite(): Boolean = rectangles.any { it.isInfinite() }
    fun isFinite(): Boolean = !isInfinite()
    fun isEmpty(): Boolean = rectangles.isEmpty()
    fun isNotEmpty(): Boolean = !isEmpty()
    //todo: fun count(): BigInteger? = if(isInfinite()) null else ...
    //todo: operator fun contains(RectangularRegion): Boolean
}