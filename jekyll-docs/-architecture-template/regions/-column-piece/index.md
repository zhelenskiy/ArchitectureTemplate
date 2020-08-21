---
title: ColumnPiece -
---
//[ArchitectureTemplate](../../index.md)/[regions](../index.md)/[ColumnPiece](index.md)



# ColumnPiece  
 [jvm] open class [ColumnPiece](index.md) : [RectangularRegion](../-rectangular-region/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [<init>](-init-.md)|  [jvm] fun [<init>](-init-.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html))   <br>
| [<init>](-init-.md)|  [jvm] fun [<init>](-init-.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html))   <br>
| [<init>](-init-.md)|  [jvm] fun [<init>](-init-.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [contains](../-rectangular-region/contains.md)| [jvm]  <br>Content  <br>operator override fun [contains](../-rectangular-region/contains.md)(subregion: [RectangularRegion](../-rectangular-region/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [count](../-rectangular-region/count.md)| [jvm]  <br>Content  <br>override fun [count](../-rectangular-region/count.md)(): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)?  <br><br><br>
| [equals](../-rectangular-region/equals.md)| [jvm]  <br>Content  <br>open operator override fun [equals](../-rectangular-region/equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [get](../-rectangular-region/get.md)| [jvm]  <br>Content  <br>operator override fun [get](../-rectangular-region/get.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [Cell](../-cell/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [BigIntegerRange](../../sequences/-big-integer-range/index.md)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), columns: [BigIntegerRange](../../sequences/-big-integer-range/index.md)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [BigIntegerRange](../../sequences/-big-integer-range/index.md)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [BigIntegerRange](../../sequences/-big-integer-range/index.md)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br>operator override fun [get](../-rectangular-region/get.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](../-rectangular-region/index.md)?  <br><br><br>
| [hashCode](../-rectangular-region/hash-code.md)| [jvm]  <br>Content  <br>open override fun [hashCode](../-rectangular-region/hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intersect](intersect.md)| [jvm]  <br>Content  <br>infix fun [intersect](intersect.md)(rowPiece: [RowPiece](../-row-piece/index.md)): [Cell](../-cell/index.md)?  <br>infix override fun [intersect](../-rectangular-region/intersect.md)(other: [RectangularRegion](../-rectangular-region/index.md)): [RectangularRegion](../-rectangular-region/index.md)  <br><br><br>
| [isEmpty](../-rectangular-region/is-empty.md)| [jvm]  <br>Content  <br>override fun [isEmpty](../-rectangular-region/is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [isFinite](../-rectangular-region/is-finite.md)| [jvm]  <br>Content  <br>override fun [isFinite](../-rectangular-region/is-finite.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [isInfinite](../-rectangular-region/is-infinite.md)| [jvm]  <br>Content  <br>override fun [isInfinite](../-rectangular-region/is-infinite.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [isNotEmpty](../-rectangular-region/is-not-empty.md)| [jvm]  <br>Content  <br>override fun [isNotEmpty](../-rectangular-region/is-not-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [rangeTo](range-to.md)| [jvm]  <br>Content  <br>operator fun [rangeTo](range-to.md)(infinity: [Infinity](../../extensions/-infinity/index.md)): [RectangularRegion](../-rectangular-region/index.md)  <br>operator fun [rangeTo](range-to.md)(infinity: [PositiveInfinity](../../extensions/-positive-infinity/index.md)): [RectangularRegion](../-rectangular-region/index.md)  <br><br><br>
| [toString](to-string.md)| [jvm]  <br>Content  <br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [until](until.md)| [jvm]  <br>Content  <br>infix fun [until](until.md)(infinity: [Infinity](../../extensions/-infinity/index.md)): [RectangularRegion](../-rectangular-region/index.md)  <br>infix fun [until](until.md)(infinity: [PositiveInfinity](../../extensions/-positive-infinity/index.md)): [RectangularRegion](../-rectangular-region/index.md)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [bottom](index.md#regions/ColumnPiece/bottom/#/PointingToDeclaration/)|  [jvm] open override val [bottom](index.md#regions/ColumnPiece/bottom/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)?   <br>
| [column](index.md#regions/ColumnPiece/column/#/PointingToDeclaration/)|  [jvm] val [column](index.md#regions/ColumnPiece/column/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)   <br>
| [columns](index.md#regions/ColumnPiece/columns/#/PointingToDeclaration/)|  [jvm] override val [columns](index.md#regions/ColumnPiece/columns/#/PointingToDeclaration/): [BigIntegerRange](../../sequences/-big-integer-range/index.md)   <br>
| [left](index.md#regions/ColumnPiece/left/#/PointingToDeclaration/)|  [jvm] open override val [left](index.md#regions/ColumnPiece/left/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)   <br>
| [right](index.md#regions/ColumnPiece/right/#/PointingToDeclaration/)|  [jvm] open override val [right](index.md#regions/ColumnPiece/right/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)?   <br>
| [rows](index.md#regions/ColumnPiece/rows/#/PointingToDeclaration/)|  [jvm] override val [rows](index.md#regions/ColumnPiece/rows/#/PointingToDeclaration/): [BigIntegerRange](../../sequences/-big-integer-range/index.md)   <br>
| [top](index.md#regions/ColumnPiece/top/#/PointingToDeclaration/)|  [jvm] open override val [top](index.md#regions/ColumnPiece/top/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)   <br>


## Inheritors  
  
|  Name| 
|---|
| [Column](../-column/index.md)

