package cn.lazycat.codewing.coder.replace;

import cn.lazycat.codewing.coder.replace.label.ListLabelReplacer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     Replacer is used to replace some special placeholders in a string with
 *     another string  based on a rule. Replacer provides the ability to detect
 *     if there is a placeholder in a string. With the getFeature() method,
 *     you can get the characteristics of this placeholder, which is actually
 *     a regex expression. Coder will retrieve the entire Schema. When it finds this
 *     regex, it will call getStart and getEnd of this interface to get the
 *     offset of the entire placeholder in the string. Then take out the full
 *     placeholder.
 * </p>
 * <p>
 *     The most important method is replace . This method accepts the placeholders
 *     that Coder retrieves using the getStart and getEnd methods for parsing and returns
 *     the contents of the placeholders. Coder replaces the original placeholders with
 *     this content and proceeds to the next testing.
 * </p>
 * <p>
 *     The replacer needs to use many bean objects in most cases. Therefore, beans are
 *     saved in Replacer by default, indicating the list of bean objects required by Replacer.
 *     The needBeans() method indicates whether this Replacer needs beans. If it is true,
 *     Coder will pass the list of bean objects parsed by the schema to replacer. Subclasses
 *     can choose to have it return false so that beans are null.
 * </p>
 *
 * @author lazycat
 */
public abstract class Replacer {
    /**
     * Return the regex expression that describes a placeholder.
     * Use this regex expression to determine if there is such placeholder in the schema.
     * @return regex that can find if there has placeholder in schema.
     */
    public abstract String getFeature();

    /**
     * get the start of the placeholder in schema.
     * Note that this method will be called only placeholder is detected by feature.
     * @param schemaStr where to get the placeholder
     * @return the start location of the placeholder
     */
    public abstract int getStart(String schemaStr);

    /**
     * get the end of the placeholder in schema
     */
    public abstract int getEnd(String schemaStr);

    /**
     * Get the replace content by using placeholder and beans that given by coder.
     * coder will actually replace the schema string from getStart to getEnd
     * to the return string of this method.
     * @param placeholder placeholder, use getStart and getEnd to intercept it.
     * @return the replacement for placeholder.
     */
    public abstract String replace(String placeholder);

    /**
     * This method is to tell coder whether this replacer need beans or not.
     * if it return true, the beans will be equals to the coder's beans.
     * if it return false, the beans will be null.
     * @return whether current replacer need beans or not.
     */
    public boolean needBeans() {
        return true;
    }

    public void setBeans(Map<String, Object> beans) {
        this.beans = beans;
    }

    // beans support for replacer.
    // Coder will add bean if needBeans() return true.
    //
    protected Map<String, Object> beans = null;
}
