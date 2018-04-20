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
        BeanPool.addBean("p1", new Person("Tang", 21));
        BeanPool.addBean("p2", new Person("Hehe", 30));

        List<Person> persons = new LinkedList<>();
        persons.add(new Person("DaDa", 1));
        persons.add(new Person("Xx", 2));
        persons.add(new Person("NN", 3));

        BeanPool.addBean("persons", persons);

        CoderBuilder.setSchemaInputFile("/Users/lazycat/Desktop/code/work/codewing/coder/src/schema-demo.txt");
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
