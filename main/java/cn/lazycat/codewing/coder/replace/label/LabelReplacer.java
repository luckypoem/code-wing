package cn.lazycat.codewing.coder.replace.label;

import cn.lazycat.codewing.coder.replace.Replacer;
import cn.lazycat.codewing.coder.tool.StringTool;

import java.util.HashMap;
import java.util.Map;

public abstract class LabelReplacer extends Replacer {

    protected abstract String getLabelName();

    protected abstract String[] getParamNames();

    protected abstract String generateResultString(String content);

    protected Map<String, String> getParams() {
        return this.params;
    }

    @Override
    public String getFeature() {
        String labelName = getLabelName();
        String[] paramNames = getParamNames();
        StringBuilder sb = new StringBuilder();
        sb.append("<wing:").append(labelName);
        if (paramNames != null) {
            sb.append(" ");
            for (String paramName : paramNames) {
                sb.append(paramName).append("='.+' ");
            }
            StringTool.deleteLastChar(sb);
        }
        sb.append(">.+</wing:").append(labelName).append(">");

        return sb.toString();
    }

    @Override
    public int getStart(String schemaStr) {
        return schemaStr.indexOf("<wing:" + getLabelName());
    }

    @Override
    public int getEnd(String schemaStr) {
        String stop = "</wing:" + getLabelName() + ">";
        return schemaStr.indexOf(stop) + stop.length();
    }

    @Override
    public String replace(String placeholder) {
        String labelDef = "<wing:" + getLabelName();
        String paramListStr = placeholder.substring(
                placeholder.indexOf(labelDef) + labelDef.length(),
                placeholder.indexOf(">")
        );
        String tmp[] = paramListStr.trim().split(" ");
        params = new HashMap<>(tmp.length);
        for (String eq : tmp) {
            String leftRight[] = eq.split("=");
            String paramName = leftRight[0];
            String paramValue = leftRight[1].substring(1, leftRight[1].length() - 1);

            params.put(paramName, paramValue);
        }

        int startContent = placeholder.indexOf(">") + 1;
        int endContent = placeholder.indexOf("</");

        return generateResultString(placeholder.substring(startContent, endContent));
    }

    protected Map<String, String> params = null;
}
