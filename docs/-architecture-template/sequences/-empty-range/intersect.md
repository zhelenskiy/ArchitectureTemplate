//[ArchitectureTemplate](../../index.md)/[sequences](../index.md)/[EmptyRange](index.md)/[intersect](intersect.md)



# intersect  
[jvm]  
Content  
infix fun [intersect](intersect.md)(other: [BigIntegerProgression](../-big-integer-progression/index.md)): [EmptyRange](index.md)  
infix fun [intersect](intersect.md)(other: [IntProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-progression/index.html)): [EmptyRange](index.md)  
infix fun [intersect](intersect.md)(other: [LongProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-progression/index.html)): [EmptyRange](index.md)  


[jvm]  
Brief description  
Finds maximal [BigIntegerRange](../-big-integer-range/index.md) that is contained by both [BigIntegerRange](../-big-integer-range/index.md)s yielding O(1) time capacity.  
  
  
Content  
open infix override fun [intersect](intersect.md)(other: [BigIntegerRange](../-big-integer-range/index.md)): [EmptyRange](index.md)  


[jvm]  
Brief description  
Finds maximal [BigIntegerRange](../-big-integer-range/index.md) that is contained by both [BigIntegerRange](../-big-integer-range/index.md) and [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html) converted to [BigIntegerRange](../-big-integer-range/index.md) yielding O(1) time capacity.  
  
  
Content  
open infix override fun [intersect](intersect.md)(other: [IntRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)): [EmptyRange](index.md)  


[jvm]  
Brief description  
Finds maximal [BigIntegerRange](../-big-integer-range/index.md) that is contained by both [BigIntegerRange](../-big-integer-range/index.md) and [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html) converted to [BigIntegerRange](../-big-integer-range/index.md) yielding O(1) time capacity.  
  
  
Content  
open infix override fun [intersect](intersect.md)(other: [LongRange](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-long-range/index.html)): [EmptyRange](index.md)  



