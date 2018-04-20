package cn.lazycat.codewing.coder.replace.label;

import cn.lazycat.codewing.coder.exception.ObjectNotListException;
import cn.lazycat.codewing.coder.tool.ReflectTool;
import cn.lazycat.codewing.coder.tool.StringTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListLabelReplacer extends LabelReplacer {

    @Override
    protected String getLabelName() {
        return "foreach";
    }

    @Override
    protected String[] getParamNames() {
        return new String[] { "list", "var" };
    }

    @Override
    protected String generateResultString(String content) {

        Map<String, String> params = getParams();
        String listId = params.get("list");

        Object bean = beans.get(listId);
        if (!(bean instanceof List)) {
            throw new RuntimeException(new ObjectNotListException(listId));
        }

        String varName = params.get("var");

        Map<String, String> fields = createElFieldMap(content, varName);
        List list = (List) bean;

        StringBuilder sb = new StringBuilder();
        String loop;
        for (Object obj : list) {
            loop = content;
            if (fields != null) {
                for (Map.Entry<String, String> entry : fields.entrySet()) {
                    String el = entry.getKey();
                    String field = entry.getValue();

                    String replace;
                    if (field.contains(".")) {
                        field = field.substring(1, field.length());
                        replace = ReflectTool.getField(obj, field);
                    }
                    else {
                        replace = obj.toString();
                    }

                    loop = loop.replaceAll(el, replace);
                }
            }
            sb.append(loop);
        }

        boolean deleteLast = true;
        String deleteLastStr = params.get("deleteLast");
        if ("false".equals(deleteLastStr)) {
            deleteLast = false;
        }

        if (deleteLast) {
            StringTool.deleteLastChar(sb);
        }

        return sb.toString();
    }

    private static Map<String, String> createElFieldMap(String content, String var) {
        if (content.length() <= 3) {
            return null;
        }
        int len = content.length();
        Map<String, String> map = new HashMap<>();
        String sub;
        for (int i = 0; i < len;  i++) {

            sub = content.substring(i, len);
            if (sub.startsWith("${" + var)) {
                int j = sub.indexOf('}');
                String el = sub.substring(2, j);

                String field;
                if (el.contains(".")) {
                    field = "." + el.split("\\.")[1];
                }
                else {
                    field = el;
                }

                map.put("\\$\\{" + el + "}", field);
            }

        }

        return map;
    }
}
