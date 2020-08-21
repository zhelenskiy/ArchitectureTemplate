---
title: progression -
---
//[ArchitectureTemplate](../index.md)/[sequences](index.md)/[progression](progression.md)



# progression  
[jvm]  
Brief description  
Abstract function that constructs [BigIntegerProgression](-big-integer-progression/index.md) and its inheritors.  
  


#### Return  
<ul><li>[EmptyRange](-empty-range/index.md) when resulting [BigIntegerProgression](-big-integer-progression/index.md) is empty</li><li>[SingleRange](-single-range/index.md) when it contains one element</li><li>[BigIntegerRange](-big-integer-range/index.md) when step is 1 and resulting [BigIntegerProgression](-big-integer-progression/index.md) contains more than one element</li><li>Pure [BigIntegerProgression](-big-integer-progression/index.md) otherwise</li></ul>  
  


## See also  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| [IntProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-progression/index.html)| 
| [LongProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-progression/index.html)| 
  


## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| first| The first value of the progression (unless it is empty)
| step| Non-null value to be added to calculate the next element
| toInclusive| <ul><li>When null, the progression is infinite</li><li>When [step]() is positive, the least upper bound integer</li><li>When [step]() is negative, the greatest lower bound integer</li></ul>
  
  
Content  
fun [progression](progression.md)(first: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html), toInclusive: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)?, step: [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)): [BigIntegerProgression](-big-integer-progression/index.md)  


[jvm]  
Brief description  
Constructs [BigIntegerProgression](-big-integer-progression/index.md) with the same first, last and step as in the [progression]().  
  
  
Content  
fun [progression](progression.md)(progression: [BigIntegerProgression](-big-integer-progression/index.md)): [BigIntegerProgression](-big-integer-progression/index.md)  


[jvm]  
Brief description  
Constructs [BigIntegerProgression](-big-integer-progression/index.md) with the converted to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)first, last and step of the [progression]().  
  
  
Content  
fun [progression](progression.md)(progression: [IntProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-progression/index.html)): [BigIntegerProgression](-big-integer-progression/index.md)  
fun [progression](progression.md)(progression: [LongProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-progression/index.html)): [BigIntegerProgression](-big-integer-progression/index.md)  


[jvm]  
Brief description  
Abstract function that constructs [BigIntegerProgression](-big-integer-progression/index.md) and its inheritors.  
  


#### Return  
<ul><li>[EmptyRange](-empty-range/index.md) when built [BigIntegerProgression](-big-integer-progression/index.md) is empty</li><li>[SingleRange](-single-range/index.md) when it contains one element</li><li>[BigIntegerRange](-big-integer-range/index.md) when step is 1 and resulting [BigIntegerProgression](-big-integer-progression/index.md) contains more than one element</li><li>Pure [BigIntegerProgression](-big-integer-progression/index.md) otherwise</li></ul>This function is the main way to construct instances of [BigIntegerProgression](-big-integer-progression/index.md) and its inheritors.  
  


## See also  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| [IntProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-progression/index.html)| 
| [LongProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-progression/index.html)| 
  


## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| first| The first value (after conversation to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)) of the progression (unless it is empty)
| step| Non-null value (after conversation to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)) to be added to calculate the next element
| toInclusive| <ul><li>When null, the progression is infinite</li><li>When equals to Double.POSITIVE_INFINITY or Float.POSITIVE_INFINITY and [step]() is positive, the progression is infinite</li><li>When equals to Double.POSITIVE_INFINITY or Float.POSITIVE_INFINITY and [step]() is negative, the progression is empty</li><li>When equals to Double.NEGATIVE_INFINITY or Float.NEGATIVE_INFINITY and [step]() is negative, the progression is infinite</li><li>When equals to Double.NEGATIVE_INFINITY or Float.NEGATIVE_INFINITY and [step]() is positive, the progression is empty</li><li>When converted to [BigInteger]() is positive, the least upper bound integer</li><li>When converted to [BigInteger]() is negative, the greatest lower bound integer</li></ul>
  
  
Content  
fun [progression](progression.md)(first: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), toInclusive: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)?, step: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [BigIntegerProgression](-big-integer-progression/index.md)  



