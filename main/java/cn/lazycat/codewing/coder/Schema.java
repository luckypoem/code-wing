package cn.lazycat.codewing.coder;

import cn.lazycat.codewing.coder.exception.BadSchemaPreDefinitionException;
import cn.lazycat.codewing.coder.exception.InconsistentVersionException;
import cn.lazycat.codewing.coder.tool.StringTool;

import java.io.*;
import java.util.LinkedList;
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

    /**
     * Set the input stream of the schema, the schema object will read the text from
     * this input stream, and the text will be treated as a schema by coder. The Schema
     * also parses the prefix definition of the text to get information about beans and versions.
     * @param input use this stream to get schema text
     */
    public void setInput(InputStream input)
            throws IOException {
        parseSchemaInfo(input);
    }

    /**
     * Get the contents of the Schema, which does not include the prefix definition,
     * the bean definition, but includes unhandled placeholders.
     * @return contents of the schema.
     */
    public String getSchemaString() {
        return content;

    }

    /**
     * Get all the ids of beans that are resolved according to beans definition.
     * @return all beans' ids.
     */
    public List<String> getBeanIds() {
        return beanIds;
    }

    // get version, beans id, content from input stream.
    //
    private void parseSchemaInfo(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        if (StringTool.isEmpty(line) || !line.matches(versionRegex)) {
            throw new RuntimeException(new BadSchemaPreDefinitionException());
        }

        double currentVersion;
        try {
            String vstr = line.substring(
                    line.indexOf("version='") + 9,
                    line.indexOf("'/>")
            );
            currentVersion = Double.parseDouble(vstr);
        } catch (NumberFormatException e) {
            throw new RuntimeException(new BadSchemaPreDefinitionException());
        }

        if (currentVersion != VERSION) {
            throw new RuntimeException(new InconsistentVersionException());
        }

        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            if (line.matches(beansRegex)) {
                String beanId = line.substring(
                        line.indexOf("use='") + 5,
                        line.indexOf("'/>")
                );
                beanIds.add(beanId);
            }
            else {
                sb.append(line).append('\n');
            }
        }

        // delete last \n
        StringTool.deleteLastChar(sb);

        content = sb.toString();
    }

    // current available version.
    private static final double VERSION = 1.0;

    // All known ids' names from prefix definition in schema file.
    private List<String> beanIds = new LinkedList<>();

    // the text that not include prefix definition.
    private String content;

    private static final String versionRegex = "^<wing:schema version='\\d+\\.\\d+'/>$";
    private static final String beansRegex = "^<wing:bean use='\\w+'/>$";
}
