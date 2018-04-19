package cn.lazycat.codewing.coder;

import java.util.HashMap;
import java.util.Map;

/**
 * The beans pool, which holds all known bean objects.
 * The beans are actually key-value pairs, and the key is the id of the bean,
 * which is unique to each bean. Before using the schema, the user needs to
 * add the user's required beans in beanPool. The schema takes out the bean
 * objects that are defined in the schema through the bean label.
 */
public class BeanPool {

    private static Map<String, Object> beans = new HashMap<>();

    /**
     * add a bean object
     * @param id the object's id
     * @param bean the object
     */
    public static void addBean(String id, Object bean) {
        beans.put(id, bean);
    }

    /**
     * remove a bean object
     * @param id the bean's id you want to remove.
     */
    public static void removeBean(String id) {
        beans.remove(id);
    }

    /**
     * get a bean object
     * @param id the bean's id you want to get
     * @return the bean object
     */
    public static Object getBean(String id) {
        return beans.get(id);
    }

}
