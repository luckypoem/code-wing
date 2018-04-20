package cn.lazycat.codewing.coder.replace.label;

import cn.lazycat.codewing.coder.exception.ObjectNotListException;
import cn.lazycat.codewing.coder.tool.BeanTool;
import cn.lazycat.codewing.coder.tool.ReflectTool;
import cn.lazycat.codewing.coder.tool.StringTool;

import java.util.*;

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

        Map<String, String> fields = BeanTool.parseEL(content,
                Collections.singletonList(varName));
        List list = (List) bean;

        StringBuilder sb = new StringBuilder();
        String loop;
        for (Object obj : list) {
            loop = content;
            if (fields != null) {
                for (Map.Entry<String, String> entry : fields.entrySet()) {

                    String replace = BeanTool.getBeanReplacement(entry.getValue(), obj);
                    loop = loop.replaceAll(entry.getKey(), replace);
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

}
