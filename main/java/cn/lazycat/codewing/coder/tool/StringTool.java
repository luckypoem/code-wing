package cn.lazycat.codewing.coder.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provide some handy String tools.
 */
public class StringTool {

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static void deleteLastChar(StringBuilder sb) {
        sb.deleteCharAt(sb.length() - 1);
    }

    public static boolean isMatching(String str, String regex) {
        return getMatcher(str, regex).matches();
    }

    public static String replace(String str, int start, int end, String target) {
        return str.substring(0, start) + target + str.substring(end, str.length());
    }

    public static String upperFirstCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

    public static int nextCharIndex(String str, int start, char ch) {
        char[] characters = str.toCharArray();
        for (int i = start; i < characters.length; ++i) {
            if (characters[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    private static Matcher getMatcher(String str, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE |
                Pattern.DOTALL);
        return pattern.matcher(str);
    }
}
