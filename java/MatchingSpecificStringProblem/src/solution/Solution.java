package solution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.*;

// https://www.hackerrank.com/challenges/matching-specific-string/problem
public class Solution {

    public static final String SAMPLE_TEXT_1 = "The hackerrank team is on a mission to flatten the world by restructuring " +
            "the DNA of every company on the planet. We rank programmers based on their coding skills, " +
            "helping companies source great programmers and reduce the time to hire. As a result, we are " +
            "revolutionizing the way companies discover and evaluate talented engineers. " +
            "The hackerrank platform is the destination for the best engineers to hone their " +
            "skills and companies to find top engineers.";

    public static void countPatternFinded(String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(SAMPLE_TEXT_1);
        int counting = 0;
        while (matcher.find()) {
            counting++;
        }
        out.println(counting);
    }

    public static void main(String[] args) {
        countPatternFinded("hackerrank");
        countPatternFinded("t\\w+p");
        countPatternFinded("r\\w+g");
    }
}
