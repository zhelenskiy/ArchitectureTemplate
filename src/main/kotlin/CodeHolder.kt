import regions.Region

open class CodeHolder(val region: Region, open val code: Any /*Now*/, val name: String) {
    val subsriptors: List<CodeHolder> = emptyList() //stuff that uses this holder. They should be notified when data changes
    fun evaluate(): Any /*Now*/ = Unit
    fun reevaluate(): Any /*Now*/ = Unit
    enum class EvaluationStatus
    { Waiting, Evaluating, Done }
    val color: Any = Unit /*Now*/
    val shows: Any = Unit /*Now*/
}