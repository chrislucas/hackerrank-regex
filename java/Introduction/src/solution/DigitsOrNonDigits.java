package solution;

// https://www.hackerrank.com/challenges/matching-digits-non-digit-character/problem?h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DigitsOrNonDigits {


    private static final String [] SAMPLE =  new String[]{
            "11D22d3333"
            ,"22a2222222"
            ,"22a22A2222"
            ,"22a22A222+"
            ,"22a22A222a"
            ,"22z22A2222"
            ,"22z22Z2222"
            ,"22+22.2222"
            ,"22'22'2222"
            ,"22\"22\"2222"
    };



    private static void checker(String regex){
        Pattern p = Pattern.compile(regex);
        for (String s : SAMPLE) {
            Matcher m = p.matcher(s);
            System.out.printf("Pattern: %s %s com Sample: %s%n"
                , regex
                , m.find() ? "corresponde" : "nao corresponde"
                    , s
            );
        }
    }


    public static void main(String[] args) {
        checker("(\\d{2}\\D){2}\\d{4}");
    }
}
