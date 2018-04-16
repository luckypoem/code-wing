package cn.lazycat.codewing.coder.tool;

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
}
