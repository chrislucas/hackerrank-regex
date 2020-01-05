package solutions.kotlin.samples

import solutions.kotlin.utils.lineSeparation
import solutions.kotlin.utils.logMatchRegex

/**
 * https://stackoverflow.com/questions/3512471/what-is-a-non-capturing-group-in-regular-expressions
 * https://www.rexegg.com/regex-disambiguation.html#lookarounds
 * */

fun testSimpleGroup() {
    // combina com a-z
    var groupRegex = Regex("(a-z)")
    val strs = mutableListOf("a-z", "a", "b", "z", "abc")
    strs.forEach {
        it.logMatchRegex(groupRegex)
    }
    lineSeparation()
    // combina com a aocorrencia de a-z uma ou mais vezes
    groupRegex = Regex("(a-z)+")
    strs.addAll(listOf("a-za-za-z", "a-ztestea-z"))
    strs.forEach {
        it.logMatchRegex(groupRegex)
    }
}

fun main() {
    testSimpleGroup()
}