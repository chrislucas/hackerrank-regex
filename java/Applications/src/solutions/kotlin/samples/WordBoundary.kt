package solutions.kotlin.samples

import solutions.kotlin.utils.logMatchRegex
import solutions.kotlin.utils.logMatchesRegex

/**
 *
 * Testes baseados na fonte: https://www.regular-expressions.info/wordboundaries.html
 * O metachar \b eh uma anconra assim como ^ e $ (inicio e fim de string) e corresponde
 * a uma posicao chamada de "Limite|Fonteira da palavra" (word boundary), sendo uma
 * correspondencia de comprimento zero (zero-length)
 *
 * Esse metachar permite fazer uma pesquisa por uma palavra inteira atraves de uma expressao regular
 * (ER) na forma \btexto pesquisado\b
 *
 * Existem 3 posicoes numa string de delimitam o chamado "limites|fronteiras da string"
 * 1) antes do primeiro caracter na string, se o primeiro caracter for um "caracter de palavras"
 * o que na maioria dos "flavors" de regex sao os caracteres considerados no metachar \\w, com excecao
 * do Java que tbm considera caracteres Unicode
 *
 * 2) depois do ultimo caracter na string, se o ultimo caracter for uim "caracter de palavra"
 *
 * 3) entre 2 caracteres de palavras
 * */

fun testWordBoundary_1() {
    val regex1 = Regex("\\b\\d+\\b")
    val regex2 = Regex(".*\\b\\d+\\b.*")

    val list = listOf("1010"
        , " 101010"
        , " 101010 "
        , " 101010a "
        , " a101010a "
        , "1"
        , "a1a"
    )

    list.forEachIndexed {
        i, s ->
        s.logMatchesRegex(regex1
            , regex2
            , after = { println("*********************************************") }
            , assertion = i < list.size - 1)
    }
}

fun searchAWord(word: String, textTarget: String) {
    val matchResult = Regex("\\b$word\\b").find(textTarget)
    matchResult?.let {
        var mr : MatchResult? = it
        while (mr != null) {
            println("${mr.value}, ${mr.range}")
            mr = mr.next()
        }
    }
}

fun testSearchAWord() {
    searchAWord("Before"
        , "Before the first character in the string, " +
                "if the first character is a word character.")
    searchAWord("After", "After the last character in " +
            "the string, if the last character is a word character.")
    val text1 = "Between two characters in the string, " +
            "where one is a word character and the other is not a word character."
    searchAWord("Between", text1)
    searchAWord("is", text1)
    // passando uma regex como argumento (character(s) ? 0 ou 1 ocorrencia do caracter "s"
    searchAWord("characters?", text1)
}


fun testMatchWithWordIs() {
    val matchWordIs = Regex("\\bis\\b")
    listOf("is", " is ", " is", "is ").forEach {
        it.logMatchRegex(matchWordIs)
    }
}

fun main() {
    testMatchWithWordIs()
}