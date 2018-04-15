package cn.lazycat.codewing.coder;

/**
 * <p>
 *     A Java Bean describes some of the object that Coder needs
 *     when coding according to a schema. The beans need to be declared
 *     at the beginning of the schema to refer to these beans in the following schema.
 * </p>
 * <p>
 *     The bean contains an id number that uniquely identifies it. And the specific
 *     object that this bean holds. Subclasses of Coder are responsible for producing
 *     bean objects for coders to facilitate the production of code based on the schema.
 * </p>
 * <p>
 *     Replacer also requires a bean to replace some placeholders.
 * </p>
 */
public class Bean {

    private String id;

    private Object obj;

    /**
     * The id uniquely identifies a bean.
     * If the bean accepted by Coder has id repetition, undefined behavior will result!
     * @param id the bean's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get bean's object.
     * @return bean's object
     */
    public Object getObj() {
        return obj;
    }

    /**
     * set bean's object.
     * @param obj bean's new object.
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * get bean's id
     * @return bean's id
     */
    public String getId() {
        return id;
    }
}
