package solutions.kotlin.utils

fun String.logMatchRegex(regex: Regex) = println(this.logRegex(regex))

fun String.logRegex(regex: Regex) : String = String.format("'%s' %s with regex '%s'"
    , this, if (this.matches(regex)) "match" else "no match", regex)

inline fun String.logMatchesRegex(vararg listRegex: Regex, after: () -> Unit, assertion: Boolean = false) {
    listRegex.forEach {
        this.logMatchRegex(it)
    }
    if (assertion)
        after()
}

val startOrEndWithWhiteSpaces = Regex("(^\\s+.*)|(.*\\s+$)", RegexOption.MULTILINE)

val startOrEndWithWhiteSpacesAndHasSomeChars = Regex("(^\\s+\\w+)|(\\w+\\s+$)", RegexOption.MULTILINE)

val startingOrEndingWithWhiteSpace= "^\\s+|\\s+$".toRegex(RegexOption.MULTILINE)

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

fun MatchResult.log(target: String, regex: Regex)   {
    println("I: MatchResult - Range: $range, Value: '$value'," +
            " LogMatchRegex: ${target.logRegex(regex)}")
    for (group in groups) {
        group?.let {
            println("I: Group - Range: ${it.range}, Value: '${it.value}'")
        }
    }
}