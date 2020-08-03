import java.math.BigInteger

val firstIndex: Number = 0
val allIndexes = firstIndex..Infinity

fun region(row: Number, column: Number) = Cell(row, column)
fun region(rows: BigIntegerRange, column: Number): RectangularRegion = when (rows) {
    allIndexes -> Column(column)
    is EmptyRange -> EmptyRegion
    else -> ColumnPiece(rows, column)
}

fun region(rows: IntRange, column: Number): RectangularRegion = region(range(rows), column)
fun region(rows: LongRange, column: Number): RectangularRegion = region(range(rows), column)
fun region(row: Number, columns: BigIntegerRange) = when (columns) {
    allIndexes -> Row(row)
    is EmptyRange -> EmptyRegion
    else -> RowPiece(row, columns)
}

fun region(row: Number, columns: IntRange): RectangularRegion = region(row, range(columns))
fun region(row: Number, columns: LongRange): RectangularRegion = region(row, range(columns))
fun region(rows: BigIntegerRange, columns: BigIntegerRange): RectangularRegion = when {
    rows.isEmpty() || columns.isEmpty() -> EmptyRegion
    rows.isSingle() && columns.isSingle() -> Cell(rows.first, columns.first)
    rows.isSingle() && columns == allIndexes -> Row(rows.first)
    rows.isSingle() -> RowPiece(rows.first, columns)
    rows == allIndexes && columns.isSingle() -> Column(columns.first)
    columns.isSingle() -> ColumnPiece(rows, columns.first)
    rows == allIndexes && columns == allIndexes -> SheetRegion
    else -> RectangularRegion(rows, columns)
}

fun region(rows: IntRange, columns: BigIntegerRange): RectangularRegion =
    region(range(rows), columns)

fun region(rows: LongRange, columns: BigIntegerRange): RectangularRegion =
    region(range(rows), columns)

fun region(rows: IntRange, columns: IntRange): RectangularRegion =
    region(range(rows), range(columns))

fun region(rows: LongRange, columns: IntRange): RectangularRegion =
    region(range(rows), range(columns))

fun region(rows: BigIntegerRange, columns: IntRange): RectangularRegion =
    region(rows, range(columns))

fun region(rows: IntRange, columns: LongRange): RectangularRegion =
    region(range(rows), range(columns))

fun region(rows: LongRange, columns: LongRange): RectangularRegion =
    region(range(rows), range(columns))

fun region(rows: BigIntegerRange, columns: LongRange): RectangularRegion =
    region(rows, range(columns))

data class Cell(val row: BigInteger, val column: BigInteger) : RectangularRegion(row..row, column..column) {
    val rowInstance: Row
        get() = Row(row)
    val columnInstance: Column
        get() = Column(column)

    constructor(rowInstance: Row, columnInstance: Column) : this(rowInstance.index, columnInstance.index)
    constructor(rowInstance: Row, column: Number) : this(rowInstance.index, column)
    constructor(row: Number, columnInstance: Column) : this(row, columnInstance.index)
    constructor(row: Number, column: Number) : this(row.toBigInteger(), column.toBigInteger())

    operator fun rangeTo(other: Cell): RectangularRegion = region(this.row..other.row, this.column..other.column)
    operator fun rangeTo(infinity: Infinity): RectangularRegion = region(row..Infinity, column..Infinity)

    infix fun until(other: Cell): RectangularRegion = region(this.row until other.row, this.column until other.column)
    infix fun until(infinity: Infinity): RectangularRegion = this..Infinity

    infix fun downTo(other: Cell): RectangularRegion = other..this
}

open class RectangularRegion internal constructor(val rows: BigIntegerRange, val columns: BigIntegerRange) {

    fun isInfinite(): Boolean = rows.isInfinite() || columns.isInfinite()

    fun isFinite(): Boolean = !isInfinite()

    fun isEmpty(): Boolean = rows.isEmpty() || columns.isEmpty()

    fun isNonEmpty(): Boolean = !isEmpty()

    fun count(): BigInteger? = when {
        isEmpty() -> BigInteger.ZERO
        isInfinite() -> null
        else -> rows.count()!! * columns.count()!!
    }

    override fun equals(other: Any?): Boolean = when {
        other !is RectangularRegion -> false
        this.isEmpty() && other.isEmpty() -> true
        else -> this.rows == other.rows && this.columns == other.columns
    }

    override fun hashCode(): Int = if (isEmpty()) 0 else 31 * rows.hashCode() * columns.hashCode()

    operator fun get(row: Number, column: Number): Cell? = region(row, column).takeIf { it in this }
    operator fun get(rows: BigIntegerRange, column: Number): RectangularRegion? =
        region(rows, column).takeIf { it in this }

    operator fun get(rows: IntRange, column: Number): RectangularRegion? =
        get(range(rows), column)

    operator fun get(rows: LongRange, column: Number): RectangularRegion? =
        get(range(rows), column)

    operator fun get(row: Number, columns: BigIntegerRange): RectangularRegion? =
        region(row, columns).takeIf { it in this }

    operator fun get(row: Number, columns: IntRange): RectangularRegion? =
        get(row, range(columns))

    operator fun get(row: Number, columns: LongRange): RectangularRegion? =
        get(row, range(columns))

    operator fun get(rows: BigIntegerRange, columns: BigIntegerRange): RectangularRegion? =
        region(rows, columns).takeIf { it in this }

    operator fun get(rows: IntRange, columns: BigIntegerRange): RectangularRegion? =
        get(range(rows), columns)

    operator fun get(rows: LongRange, columns: BigIntegerRange): RectangularRegion? =
        get(range(rows), columns)

    operator fun get(rows: BigIntegerRange, columns: IntRange): RectangularRegion? =
        get(rows, range(columns))

    operator fun get(rows: IntRange, columns: IntRange): RectangularRegion? =
        get(range(rows), range(columns))

    operator fun get(rows: LongRange, columns: IntRange): RectangularRegion? =
        get(range(rows), range(columns))

    operator fun get(rows: BigIntegerRange, columns: LongRange): RectangularRegion? =
        get(rows, range(columns))

    operator fun get(rows: IntRange, columns: LongRange): RectangularRegion? =
        get(range(rows), range(columns))

    operator fun get(rows: LongRange, columns: LongRange): RectangularRegion? =
        get(range(rows), range(columns))

    operator fun contains(subregion: RectangularRegion): Boolean =
        subregion.rows in this.rows && subregion.columns in this.columns

    infix fun intersect(other: RectangularRegion) =
        region(this.rows intersect other.rows, this.columns intersect other.columns)

    val top: BigInteger
        get() = rows.first

    val bottom: BigInteger?
        get() = rows.last

    val left: BigInteger
        get() = columns.first

    val right: BigInteger?
        get() = columns.last

    override fun toString(): String = when {
        rows.isInfinite() && columns.isInfinite() -> "${region(top, left)}..+∞"
        rows.isInfinite() -> "${region(top, columns)}..+∞"
        columns.isInfinite() -> "${region(rows, left)}..+∞"
        else -> "${region(top, left)}..${region(bottom!!, right!!)}"
    }
}

open class RowPiece internal constructor(val row: BigInteger, columns: BigIntegerRange) :
    RectangularRegion(row..row, columns) {
    constructor(index: Number, columns: BigIntegerRange) : this(index.toBigInteger(), columns)
    constructor(index: Number, columns: IntRange) : this(index.toBigInteger(), range(columns))
    constructor(index: Number, columns: LongRange) : this(index.toBigInteger(), range(columns))

    operator fun rangeTo(infinity: Infinity): RectangularRegion = region(this.row..Infinity, columns)

    infix fun until(infinity: Infinity): RectangularRegion = this..Infinity

    infix fun intersect(columnPiece: ColumnPiece) =
        Cell(row, columnPiece.column).takeIf { it in this && it in columnPiece }
}

data class Row(val index: BigInteger) : RowPiece(index, allIndexes) {
    constructor(index: Number) : this(index.toBigInteger())

    infix fun intersect(column: Column) = Cell(this, column)

    operator fun rangeTo(other: Row): RectangularRegion = region(this.index..other.index, allIndexes)

    infix fun until(other: Row): RectangularRegion = region(this.index until other.index, allIndexes)

    infix fun downTo(other: Row): RectangularRegion = other..this
}

open class ColumnPiece internal constructor(rows: BigIntegerRange, val column: BigInteger) :
    RectangularRegion(rows, column..column) {
    constructor(rows: BigIntegerRange, column: Number) : this(rows, column.toBigInteger())
    constructor(rows: IntRange, column: Number) : this(range(rows), column.toBigInteger())
    constructor(rows: LongRange, column: Number) : this(range(rows), column.toBigInteger())

    operator fun rangeTo(infinity: Infinity): RectangularRegion = region(rows, this.column..Infinity)

    infix fun until(infinity: Infinity): RectangularRegion = this..Infinity

    infix fun intersect(rowPiece: RowPiece) = Cell(rowPiece.row, column).takeIf { it in rowPiece && it in this }
}

data class Column(val index: BigInteger) : ColumnPiece(allIndexes, index) {
    constructor(index: Number) : this(index.toBigInteger())

    infix fun intersect(row: Row) = Cell(row, this)

    operator fun rangeTo(other: Column): RectangularRegion = region(allIndexes, this.index..other.index)

    infix fun until(other: Column): RectangularRegion = region(allIndexes, this.index until other.index)

    infix fun downTo(other: Column): RectangularRegion = other..this
}

object EmptyRegion : RectangularRegion(EmptyRange, EmptyRange) {
    override fun toString(): String {
        return "EmptyRegion"
    }
}

object SheetRegion : RectangularRegion(allIndexes, allIndexes)

//todo explicit types,
// * documentation,
// + add non-existing methods to std-ranges,
// + commented stuff,
// + row and column as cell,
// + add cell properties definitions as declared,
// * add tests for the new features,
// + range.contains(subrange)
// + isSingle()
// + changeBehaviour when single
// * names, codes and parents for the outer container
// + sheet
// + components for ranges