package solutions.kotlin.samples

import solutions.kotlin.utils.log
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
 * Existem 3 posicoes numa string que delimitam o chamado "limites|fronteiras da string"
 * 1) antes do primeiro caracter na string, SE O PRIMEIRO CARACTER for um "caracter de palavras"
 * o que na maioria dos "flavors" de regex sao os caracteres considerados no metachar \\w, com excecao
 * do Java que tbm considera caracteres Unicode
 *
 * 2) depois do ultimo caracter na string, SE O ULTIMO CARACTER for um "caracter de palavra"
 *
 * 3) entre 2 caracteres de palavras
 *
 * Uma segunda descricao (https://www.regular-expressions.info/refwordboundaries.html) diz que:
 * "\b corresponde a uma posicao que eh seguida por um 'caracter de palavras' mas nao eh precedido por um, ou
 * que e precedido por um 'caracter de palavra' mas nao seguido por um"
 *
 * Assim \b corresponde a uma posicao numa string cujos limites sejam [\w  \W] (), ou se estiver no meio
 * de uma string [\w  \w]
 *
 * */

fun testWordBoundary_1() {
    val regex1 = Regex("\\b\\d+\\b")
    val regex2 = Regex(".*\\b\\d+\\b.*")

    val list = listOf(
        "1010"
        , " 101010"
        , " 101010 "
        , " 101010a "
        , " a101010a "
        , "1"
        , "a1a"
    )

    list.forEachIndexed { i, s ->
        s.logMatchesRegex(regex1
            , regex2
            , after = { println("*********************************************") }
            , assertion = i < list.size - 1)
    }
}

fun searchAWord(word: String, textTarget: String) {
    val matchResult = Regex("\\b$word\\b").find(textTarget)
    matchResult?.let {
        var mr: MatchResult? = it
        while (mr != null) {
            println("${mr.value}, ${mr.range}")
            mr = mr.next()
        }
    }
}

fun testSearchAWord() {
    searchAWord(
        "Before"
        , "Before the first character in the string, " +
                "if the first character is a word character."
    )
    searchAWord(
        "After", "After the last character in " +
                "the string, if the last character is a word character."
    )
    val text1 = "Between two characters in the string, " +
            "where one is a word character and the other is not a word character."
    searchAWord("Between", text1)
    searchAWord("is", text1)
    // passando uma regex como argumento (character(s) ? 0 ou 1 ocorrencia do caracter "s"
    searchAWord("characters?", text1)
}


fun testMatchWithWordIs() {
    val matchWordIs = Regex("\\bis\\b")
    val list = listOf("is"
        , " is "
        , " is"
        , "is "
        , " is is "
    )
    list.forEach {
        it.logMatchRegex(matchWordIs)
    }
}

fun testBoundaryOfString() {
    val phrases = listOf(
        "is"
        , " is "
        , " is"
        , "is "
        , "xis"
        , "xis "
        , " xis "
        , " xis"
        , "this"
        , "this "
        , " this "
        , "this "
    )
    val listRegex = arrayOf(
        Regex("\\bis")
        , Regex("is\\b")
        , Regex("\\Bis")
        , Regex("is\\B")
    )
    phrases.forEach { phrase ->
        listRegex.forEach { regex ->
            val matchResult = regex.find(phrase)
            matchResult?.log(phrase, regex)
        }
    }
}

/**
 * https://stackoverflow.com/questions/1324676/what-is-a-word-boundary-in-regexes/1325631#1325631
 * # same as using a \b before: (?(?=\w) (?<!\w)  | (?<!\W) )
 * # same as using a \b after: (?(?<=\w) (?!\w)  | (?!\W)  )
 * codigo abaixo estuda essa afirmacao feita na post no stackoverflow
 * */

//fun test() {}

/**
 *
 * Minha interpretacao da definicao de boundary segundo usuario do stackoverflow no post citado acima
 * "Os limites de uma palavra sao definidos como a posicao que eh ou precedida por um caracter de palavra (definido
 * pelo motor de regex que se esta usando) e nao eh seguido por um caracter de palavra ou, seguido por um caracter de palavra
 * mas"
 *
 * */
val phrases = listOf(
    "This is a cat, and she's awesome"
    , "This is a c a t, pand she's pawesome"
    , "This is a cat, a awesome cat"
    , "This is a cat\nA awesome cat"
    , "This is a cat\nA awesome cat\nI love cat\nThe dog ates cat\nShe has a lot of cats"
    , "cat is awesome"
    , "This is a cat, a awesome cat"
    , "is,is,is,is,is,is,is,is,is,is,is"
    , "*is,is,|is,#is,sis, is , sis , asis ,asis,*is, is,*is,\$is,%is,-is,+is,is"
    , "is*, is*, is|, is#, is , sis, asis ,asis,is*,is ,is$,is%,is-,is+, is,   is ,  is  "
    , " 'isnot' | 'is not' | is not | isnot"
    , " 'isnot' a 'is not' a is not a isnot ais not aisnot"
    , " 1) 'isnot', 2) ' is not', 3) ' is not ', 4) 'is not', 5) 'aisnot', 6) 'a is not', 7) 'a isnot'" +
            ", 8) 'ais not', 9) 'aisnot'"
    , "'isnot' | 'is not' |is not|isnot"
    , "is\nis\nis\nis\nis\nis"
    , "is not\nis not\nis not\nis not\nis not\nis not"
)
fun testReplaceWithABoundaryWord() {

    val listOfRegex = listOf(
        Regex("\\ba") to "*"
        // as duas regex abaixo vao nos dar uma nocao da diferenca sobre word boundary antes e depois de um caracter/string de buscca
        , Regex("\\bis") to "isn't"
        , Regex("is\\b") to "isn't"
        , Regex("\\bis\\b") to "(**)"
        //, Regex("\\bis not") to "isn't"
        , Regex("\\b(is not|isnot)") to "isn't"
        //, Regex("is not\\b") to "isn't"
        , Regex("(is not|isnot)\\b") to "isn't"
        //, Regex("\\bis not\\b") to "isn't"
        , Regex("\\b(is not|isnot)\\b") to "isn't"
        , Regex("is$") to "isn't"
        , Regex("is(?m)$") to "isn't"
        //, Regex("\\bisnot") to "isn't"
        //, Regex("isnot\\b") to "isn't"
        //, Regex("\\bisnot\\b") to "isn't"
        // A regex cat\\b e cat$ vai nos mostrar a diferenca de word boundary e fim de string/line
        , Regex("cat\\b") to "dog"     // essa regex vai substituir a primeira ocorrencia de 'cat' na segunda frase
        , Regex("\\bcat") to "dog"
        , Regex("cat$") to "dog"       // essa regex vai substituir a ultima ocorrencia de 'cat' na segunda frase
        , Regex("cat(?m)$") to "dog"   // essa regex vai substituir todas as ocorrencias de cat no final da string
    )
    phrases.forEachIndexed { i, phrase ->
        println("Frase ${i + 1}: '$phrase'")
        println("----------------------------------------")
        listOfRegex.forEachIndexed { j, pair ->
            val regex = pair.first
            println(String.format("Replace(%d), Regex '%s', Result: '%s'", (j + 1)
                , regex
                , phrase.replace(regex, pair.second)))
        }
        println("*************************************************************")
    }
}

fun testReplaceWithANonBoundaryWord() {
    val pairRegexReplace = listOf(
        Regex("\\Ba") to "*"
        , Regex("a\\B") to "*"
        , Regex("\\Bis") to "**"
        , Regex("is\\B") to "**"
    )

    phrases.forEachIndexed { i, phrase ->
        println("Frase ${i + 1}: '$phrase'")
        println("----------------------------------------")
        pairRegexReplace.forEachIndexed { j, pair ->
            val regex = pair.first
            println(String.format("Replace(%d), Regex '%s', Result: '%s'", (j + 1)
                , regex
                , phrase.replace(regex, pair.second)))
        }
        println("*************************************************************")
    }
}

fun main() {
    testReplaceWithANonBoundaryWord()
}