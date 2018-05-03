package cn.lazycat.codewing.coder;

import cn.lazycat.codewing.coder.replace.BeanReplacer;
import cn.lazycat.codewing.coder.replace.Replacer;
import cn.lazycat.codewing.coder.replace.label.IfLabelReplacer;
import cn.lazycat.codewing.coder.replace.label.ListLabelReplacer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoderBuilder {

    private CoderBuilder() {
    }

    public static Coder build(String inputPath) throws IOException {
        List<Replacer> replacers = new ArrayList<>(3);
        replacers.add(new BeanReplacer());
        replacers.add(new ListLabelReplacer());
        replacers.add(new IfLabelReplacer());

        Schema schema = new Schema();
        schema.setInput(new FileInputStream(inputPath));

        return new Coder(schema, replacers);
    }
}
