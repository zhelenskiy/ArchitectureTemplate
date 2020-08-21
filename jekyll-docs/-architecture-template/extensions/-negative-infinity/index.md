---
title: NegativeInfinity -
---
//[ArchitectureTemplate](../../index.md)/[extensions](../index.md)/[NegativeInfinity](index.md)



# NegativeInfinity  
 [jvm] Represents mathematical negative infinity  
  
object [NegativeInfinity](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [minus](minus.md)| [jvm]  <br>Brief description  <br>-∞ - +∞ = -∞  <br>Content  <br>operator fun [minus](minus.md)(positiveInfinity: [PositiveInfinity](../-positive-infinity/index.md)): [NegativeInfinity](index.md)  <br><br><br>
| [plus](plus.md)| [jvm]  <br>Brief description  <br>-∞ + -∞ = -∞  <br>Content  <br>operator fun [plus](plus.md)(negativeInfinity: [NegativeInfinity](index.md)): [NegativeInfinity](index.md)  <br><br><br>
| [times](times.md)| [jvm]  <br>Brief description  <br>-∞ * +∞ = -∞  <br>Content  <br>operator fun [times](times.md)(positiveInfinity: [PositiveInfinity](../-positive-infinity/index.md)): [NegativeInfinity](index.md)  <br><br><br>[jvm]  <br>Brief description  <br>-∞ * -∞ = +∞  <br>Content  <br>operator fun [times](times.md)(negativeInfinity: [NegativeInfinity](index.md)): [PositiveInfinity](../-positive-infinity/index.md)  <br><br><br>
| [toString](to-string.md)| [jvm]  <br>Brief description  <br>-∞  <br>Content  <br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [unaryMinus](unary-minus.md)| [jvm]  <br>Brief description  <br>-(-∞) = +∞  <br>Content  <br>operator fun [unaryMinus](unary-minus.md)(): [PositiveInfinity](../-positive-infinity/index.md)  <br><br><br>
| [unaryPlus](unary-plus.md)| [jvm]  <br>Brief description  <br>+(-∞) = -∞  <br>Content  <br>operator fun [unaryPlus](unary-plus.md)(): [NegativeInfinity](index.md)  <br><br><br>

