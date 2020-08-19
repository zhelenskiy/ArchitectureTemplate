---
title: extend -
---
//[ArchitectureTemplate](../../index.md)/[sequences](../index.md)/[EmptyRange](index.md)/[extend](extend.md)



# extend  
[jvm]  
Brief description  
Adds [n]() (converted to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)) elements to the end of the [progression](../-big-integer-progression/index.md) according to its step if it has at least 2 elements.The input [progression](../-big-integer-progression/index.md) is returned if it can not be extended (it is empty or infinite). The step for all progressions of length 1 is assumed to be 1 (they are instances of [SingleRange](../-single-range/index.md)).  
  
  
Content  
open override fun [extend](extend.md)(n: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [EmptyRange](index.md)  



