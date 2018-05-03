package cn.lazycat.codewing.coder.tool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class IOTool {

    public static void write(String outPath, String str) throws IOException {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outPath));
            writer.write(str);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }



}
