//[ArchitectureTemplate](../../index.md)/[sequences](../index.md)/[BigIntegerProgression](index.md)/[average](average.md)



# average  
[jvm]  
Brief description  
Finds average element of the sequence (yields O(1) time capacity assuming [BigDecimal](https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html) arithmetic operations to take O(1) time).  
  


#### Return  
<ul><li>Double.POSITIVE_INFINITY (step > 0) or Double.NEGATIVE_INFINITY (step < 0) when the [sequence](index.md) is infinite</li><li>Double.NaN when the [sequence](index.md) is empty</li><li>[BigDecimal](https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html) average element otherwise</li></ul>  
  
  
Content  
fun [average](average.md)(): [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)  



