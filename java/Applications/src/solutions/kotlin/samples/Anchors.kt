package solutions.kotlin.samples

import solutions.kotlin.utils.*

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
        , " "
        , "                  a              "
        , "                  a parada              "
        , "  *                a teste              \n a * "
        , "  *                a teste              \n parada * "
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
 * \\A corresponde somente com o inicio da String
 *
 * \\Z corresponde somente com o fim da string
 * O interessante desse exemplo eh que \\A e \\Z funciona para uma string com multiplas
 * linhas sem a necessidade de usar options MULTILINE
 *
 * Esses 2 metachars nao correspondem a quebra de linha \n
 * */
fun testStartAndEndOfString() {
    // inicia e termina com um ou mais numeros
    val startAndEndsOnlyNumbers = Regex("\\A\\d+.*\\d+\\Z", RegexOption.MULTILINE)
    var list = listOf(
        "123123123123123"
        ,"1a3"
        ,"1aaaaaaaaaaaaa3"
        ,"1aaaaaaaaaaaaa"
        ,"aaaaaaaaaaaaa3"
        ,"0aaaaaaaaaaaaa3"
        ,  "1231231\n23123123"
    )
    list.forEach {
        it.logMatchRegex(startAndEndsOnlyNumbers)
    }
    lineSeparation()
    // inicia com qualquer caracter (incluindo \r ou \n) e termina com N espacos em branco
    val endingWithNWhiteSpaces = Regex("[\\w-*\\s]*\\s+\\Z")
    list = listOf("a  "
        , "a\n        "
        , "a -\n        "
        , "a -\n\n\n\n\n\n\n\n        "
        , "a\n        *"
        , "a\n\n\n\n\n\n atestado LF        * "
        , "a\r\r\r\r\r\r atestado CR       * "
        , "a\n\r\n\r\n\r\n\r\n\r\n\r atestado CRLF       * "
        , "a\n        * "
        , "a \n        * "
        , " \n         "
    )
    list.forEach {
        it.logMatchRegex(endingWithNWhiteSpaces)
    }

}

fun testFlagMultiline() {
    // inicia com 1 ou mais numeros e termiina com numero
    // (?m) eh uma flag para que a regex atenda a multiplas linhas
    val matchWithStartAndEndWithDigitsMultiline = "^\\d+[\\w\\s]\\d+(?m)$".toRegex()
    val matchWithStartAndEndWithDigits = "^\\d+[\\w\\s]\\d+$".toRegex()
    val matchWithStartAndEndWithDigitsAndMoreCharBetween = "^\\d+[\\w\\s]+\\d+$".toRegex()
    val list = listOf( "123123123123123"
        // String de numeros com um unico espaco
        , "123123 123123123"
        // Testando a regex para verificar uma String comeca e termina com numeros, mas
        // entre o inicio e o fim ha caracteres
        , "1a3"
        , "0xf0"
        , "0xff"
        , "1231231\n23123123"
        , "1231231\n\n23123123"
        , "123\n3\n"
        , "123\n3\n0"
    )

    list.forEachIndexed {
        index, it ->
        it.logMatchesRegex(
           listRegex = *arrayOf( matchWithStartAndEndWithDigitsMultiline, matchWithStartAndEndWithDigits,
               matchWithStartAndEndWithDigitsAndMoreCharBetween)
            , after = { println("*****************************************************************") }
            , assertion = index < list.size - 1
        )
    }

    println("\n****************** Outro teste ***************************\n")

    // inicia com 1 digito seguido de varios caracteres quaisquer inclusive espaco
    // termina com 1 digito
    val regex = Regex("\\A\\d[\\w\\s]+\\d\\Z")
    list.forEach {
        it.logMatchRegex(regex)
    }
}

fun testOnlyDigits() {
    val list = listOf(
        "123"
        , "123\n"
        , "123\n\n\n\n123"
        , "123\n\n\n\n"
        , "123a3"
        , "0x0f"
        , "0\n0"
        , "00\n0"
        , "12345654654654654654646546546546546546546541321313131313213213131"
        , "1234565465465465465464654654654654654654654132131313131321321313a"
    )

    list.forEachIndexed {
        i, s ->
        s.logMatchesRegex(
            listRegex = *arrayOf(
                Regex("^\\d+$")
                , Regex("^\\d+(m?)$")
                , Regex("\\A\\d+\\Z")
                , Regex("\\A\\d+(m?)\\Z")
                , Regex("\\A\\d+\\z")       // \\z matches
            )
            , after = { println("******************************************************") }
            , assertion = i < list.size - 1
        )
    }
}

fun testEndOfString() {
    // 1 caracter
    val manyLineBreaksAndMultiLineModeOff = "[a-zA-Z]\\n+$".toRegex(RegexOption.MULTILINE)
    "a\n\n\n\n".logMatchRegex(manyLineBreaksAndMultiLineModeOff)
}

fun main() {
    testEndOfString()
}