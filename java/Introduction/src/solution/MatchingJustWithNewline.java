package solution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchingJustWithNewline {

    private static final String [] TEXT  = {
            "If you want to match (.) in the test string, you need to escape the dot by using a slash \\"
            ,"\n"
            ,""
            ,"."
            ," z\n"
            ," \n"
            ,"abc.def.ghi.jkl"
            ,"abc.def.ghi.123"
            ,"abc.def.ghi.1234"
            ,"abdc.def.ghi.1234"
            ,"abdd.def.ghi.123"
            ,"abdd.df.ghi.123"
            ,"abdd.df.hi.123"
            ,"abdd.dfe.hi.123"
            ,"123.dfe.hi.123"
            ,"123.dfe.hi4.123"
            ,"123.dfe.hi4.12\n"
            ,"abc.dfe.hi4.12s\n"
            ,"..............."
            ,"`!@.#$%.^&*.()_"
    };

    private static void check(String pattern) {
        Pattern p = Pattern.compile(pattern);
        for (String s : TEXT) {
            Matcher m = p.matcher(s);
            System.out.printf("Pattern: %s %s com Sample: %s%n"
                    , pattern
                    , m.matches() ? "combina": "nao combina"
                    , s.replace("\n", "*"));
        }
    }

    public static void main(String[] args) {
        //System.out.println("Corresponde somente com newline");
        //check("[^.]");
        System.out.println("corresponde com o padrao 'abc.def.ghi.jkl' qnao podendo ser newline");
        //check("\\w{3}(\\.\\w{3}){3}");
        //check("(\\w{3}\\.?){4}");
        check("(.{3}\\.?){4}");
    }
}
