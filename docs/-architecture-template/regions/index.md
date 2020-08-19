//[ArchitectureTemplate](../index.md)/[regions](index.md)



# Package regions  


## Types  
  
|  Name|  Summary| 
|---|---|
| [Cell](-cell/index.md)| [jvm]  <br>Content  <br>data class [Cell](-cell/index.md)(**row**: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html),**column**: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)) : [RectangularRegion](-rectangular-region/index.md)  <br><br><br>
| [Column](-column/index.md)| [jvm]  <br>Content  <br>data class [Column](-column/index.md)(**number**: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)) : [ColumnPiece](-column-piece/index.md)  <br><br><br>
| [ColumnPiece](-column-piece/index.md)| [jvm]  <br>Content  <br>open class [ColumnPiece](-column-piece/index.md) : [RectangularRegion](-rectangular-region/index.md)  <br><br><br>
| [EmptyRegion](-empty-region/index.md)| [jvm]  <br>Content  <br>object [EmptyRegion](-empty-region/index.md) : [RectangularRegion](-rectangular-region/index.md)  <br><br><br>
| [RectangularRegion](-rectangular-region/index.md)| [jvm]  <br>Content  <br>open class [RectangularRegion](-rectangular-region/index.md)  <br><br><br>
| [Region](-region/index.md)| [jvm]  <br>Content  <br>class [Region](-region/index.md)(**rectangularRegions**: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<Out [RectangularRegion](-rectangular-region/index.md)>)  <br><br><br>
| [Row](-row/index.md)| [jvm]  <br>Content  <br>data class [Row](-row/index.md)(**number**: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)) : [RowPiece](-row-piece/index.md)  <br><br><br>
| [RowPiece](-row-piece/index.md)| [jvm]  <br>Content  <br>open class [RowPiece](-row-piece/index.md) : [RectangularRegion](-rectangular-region/index.md)  <br><br><br>
| [SheetRegion](-sheet-region/index.md)| [jvm]  <br>Content  <br>object [SheetRegion](-sheet-region/index.md) : [RectangularRegion](-rectangular-region/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [region](region.md)| [jvm]  <br>Content  <br>fun [region](region.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [Cell](-cell/index.md)  <br>fun [region](region.md)(rows: [BigIntegerRange](../sequences/-big-integer-range/index.md), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), column: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [BigIntegerRange](../sequences/-big-integer-range/index.md)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(row: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [BigIntegerRange](../sequences/-big-integer-range/index.md), columns: [BigIntegerRange](../sequences/-big-integer-range/index.md)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [BigIntegerRange](../sequences/-big-integer-range/index.md)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [BigIntegerRange](../sequences/-big-integer-range/index.md)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [BigIntegerRange](../sequences/-big-integer-range/index.md), columns: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br>fun [region](region.md)(rows: [BigIntegerRange](../sequences/-big-integer-range/index.md), columns: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [RectangularRegion](-rectangular-region/index.md)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [allIndexes](index.md#regions//allIndexes/#/PointingToDeclaration/)|  [jvm] val [allIndexes](index.md#regions//allIndexes/#/PointingToDeclaration/): [BigIntegerRange](../sequences/-big-integer-range/index.md)   <br>
| [firstIndex](index.md#regions//firstIndex/#/PointingToDeclaration/)|  [jvm] val [firstIndex](index.md#regions//firstIndex/#/PointingToDeclaration/): [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)   <br>

