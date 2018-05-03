package cn.lazycat.codewing.coder.exception;

/**
 * The bean is declared in the schema, but this bean is not found in beanPool.
 */
public class BeanNotFoundException extends RuntimeException {

    private String beanId;

    public BeanNotFoundException(String beanId) {
        this.beanId = beanId;
    }

    @Override
    public String getMessage() {
        return "can not find bean whose id is " + beanId;
    }
}
