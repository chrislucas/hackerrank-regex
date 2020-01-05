package solutions.kotlin.samples

import solutions.kotlin.utils.logMatchRegex

/**
 * https://www.regular-expressions.info/charclass.html
 *
 * Com o recurso de "conjutno de caracteres ou character class|set" podemos usar
 * o mecanimos de regex para procurar por corresponder UM ÚNICO caracter de um conjunto
 * deles numa string
 *
 * Exemplo
 * regex -> th[iu]s corresponde com this e thus
 * regex -> th[iu]s corresponde com this e thus
 * Importante entender que
 * 1) o char set corresponde a um unico caracter dentre os caracteres do conjunto
 * [aeiou] quer dizer que so ocorre correspondencia com um desses caracteres
 *
 * 2) se usar os operadores de quantificacao +,* ou ? podemos ter resultados inesperados
 * como por exemplo
 * [ai]+ corresponde com aa..., ai..., ii... so on
 *
 * */

fun testCharSetWithQuantifierOp() {
    val regex1 = "p[aã][io]+".toRegex()
    // uma lista com string que correspondem a regex acima
    listOf("paioioioioio", "paoooooooooo", "pãoooooooooooo", "paioioioio", "pao", "pai")
        .forEach { println(it.logMatchRegex(regex1)) }
}

fun testCharSetWithQuantifierOpCorrectRepeat() {
    /**
     * Caso queiramos que a nossa regex corresponda com uma repeticao de um caracter
     * precisamos usar um recurso chamado backreference (\1)
     *
     * Exemplo
     * [io]\\1+ corresponde com i, o, ii..., oo... mas nao corresponde
     * com i...o...
     *
     * */
    val regex2 = "pa([io])\\1+".toRegex()
    println("paiiiii".logMatchRegex(regex2))
    println("paiiiii".logMatchRegex(regex2))
    println("paiioo".logMatchRegex(regex2))
    println("paio".logMatchRegex(regex2))
}

fun testCharClass101() {
    println("this".logMatchRegex("th[iu]s".toRegex())) // corresponde com this ou thus
    println("thus".logMatchRegex("th[iu]s".toRegex())) // corresponde com this ou thus
    println("gray".logMatchRegex("gr[ae]y".toRegex()))

    val regexpNumberFormatHex = Regex("0[xX][a-fA-F0-9]+")
    println("0xff".logMatchRegex(regexpNumberFormatHex))
    println("0xf0".logMatchRegex(regexpNumberFormatHex))
    println("0Xf0".logMatchRegex(regexpNumberFormatHex))
}

fun testNegateCharClass() {
    /**
     * Quando usamos a negacao dentro de um conjunto de caracteres queremos procurar
     * por uma string que que em uma determinada parte tenha "QUALQUER CARACTER" que
     * nao seja o do conjunto
     *
     * Por exemplo
     * regex: q[^u] = corresponde a uma string que comece com caracter "q" a seguir
     * seja qualquer caracter diferente de "u", nao podemos interpretar como se com essa regex
     * quisemos corresponder com uma string q comece com "q" nao seguido de "u", pq o 'q' poderia
     * ser o ultimo (e unico) caracter e a regex nao corresponderia com a string, em nenhum ponto
     * */
    "q".logMatchRegex("q[^u]".toRegex())
    "q".logMatchRegex("q(?!u)".toRegex())
    "q_".logMatchRegex("q(?!u)_".toRegex())         // negative lookahead -> "q" nao seguido por "u" seguido por _
    "qa_".logMatchRegex("q(?!u)_".toRegex())
    "q ".logMatchRegex("q[^u]".toRegex())
    "qa_".logMatchRegex("q[^u]_".toRegex())
    "Prozac".logMatchRegex("\\w+c[^u]".toRegex())
    "Prozac ".logMatchRegex("\\w+c[^u]".toRegex())
    "t3st".logMatchRegex("t[^aeiou]st".toRegex())
    "tast".logMatchRegex("t[aeiou]st".toRegex())

    /**
     * Se quisermos uma regex que corresponda com "A" nao seguido de "B
     * usamos o negative lookahead(?!)
     * */
    "http".logMatchRegex("http(?!s)".toRegex())
    "https".logMatchRegex("http(?!s)".toRegex())
}

// https://www.regular-expressions.info/characters.html#special
//fun testSpecialChar() {}

fun testBackReference() {
    "333".logMatchRegex(Regex("([0-9])\\1+"))
    "333".logMatchRegex(Regex("([0-9])\\1{3}"))
    "123".logMatchRegex(Regex("([0-9])\\1{3}"))
}

fun main() {
    testBackReference()
}