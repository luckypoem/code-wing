package cn.lazycat.codewing.coder.exception;

public class BadConditionExprException extends RuntimeException {

    private String condition;

    public BadConditionExprException(String condition) {
        this.condition = condition;
    }

    @Override
    public String getMessage() {
        return "Bad test expression: " + condition;
    }
}
