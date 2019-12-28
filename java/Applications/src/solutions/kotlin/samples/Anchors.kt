package solutions.kotlin.samples

import solutions.kotlin.utils.lineSeparation
import solutions.kotlin.utils.logIfStringStartsOrEndsWithWhiteSpace
import solutions.kotlin.utils.logMatchRegex
import solutions.kotlin.utils.replaceWhiteSpacesInStartOrEnd

/**
 * Fonte: https://www.regular-expressions.info/anchors.html
 *
 * Regex usando caracteres litererais ("test123"), classes de caracteres ([a-zA-Z1-9], [aeiou])
 * ou o metacaracter dot (. ponto que so nao corresponde com line break) fazem com que o a regex engine
 * corresponda com um caracter único. Âncoras (^, $) sao diferentes, elas nao correspondem com
 * caracteres e sim com uma posicao antes, depois ou entre caracteres.
 *
 * O simbolo ^ corresponde com um caracter antes da primeira posicao da String e o $ corresponde
 * logo depois do ultimo caracter da String
 *
 * zero-length matches. (https://www.regular-expressions.info/zerolength.html)
 *
 * "Correspondencia de comprimento zero"
 *
 * Ancoras (end:$, start:^), lookaround ( (?=, ?:))
 *
 * */

fun testStartAndEndWith() {
    // Regex que testa se  Inicia com numero tem zero ou mais caracteres de a-z e termina com numero
    "1asbc4".logMatchRegex(Regex("^\\d[a-z]*\\d$"))
    listOf(
        "14"
        , "1asdasdasdasdasdsada4"
        , "1asdasdasdasdasdsadae"
    ).forEach {
        // Regex que testa se Inicia com numero, tem zero ou caracteres quaisquer e termina com numero
        it.logMatchRegex(Regex("^\\d.*\\d$"))
    }
}

fun testAnchorInStringWithSpace() {
    // Inicia com 1 ou mais espacos segue com 1 ou caracteres quaisquer exceto \n
    // e termina com 1 ou mais espacos
    val startAndEndsWithWhiteSpace = Regex("^\\s+(.|\\s)*\\s+$")
    val strs = listOf(
         "              qualquer coisa aqui com espacos no comeco e no final "
        ,"              qualquer coisa aqui com espacos no comeco e no final"
        ,"              qualquer coisa aqui com espacos no comeco e no final                "
        ,"           teste 123                "
        ,"           teste                "
    )

    strs.forEach {
        it.logMatchRegex(startAndEndsWithWhiteSpace)
    }

    lineSeparation()
    // Inicia com 1 ou mais espacos segue com 1 ou mais caracteres e/ou espacoies
    // e termina com 1 ou mais espacos
    val matchStringWithWhiteSpaceNoNumbers = Regex("^\\s+(a-z|\\s)+\\s+$")
    strs.forEach {
        it.logMatchRegex(matchStringWithWhiteSpaceNoNumbers)
    }

    strs.forEach { println(it.replaceWhiteSpacesInStartOrEnd()) }
}

fun testStringStartOrAndWithWhitespaces() {
    val list =    listOf(
        " a"
        , " a "
        , "                  a              "
    )
    list.forEach {
        it.logIfStringStartsOrEndsWithWhiteSpace()
    }

    lineSeparation()

    list.forEach {
        println(it.replaceWhiteSpacesInStartOrEnd())
    }

    lineSeparation()

    for (s in listOf(
        "atestado"
        , "amado"
        , "ama"
        , "amA"
        , "AmA"
        , "mandada"
        , "mandadA"
        , "eu nao tenho nada com isso ok Amanda"
        , "eu nao tenho nada com isso ok AMANDA"
        , "aa"
    )) {
        s.logMatchRegex(Regex("(^[aA].*)|(.*[aA]$)")) // inicia ou termina com a e tem o ou mais caracteres antes ou depois
    }


}

/**
 * \\A
 *
 * \\Z
 *
 * */
fun testPermanentStartAndEndOfString() {
    val startAndEndsOnlyNumbers = Regex("\\A\\d+\\Z")
    "123123123123123".logMatchRegex(startAndEndsOnlyNumbers)
    "1231231\n23123123".logMatchRegex(startAndEndsOnlyNumbers)
}

fun testMultiline() {
    // inicia com 1 ou mais numeros e termiina com numero
    // (?m) eh uma flag para que a regex atenda a multiplas linhas
    val matchOnlyStringOfNumbers = "^\\d+$(?m)".toRegex()
    listOf( "123123123123123"
        // String de numeros com um unico espaco
        , "123123 123123123"
        // Testando a regex para verificar uma String comeca e termina com numeros, mas
        // entre o inicio e o fim ha caracteres
        , "1a3"
        , "0xf0"
        , "0xff"
        , "1231231\n23123123"
    ).forEach {
        it.logMatchRegex(matchOnlyStringOfNumbers)
        it.logMatchRegex(Regex("^\\d+\$", RegexOption.MULTILINE))
    }
}

fun main() {
    testPermanentStartAndEndOfString()
}