package cn.lazycat.codewing.coder;

import cn.lazycat.codewing.coder.replace.Replacer;
import cn.lazycat.codewing.coder.replace.label.ListLabelReplacer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CoderBuilder {

    private CoderBuilder() {
    }

    public static Coder build() {
        return new Coder(schema, replacerList);
    }

    public static void setSchemaInputFile(String path)
            throws IOException {
        if (schema == null) {
            schema = new Schema();
        }
        schema.setInput(new FileInputStream(path));
    }

    public static void appendReplacer(Replacer replacer) {
        replacerList.add(replacer);
    }

    public static void appendBasicReplacer() {
        replacerList.add(new ListLabelReplacer());
    }

    public static void clearReplacer() {
        replacerList.clear();
    }

    private static Schema schema;

    private static List<Replacer> replacerList = new LinkedList<>();
}
