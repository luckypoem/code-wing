package cn.lazycat.codewing.coder.tool;

public class BeanTool {

    public String[] parseEL(String el) {
        el = el.replaceFirst("$\\{", "");
        el = el.replaceFirst("}", "");
        return el.split("\\.");
    }

}
