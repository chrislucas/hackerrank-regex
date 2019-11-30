package solution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UtilsCheckRegex {

    private UtilsCheckRegex() {}

    static void checker(String regex, String[] sammples){
        Pattern p = Pattern.compile(regex);
        for (String sample : sammples) {
            Matcher m = p.matcher(sample);
            System.out.printf("Pattern: %s %s com Sample: %s%n"
                    , regex
                    , m.find() ? "corresponde" : "nao corresponde"
                    , sample
            );
        }
    }
}
