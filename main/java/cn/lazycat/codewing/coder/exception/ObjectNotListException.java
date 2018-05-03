package cn.lazycat.codewing.coder.exception;

/**
 * The List label need a list bean in bean pool.
 * If the bean defined in list label is not a List object,
 * this exception will be thrown.
 */
public class ObjectNotListException extends RuntimeException {

    private String id;
    public ObjectNotListException(String id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "the bean whose id is " + id + " is not a list object";
    }
}
