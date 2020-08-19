---
title: equals -
---
//[ArchitectureTemplate](../../index.md)/[sequences](../index.md)/[BigIntegerProgression](index.md)/[equals](equals.md)



# equals  
[jvm]  
Brief description  
Two [BigIntegerProgression](index.md)s are equal when they are equal as sequences.This means:<ul><li>If [other]() is not [BigIntegerProgression](index.md), equality is checked as for [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/index.html)</li><li>Empty [BigIntegerProgression](index.md)s are equal only to each other</li><li>Single ranges are equal when their only elements are equal</li><li>Other ones are equal when the [sequences](index.md) have equal first, last, step.</li></ul>  
  
  
Content  
open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  



