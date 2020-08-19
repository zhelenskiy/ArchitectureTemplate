//[ArchitectureTemplate](../../index.md)/[sequences](../index.md)/[BigIntegerProgression](index.md)/[elementAtOrElse](element-at-or-else.md)



# elementAtOrElse  
[jvm]  
Brief description  
Gets element by the specified [index]() (since 0) (converted to [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)) or calls [defaultValue]() with [index]() if the [index]() is out of range.The [defaultValue]() is be called if the [index]() is negative or if it is not less than [count](count.md).Yields O(1) time capacity.  
  
  
Content  
fun [elementAtOrElse](element-at-or-else.md)(index: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), defaultValue: ([Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)) -> [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)): [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)  



