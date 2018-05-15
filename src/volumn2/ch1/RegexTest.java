package volumn2.ch1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HP-PC on 2018/5/15.
 */
public class RegexTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Pattern");
        String patternStr = in.nextLine();
        Pattern pattern = Pattern.compile(patternStr);

        while (true) {
            System.out.println("Enter string to match: ");
            String input = in.nextLine();
            if (input == null || "".equals(input)) {
                in.close();
                return;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                System.out.println("Match");
                int g = matcher.groupCount();
                if (g > 0) {
                    for (int i = 0; i < input.length(); i++) {
                        for (int j = 1; j <= g; j++) {
                            if (i == matcher.start(j)) {
                                System.out.print('(');
                            }
                        }
                        System.out.println(input.charAt(i));
                        for (int j = 1; j <= g; j++) {
                            if (i + 1 == matcher.end(j)) {
                                System.out.print(')');
                            }
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No match");
            }
        }
    }
}
