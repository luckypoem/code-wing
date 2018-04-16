package cn.lazycat.codewing.coder.exception;

/**
 * This exception is thrown when the prefix definition format error
 * in the schema. The first line of the schema file must be the
 * version declaration, followed by the beans declaration. If there
 * is no version declaration in a schema, this exception must be thrown.
 * The beans declaration may not be written (but it is usually written,
 * otherwise codewing will not be able to perform its power), and this
 * exception will be thrown if there is a problem with the declared format.
 */
public class BadSchemaPreDefinitionException extends Exception {
}
