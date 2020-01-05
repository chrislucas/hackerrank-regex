package solutions.kotlin.samples

// fonte de estudo: https://www.regular-expressions.info/dot.html#linebreak
// https://stackoverflow.com/questions/1552749/difference-between-cr-lf-lf-and-cr-line-break-types

/**
 * Todos os flavors de regex tratam o \n (newline) comoi line break
 * Arquivos de texto UNIX terminam as linhas com \n no Windows com \r\n (CR Carriage return e LF Line Feed).
 * Quando estamos executando Windows ao ler um arquivo o par \r\n eh convertido para \n
 * e ao escrever num arquivo \n eh convertido para \r\n
 *
 * ALguns flavors regex permitem que escolhamos qual caracter deve ser tratado como line break. Java
 * possui UNIX_LINES options que permite tratar somente \n como line break, PCRE possui opcoes que permite
 * tratar como line break somente \n ou \r ou \r\n ou um dos caracteres unicode
 * */

fun main() {}