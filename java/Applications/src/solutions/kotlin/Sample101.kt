package solutions.kotlin

/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/find.html
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/find-all.html
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/from-literal.html
 *
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex-option/index.html
 * http://zetcode.com/kotlin/regularexpressions/
 * */
fun main() {
    val matchResult : MatchResult? =  "gr[ae]y".toRegex().find("Is his hair grey or gray")
    matchResult?.let {
        showMatchResult(it)
    }
}

fun showMatchResult(matchResult: MatchResult?) {
    if (matchResult == null)
        return
    else {
        val range = matchResult.range
        println(range)
        showMatchResult(matchResult.next())
    }
}