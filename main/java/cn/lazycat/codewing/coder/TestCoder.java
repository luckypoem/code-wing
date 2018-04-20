package cn.lazycat.codewing.coder;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TestCoder {

    public static void main(String[] args) throws IOException {
        List<String> list = new LinkedList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        BeanPool.addBean("list", list);
        CoderBuilder.setSchemaInputFile("C:\\Users\\63102\\Desktop\\work\\codewing\\coder\\src\\schema-demo.txt");
        CoderBuilder.appendBasicReplacer();
        Coder coder = CoderBuilder.build();
//        Schema schema = new Schema();
//        schema.setInput(new FileInputStream("/Users/lazycat/Desktop/code/work/codewing/coder/src/schema-demo.txt"));
//        List<Replacer> replacers = new LinkedList<>();
//        replacers.add(new ListLabelReplacer());
//        Coder coder = new Coder(schema, replacers);

        System.out.println(coder.makeCode());
    }

}
