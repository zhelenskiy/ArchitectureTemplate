---
title: range -
---
//[ArchitectureTemplate](../index.md)/[sequences](index.md)/[range](range.md)



# range  
[jvm]  
Brief description  
Abstract function that constructs [BigIntegerRange](-big-integer-range/index.md) and its inheritors.  
  


#### Return  
<ul><li>[EmptyRange](-empty-range/index.md) when built [BigIntegerRange](-big-integer-range/index.md) is empty</li><li>[SingleRange](-single-range/index.md) when it is single</li><li>Pure [BigIntegerRange](-big-integer-range/index.md) otherwise</li></ul>  
  


## See also  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)| 
| [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)| 
  


## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| first| [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html) to be converted to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html) to be first
| last| <ul><li>When is null, Double.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, the [BigIntegerRange](-big-integer-range/index.md) is infinite</li><li>Otherwise, it is a [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html) to be converted to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html) to be first</li></ul>
  
  
Content  
fun [range](range.md)(first: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), last: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)?): [BigIntegerRange](-big-integer-range/index.md)  


[jvm]  
Brief description  
Abstract function that constructs [BigIntegerRange](-big-integer-range/index.md) and its inheritors.  
  


#### Return  
<ul><li>[EmptyRange](-empty-range/index.md) when built [BigIntegerRange](-big-integer-range/index.md) is empty</li><li>[SingleRange](-single-range/index.md) when it is single</li><li>Pure [BigIntegerRange](-big-integer-range/index.md) otherwise</li></ul>This function is the main way to construct instances of [BigIntegerRange](-big-integer-range/index.md) and its inheritors.  
  


## See also  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)| 
| [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)| 
  


## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| first| is to be first
| last| <ul><li>When is null, Double.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, the [BigIntegerRange](-big-integer-range/index.md) is infinite</li><li>Otherwise, it is a [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html) to be converted to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html) to be first</li></ul>
  
  
Content  
fun [range](range.md)(first: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html), last: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)?): [BigIntegerRange](-big-integer-range/index.md)  


[jvm]  
Brief description  
Constructs [BigIntegerProgression](-big-integer-progression/index.md) with the first and the last of the [range]().  
  
  
Content  
fun [range](range.md)(range: [BigIntegerRange](-big-integer-range/index.md)): [BigIntegerRange](-big-integer-range/index.md)  


[jvm]  
Brief description  
Constructs [BigIntegerProgression](-big-integer-progression/index.md) with the converted to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)first and last of the [range]().  
  
  
Content  
fun [range](range.md)(range: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [BigIntegerRange](-big-integer-range/index.md)  
fun [range](range.md)(range: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [BigIntegerRange](-big-integer-range/index.md)  



