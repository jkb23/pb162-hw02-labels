package cz.muni.fi.pb162.hw02.error;

public class InvalidExpressionException extends RuntimeException {

    /**
     * Constructs exception for invalid exception
     *
     * @param expression invalid expression
     */
    public InvalidExpressionException(String expression) {
        super(expression);
    }

    /**
     * Constructs exception for invalid exception
     *
     * @param expression invalid expression
     * @param cause cause of this exception
     */
    public InvalidExpressionException(String expression, Throwable cause) {
        super(expression, cause);
    }
}
