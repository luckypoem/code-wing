package cn.lazycat.codewing.coder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * <p>
 *     The Schema is the basis for coder to generate code.
 *     It comes from a file. Coder must receive a schema to work properly.
 *     The setInput of the Schema is used to specify which file to read
 *     the Schema from. Note that this must be a text file.
 * </p>
 * <p>
 *     Each Schema has a prefix definition. The first line specifies the version
 *     of the current schema. In this object, the current version of the Schema
 *     in CodeWing is saved. Schema after reading the file content to do the first
 *     thing is check whether version and the current version, if does not conform
 *     to, throw SchemaVersionInconsistentException.
 * </p>
 * <p>
 *     Then check the bean definition behind the version declaration, which generates a
 *     list of bean ids based on these definitions, which are mainly used by coder.
 * </p>
 * <p>
 *     Prefix definitions and bean declarations are not available in the resulting code.
 *     A string that does not contain these declarations can be obtained through the Schema
 *     getSchemaString method. That's all the strings in the back. Coder generates code
 *     based on these strings.
 * </p>
 */
public class Schema {

    // current available version.
    private static final double VERSION = 1.0;

    // reader to read schema file.
    private BufferedReader reader;

    private double currentVersion;

    private List<String> beanIds;

    public void setInput(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input));
    }
}
