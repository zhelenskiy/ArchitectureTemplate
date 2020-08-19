---
title: RectangularRegion -
---
//[ArchitectureTemplate](../../index.md)/[regions](../index.md)/[RectangularRegion](index.md)



# RectangularRegion  
 [jvm] open class [RectangularRegion](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [contains](contains.md)| [jvm]  <br>Content  <br>operator fun [contains](contains.md)(subregion: [RectangularRegion](index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [count](count.md)| [jvm]  <br>Content  <br>fun [count](count.md)(): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)?  <br><br><br>
| [equals](equals.md)| [jvm]  <br>Content  <br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [get](get.md)| [jvm]  <br>Content  <br>operator fun [get](get.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [Cell](../-cell/index.md)?  <br>operator fun [get](get.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [BigIntegerRange](../../sequences/-big-integer-range/index.md)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), columns: [BigIntegerRange](../../sequences/-big-integer-range/index.md)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [BigIntegerRange](../../sequences/-big-integer-range/index.md)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [BigIntegerRange](../../sequences/-big-integer-range/index.md)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [BigIntegerRange](../../sequences/-big-integer-range/index.md), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](index.md)?  <br>operator fun [get](get.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](index.md)?  <br><br><br>
| [hashCode](hash-code.md)| [jvm]  <br>Content  <br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intersect](intersect.md)| [jvm]  <br>Content  <br>infix fun [intersect](intersect.md)(other: [RectangularRegion](index.md)): [RectangularRegion](index.md)  <br><br><br>
| [isEmpty](is-empty.md)| [jvm]  <br>Content  <br>fun [isEmpty](is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [isFinite](is-finite.md)| [jvm]  <br>Content  <br>fun [isFinite](is-finite.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [isInfinite](is-infinite.md)| [jvm]  <br>Content  <br>fun [isInfinite](is-infinite.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [isNotEmpty](is-not-empty.md)| [jvm]  <br>Content  <br>fun [isNotEmpty](is-not-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [toString](to-string.md)| [jvm]  <br>Content  <br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [bottom](index.md#regions/RectangularRegion/bottom/#/PointingToDeclaration/)|  [jvm] open val [bottom](index.md#regions/RectangularRegion/bottom/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)?   <br>
| [columns](index.md#regions/RectangularRegion/columns/#/PointingToDeclaration/)|  [jvm] val [columns](index.md#regions/RectangularRegion/columns/#/PointingToDeclaration/): [BigIntegerRange](../../sequences/-big-integer-range/index.md)   <br>
| [left](index.md#regions/RectangularRegion/left/#/PointingToDeclaration/)|  [jvm] open val [left](index.md#regions/RectangularRegion/left/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)   <br>
| [right](index.md#regions/RectangularRegion/right/#/PointingToDeclaration/)|  [jvm] open val [right](index.md#regions/RectangularRegion/right/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)?   <br>
| [rows](index.md#regions/RectangularRegion/rows/#/PointingToDeclaration/)|  [jvm] val [rows](index.md#regions/RectangularRegion/rows/#/PointingToDeclaration/): [BigIntegerRange](../../sequences/-big-integer-range/index.md)   <br>
| [top](index.md#regions/RectangularRegion/top/#/PointingToDeclaration/)|  [jvm] open val [top](index.md#regions/RectangularRegion/top/#/PointingToDeclaration/): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)   <br>


## Inheritors  
  
|  Name| 
|---|
| [Cell](../-cell/index.md)
| [RowPiece](../-row-piece/index.md)
| [ColumnPiece](../-column-piece/index.md)
| [EmptyRegion](../-empty-region/index.md)
| [SheetRegion](../-sheet-region/index.md)

