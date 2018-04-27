package cn.lazycat.codewing.coder.replace;

import cn.lazycat.codewing.coder.tool.BeanTool;
import cn.lazycat.codewing.coder.tool.StringTool;

import java.util.ArrayList;
import java.util.Map;

public class BeanReplacer extends Replacer {

    private String feature = null;

    @Override
    public String getFeature() {

        if (feature == null) {

            StringBuilder sb = new StringBuilder();
            sb.append(".*\\$\\{(");

            for (String id : beans.keySet()) {
                sb.append(id).append("(\\..+)?|");
            }

            StringTool.deleteLastChar(sb);
            sb.append(")}.*");

            feature = sb.toString();
        }

        return feature;
    }

    @Override
    public int getStart(String schemaStr) {
        return 0;
    }

    @Override
    public int getEnd(String schemaStr) {
        return schemaStr.length();
    }

    @Override
    public String replace(String placeholder) {

        Map<String, String> replaceMap = BeanTool.parseEL(placeholder,
                new ArrayList<>(beans.keySet()));

        if (replaceMap == null) {
            return placeholder;
        }

        for (Map.Entry<String, String> entry : replaceMap.entrySet()) {

            String el = entry.getKey();
            String id = el.substring(
                    el.indexOf("\\$\\{") + 4, el.length() - 1
            );

            if (id.contains(".")) {
                id = id.split("\\.")[0];
            }

            String replace = BeanTool.getBeanReplacement(
                    entry.getValue(), beans.get(id));

            placeholder = placeholder.replaceAll(el, replace);
        }

        return placeholder;
    }
}
