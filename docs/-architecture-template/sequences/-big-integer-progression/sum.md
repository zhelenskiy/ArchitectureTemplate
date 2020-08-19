//[ArchitectureTemplate](../../index.md)/[sequences](../index.md)/[BigIntegerProgression](index.md)/[sum](sum.md)



# sum  
[jvm]  
Brief description  
Finds sum of the sequence (yields O(1) time capacity assuming [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html) arithmetic operations to take O(1) time).  
  


#### Return  
<ul><li>Double.POSITIVE_INFINITY (step > 0) or Double.NEGATIVE_INFINITY (step < 0) when the [sequence](index.md) is infinite</li><li>BigInteger.ZERO when the [sequence](index.md) is empty</li><li>[BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html) sum otherwise</li></ul>  
  
  
Content  
fun [sum](sum.md)(): [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)  



