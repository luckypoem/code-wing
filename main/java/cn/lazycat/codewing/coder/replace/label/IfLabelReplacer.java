package cn.lazycat.codewing.coder.replace.label;

import cn.lazycat.codewing.coder.exception.BeanNotFoundException;
import cn.lazycat.codewing.coder.tool.ReflectTool;

public class IfLabelReplacer extends LabelReplacer {

    @Override
    protected String getLabelName() {
        return "if";
    }

    @Override
    protected String[] getParamNames() {
        return new String[] { "test" };
    }

    @Override
    protected String generateResultString(String content) {

        String condition = getParams().get("test");

        Object test;
        if (condition.contains(".")) {
            String[] tmp = condition.split("\\.");
            Object obj = beans.get(tmp[0]);

            if (obj == null) {
                throw new BeanNotFoundException(tmp[0]);
            }

            String fieldName = tmp[1];

            test = ReflectTool.getField(obj, fieldName);
        }
        else {
            test = beans.get(condition);
        }

        if ("true".equals(test)) {
            return content;
        }
        else if ("false".equals(test)) {
            return "";
        }

        return "";
    }
}
