package cn.lazycat.codewing.coder.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanTool {

    public static Map<String, String> parseEL(String content,
                                              List<String> beanIds) {
        if (content.length() <= 3) {
            return null;
        }
        int len = content.length();
        Map<String, String> map = new HashMap<>();
        String sub;
        for (int i = 0; i < len;  i++) {

            sub = content.substring(i, len);

            for (String var : beanIds) {

                if (StringTool.isMatching(sub, "^\\$\\{" +
                        var + "(\\..+)?}.*$")) {
                    int j = sub.indexOf('}');
                    String el = sub.substring(2, j);

                    String field;
                    if (el.contains(".")) {
                        field = "." + el.split("\\.")[1];
                    } else {
                        field = el;
                    }

                    map.put("\\$\\{" + el + "}", field);
                }
            }

        }

        return map;

    }

    public static String getBeanReplacement(String field, Object bean) {
        String replace;
        if (field.contains(".")) {
            field = field.substring(1, field.length());
            replace = ReflectTool.getField(bean, field);
        }
        else {
            replace = bean.toString();
        }
        return replace;
    }
}
