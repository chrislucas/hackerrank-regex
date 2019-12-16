package solutions.kotlin

fun String.testMatchRegex(regex: Regex) {
    println(String.format("'%s' %s with regex '%s'"
        , this, if (this.matches(regex)) "match" else "no match", regex))
}