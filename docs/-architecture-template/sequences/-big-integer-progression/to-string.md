//[ArchitectureTemplate](../../index.md)/[sequences](../index.md)/[BigIntegerProgression](index.md)/[toString](to-string.md)



# toString  
[jvm]  
Brief description  
[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/index.html) representation for the [BigIntegerProgression](index.md).  
  


#### Return  
<ul><li>"[]" when is empty</li><li>"[" + [the only element](../-single-range/index.md#sequences/SingleRange/only/#/PointingToDeclaration/) + "]" when is single</li><li>"[" + first + ".." + last + "]([" + `first` + ".." + `last` + "])" when is finite increasing progression with step = 1</li><li>"[" + first + ".." + last + "]([" + `first` + ".." + `last` + "]) step step" when is finite increasing progression with step ≠ 1</li><li>"[" + first + " downTo " + last + "]([" + `first` + " downTo " + `last` + "])" when is finite decreasing progression with step = 1</li><li>"[" + first + " downTo " + last + "]([" + `first` + " downTo " + `last` + "]) step step" when is finite decreasing progression with step ≠ 1</li><li>"[" + first + "..+∞)" when is infinite increasing progression with step = 1</li><li>"[" + first + "..+∞) step step" when is infinite increasing progression with step ≠ 1</li><li>"[" + first + " downTo -∞)" when is infinite decreasing progression with step = 1</li><li>"[" + first + " downTo -∞) step step" when is infinite decreasing progression with step ≠ 1</li></ul>  
  
  
Content  
open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  



