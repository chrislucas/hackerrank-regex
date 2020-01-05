package solution;

// https://www.hackerrank.com/challenges/matching-whitespace-non-whitespace-character/problem?

public class WhitespaceOrNoWhitespace {

    private static final String [] SAMPLES = {
        "aa aa aa"
        ," aa aa aa"
        ,".. .. aa"
        ,".. .. .+"
        ,";; ;; ;;"
        ,";; ;; ;"
        ,";; ;; ~\\"
        ,";; ;: ~"
        ,";; {{ ~"
        ,"'~ .. ~"
        ,"'~ \"\" ~"
        ,"** ** *"
        ," ** ** *"
        /*
        * Abaixo segue um exemplo S = XXXxXXxXX
        * onde X = qualque rcoisa menos espaco e x = espaco
        * XXxXXxXX eh uma substring de S, assim alguns participantes
        * desse exercicio afirmam que esse padrao deveri ser pego pela regex
        * que resolve esse exercicio
        *
        * */
        ,"122 23 56"
        ,"123 123 123"
    };

    public static void checker(String regex) {
        UtilsCheckRegex.checker(regex, SAMPLES);
    }

    private static void test() {
        System.out.println(" ".matches("\\S"));
        String pattern = "\\S{2}";
        System.out.println(" a".matches(pattern));
        System.out.println("sa".matches(pattern));
        System.out.println("s\n".matches(pattern));
    }

    private static void test2() {
        for (String s : "abc \\xA0 def \\x85 xyz".split("\\s")) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        checker("^\\S{2,}\\s(\\S{2}\\s?){2}");
        //test2();
    }
}
