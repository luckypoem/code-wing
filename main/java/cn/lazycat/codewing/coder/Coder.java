package cn.lazycat.codewing.coder;

import cn.lazycat.codewing.coder.exception.BeanNotFoundException;
import cn.lazycat.codewing.coder.replace.Replacer;
import cn.lazycat.codewing.coder.tool.StringTool;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Coder is the core class of the entire coder component. Codewing generates
 * the code mainly by relying on this class. Using the makeCode method, you
 * can generate the code you want.Coder needs some support from other classes,
 * where Schema is the most important, and it provides templates for generating
 * code, typically from a file, which you need to create a Schema yourself and
 * upload it to coder. The schema can set the input stream through setInput.
 * </p>
 * <p>
 * Another object is replacer, which is used to replace placeholders in the template.
 * You can specify the replacer to be used with setReplacer. You can choose to not
 * override this method so that the coder will use the default replacers. Of course,
 * you can develop replacer yourself, which needs to implement the Replacer interface,
 * and then add your own replacer in setReplacer. If you want both the basic replacers
 * and the custom replacer, you can use the static field 'basicReplacers' in interface
 * Replacer for all codewing provide basis, or see the package cn.lazycat.codewing.coder.replace
 * to choose your preferred implementation class.
 * </p>
 */
@SuppressWarnings("unused")
public class Coder {

    /**
     * Create a code object that requires the support coder of schema to generate code.
     * @param schema the support schema
     */
    public Coder(Schema schema) {
        this.schema = schema;
        initBeans();
    }

    /**
     * Generate code from the schema.
     * @return result code, all placeholders are replaced.
     */
    public String makeCode() {

        if (replacerList == null) {
            replacerList = Replacer.getBasicReplacer();
        }

        String template = schema.getSchemaString();

        for (Replacer replacer : replacerList) {

            if (replacer.needBeans()) {
                replacer.setBeans(beans);
            }

            while (StringTool.isMatching(template,
                    ".*" + replacer.getFeature() + ".*")) {

                int start = replacer.getStart(template);
                int end = replacer.getEnd(template);

                String placeholder = template.substring(start, end);

                template = StringTool.replace(template, start, end,
                        replacer.replace(placeholder));
            }
        }

        return template;
    }

    /**
     * The replacer required by the custom user will use the default
     * replacer if this method is not called.
     * @param replacerList the replacer you want to use
     */
    public void setReplacerList(List<Replacer> replacerList) {
        this.replacerList = replacerList;
    }

    // schema, the main support for coder to create code.
    private Schema schema;

    // Replacer to replace placeholders in schema string.
    private List<Replacer> replacerList = null;

    // beans support for coding.
    private Map<String, Object> beans;

    private void initBeans() {
        List<String> known = schema.getBeanIds();

        beans = new HashMap<>(known.size());
        Object bean;
        for (String id : known) {
            bean = BeanPool.getBean(id);

            if (bean == null) {
                throw new RuntimeException(new BeanNotFoundException(id));
            }

            beans.put(id, bean);
        }
    }

    public static void main(String[] args) throws Exception {
        BeanPool.addBean("person", "aa");
        BeanPool.addBean("personList", "ddd");
        Schema schema = new Schema();
        schema.setInput(new FileInputStream("/Users/lazycat/Desktop/code/work/codewing/coder/src/schema-demo.txt"));
        Coder coder = new Coder(schema);

        System.out.println(coder.makeCode());
    }
}