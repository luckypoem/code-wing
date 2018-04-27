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

    public static String deleteLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    public static String deleteFirstChar(String str) {
        return str.substring(1, str.length());
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

    public static String lowerFirstCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
    }

    private static Matcher getMatcher(String str, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE |
                Pattern.DOTALL);
        return pattern.matcher(str);
    }
}
