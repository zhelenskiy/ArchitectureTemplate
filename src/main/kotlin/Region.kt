import java.math.BigInteger
import java.util.function.Function

class Region(vararg rectangularRegions: RectangularRegion) {
    //todo optimize
    val regions = rectangularRegions
        .filter { it.isNotEmpty() }
        .toMutableSet()
        .apply {
            for (item in this)
                if (any { item in it && item !== it })
                    remove(item)
        }
        .toSortedSet(compareBy({ it.rows }, { it.columns }))

    override fun toString(): String {
        return regions.joinToString(prefix = "Region(", postfix = ")")
    }

    //contains
    //count
    //others from rectangular

    fun isInfinite(): Boolean = regions.any { it.isInfinite()}
    fun isFinite(): Boolean = !isInfinite()
    fun isEmpty(): Boolean = regions.isEmpty()
    fun isNotEmpty(): Boolean = !isEmpty()
    //todo: fun count(): BigInteger? = if(isInfinite()) null else ...
    //todo: operator fun contains(RectangularRegion): Boolean
}