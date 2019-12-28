package solutions.kotlin.samples

/**
 * https://www.regular-expressions.info/lookaround.html
 * https://www.rexegg.com/regex-lookarounds.html
 * https://www.rexegg.com/regex-lookarounds.html#password
 * https://efraimgentil.github.io/post/java/core/regex/look-ahead-e-look-behind
 *
 * (?=foo)	Lookahead
 * (?<=foo)	Lookbehind
 * (?!foo)	Negative Lookahead
 * (?<!foo)	Negative Lookbehind
 *
 * (?(?=regex)then|else)
 *
 *
 * Definicao de zero width|legth assertion
 *
 * */

fun testLookAHeadAssertions() {
    val regex = "(?=<\\w>)\\w+</\\w>".toRegex()
    println("<a>teste</a>".matches(regex))


}

fun testNegativeLookAheadTest() {
    /**
     * Se quisermos uma regex que corresponda com "A" nao seguido de "B
     * usamos o negative lookahead(?!)
     * */

    println("http".matches("http(?!s)".toRegex()))
    println("https".matches("http(?!s)".toRegex()))

    println("chrisluccas".matches("chris(?=luccas)".toRegex()))
    println("barbar".matches("bar(?=bar)".toRegex()))
    println("chrispeterson".matches("chris(?=luccas)".toRegex()))
}

fun main() {
    testNegativeLookAheadTest()
}