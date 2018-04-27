package cn.lazycat.codewing.coder.replace.label;

import cn.lazycat.codewing.coder.Person;
import cn.lazycat.codewing.coder.tool.ReflectTool;
import cn.lazycat.codewing.coder.tool.StringTool;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ConditionLabelReplacer extends LabelReplacer {
    @Override
    protected String getLabelName() {
        return "if";
    }

    @Override
    protected String[] getParamNames() {
        return new String[] { "test" };
    }

    @Override
    protected String generateResultString(String content) {
        return null;
    }

    private static class ConditionParser {

        private String condition;
        private Map<String, Object> beans;

        ConditionParser(String condition, Map<String, Object> beans) {
            this.condition = condition;
            this.beans = beans;
        }

        boolean result() {
            // a = 'a' and (b='b' or c='c')
            // 1 and (0 or 1)
            // 1 * (0 + 1)
            parseEqual();
            condition = condition.replaceAll("and", "*");
            condition = condition.replaceAll("or", "+");
            return calBool();
        }

        private void parseEqual() {
            int i, j, mid;
            condition = " " + condition + " ";
            while (condition.contains("=")) {
                mid = condition.indexOf("=");
                i = preOrNextKey(condition, mid, -1);
                j = preOrNextKey(condition, mid, 1);
                String expr = condition.substring(i, j);
                boolean flag = parseExpr(expr);
                condition = StringTool.replace(condition, i, j, flag ? "1" : "0");
            }
        }

        private int preOrNextKey(String str, int cur, int step) {
            int i = cur + step;
            while (str.charAt(i) == ' ' || str.charAt(i) == '!') {
                i += step;
            }

            for (; i >= 0 && i < str.length(); i += step) {
                char ch = str.charAt(i);
                switch (ch) {
                    case '(':
                    case ')':
                    case ' ':
                        return step < 0 ? i + 1 : i;
                }
            }
            return i;
        }

        private boolean parseExpr(String expr) {
            boolean flag = true;
            if (expr.contains("!")) {
                flag = false;
                expr = expr.replaceAll("!", "");
            }
            String tmp[] = expr.split("=");
            String left = tmp[0].trim();
            String right = tmp[1].trim();

            String s1 = getValue(left);
            String s2 = getValue(right);

            s1 = s1 == null ? "null" : s1;
            s2 = s2 == null ? "null" : s2;

            if (flag) {
                return s1.equals(s2);
            }
            else {
                return !s1.equals(s2);
            }

        }

        private String getValue(String content) {
            if (content.contains("'")) {
                return  content.substring(1, content.length() - 1);
            }

            try {
                Long.parseLong(content);
                Double.parseDouble(content);
                Boolean.parseBoolean(content);

                return content;
            } catch (Exception ignore) {

            }

            if ("null".equalsIgnoreCase(content)) {
                return content;
            }

            if (content.contains(".")) {
                String tmp[] = content.split("\\.");
                String beanName = tmp[0];
                String fieldName = tmp[1];

                Object bean = beans.get(beanName);

                if (bean != null) {
                    return ReflectTool.getField(bean, fieldName);
                }
            }
            else {
                Object bean = beans.get(content);

                if (bean != null) {
                    return beans.get(content).toString();
                }
            }
            return null;
        }

        private boolean calBool() {

            System.out.println(condition);

            Stack<Boolean> num = new Stack<>();
            Stack<Character> sign = new Stack<>();

            for (int i = 0; i < condition.length(); ++i) {
                switch (condition.charAt(i)) {
                    case '+':
                    case '*':
                    case '(':
                        sign.push(condition.charAt(i));
                        break;
                    case '1':
                        num.push(true);
                        break;
                    case '0':
                        num.push(false);
                        break;
                    case ')':
                        char cur;
                        while ((cur = sign.pop()) != '(') {
                            boolean a = num.pop();
                            boolean b = num.pop();
                            boolean res = cal(a, b, cur);
                            num.push(res);
                        }
                        break;
                }  // switch
            } // for

            Character c;
            while (!sign.isEmpty()) {
                c = sign.pop();
                boolean a = num.pop();
                boolean b = num.pop();
                boolean res = cal(a, b, c);
                num.push(res);
            }

            return num.pop();
        }

        private boolean cal(boolean a, boolean b, char sign) {
            if (sign == '+') {
                return a || b;
            }
            else {
                return a && b;
            }
        }
    }
}
