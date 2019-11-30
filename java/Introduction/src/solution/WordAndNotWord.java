package solution;

// https://www.hackerrank.com/challenges/matching-word-non-word/problem?h_r=next-challenge&h_v=zen

public class WordAndNotWord {

    private static final String sampleWithDollar = "   $          $   ";

    // xxxXxxxxxxxxxxXxxx
    private static final String [] SAMPLES = {
            sampleWithDollar
        ,sampleWithDollar.replaceAll("\\s", "*")
        ,sampleWithDollar.replaceAll("\\s", "_")
        ,sampleWithDollar.replaceAll("\\s", "a")
        ,sampleWithDollar.replaceAll("\\s", "*")
        ,"   *          *   ".replaceAll("\\s", "s")
        ,"   +a         +   ".replaceAll("\\s", "s")
        ,"   +a         -   ".replaceAll("\\s", "s")
        ,"123+1a1bbba___-   ".replaceAll("\\s", "s")
        ,"123+1a1bbba__$-   ".replaceAll("\\s", "s")
        ,"$13+1a1bbba__$-   ".replaceAll("\\s", "s")
        ,"$13+1a1bbba__$-   "
        ,"www.hackerrank.com"
    };

    public static void checker(String regex) {
        UtilsCheckRegex.checker(regex, SAMPLES);
    }

    /**
     * Meta caracter \W corresponde a [a-zA-Z0-9_]
     *Meta caracter \w corresponde a caracteres nao utilizados em palavraws
     * como por exemplo cifrao $
     * */

    public static void main(String[] args) {
        // o exercicio pede para escrever uma regex
        // que corresponda ao padrao xxxXxxxxxxxxxxXxxx
        // x = caracter de palavras e X = o contrario
        checker("\\w{3,}\\W\\w{10}\\W\\w{3,}");
    }
}
