package cn.lazycat.codewing.coder;

import cn.lazycat.codewing.coder.replace.Replacer;
import cn.lazycat.codewing.coder.replace.label.ListLabelReplacer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class CoderBuilder {


    private CoderBuilder() {

    }

    public static Coder build() {
        return new Coder(schema, replacerList);
    }

    public static void setSchemaInputFile(String path)
            throws IOException {
        schema.setInput(new FileInputStream(path));
    }

    public static void appendReplacer(Replacer replacer) {
        replacerList.add(replacer);
    }

    public static void appendBasicReplacer() {
        replacerList.addAll(basicReplacer);
    }

    public static void clearReplacer() {
        replacerList.clear();
    }

    public static void deleteReplacer(Class<?> replacerCls) {
        Iterator<Replacer> ite = replacerList.iterator();
        while (ite.hasNext()) {
            Replacer replacer = ite.next();
            if (replacer.getClass().equals(replacerCls)) {
                ite.remove();
            }
        }
    }

    private static Schema schema;

    private static List<Replacer> replacerList;

    private static List<Replacer> basicReplacer;

    static {
        basicReplacer.add(new ListLabelReplacer());

        replacerList = basicReplacer;
    }
}
