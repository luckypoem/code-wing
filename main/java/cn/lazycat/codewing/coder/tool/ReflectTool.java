package cn.lazycat.codewing.coder.tool;

import java.lang.reflect.Method;

public class ReflectTool {

    public static String getField(Object obj, String fieldName) {
        String getter = "get" + StringTool.upperFirstCase(fieldName);
        try {
            Method method = obj.getClass().getMethod(getter);
            return method.invoke(obj).toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
