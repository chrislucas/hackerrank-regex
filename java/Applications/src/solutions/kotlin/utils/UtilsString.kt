package solutions.kotlin.utils

fun String.logMatchRegex(regex: Regex) {
    println(String.format("'%s' %s with regex '%s'"
        , this, if (this.matches(regex)) "match" else "no match", regex))
}

internal val startOrEndWithWhiteSpaces = Regex("(^\\s+.*)|(.*\\s$+)")

internal val startingOrEndingWithWhiteSpace= "^\\s+|\\s+$".toRegex()

fun String.testIfStringStartsOrEndsWithWhiteSpace() = this.matches(startOrEndWithWhiteSpaces)

fun String.logIfStringStartsOrEndsWithWhiteSpace() = this.logMatchRegex(startOrEndWithWhiteSpaces)

fun String.startWith() = this.matches(Regex("^$this"))

fun String.replaceWhiteSpacesInStartOrEnd(replace: String = "-") = this.replace(startingOrEndingWithWhiteSpace, replace)

fun lineSeparation(lines: Int = 1) {
    repeat(lines) {
        println("==================================================================" +
                "====================================================================")
    }
}