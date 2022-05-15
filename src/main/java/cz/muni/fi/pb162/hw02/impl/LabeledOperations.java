package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.LabelMatcher;
import cz.muni.fi.pb162.hw02.error.InvalidExpressionException;
import cz.muni.fi.pb162.hw02.LabelFilter;

/**
 * Factory for matcher production
 */
public final class LabeledOperations {

    private LabeledOperations() {
        // intentionally private
    }

    /**
     * Produces instance of your {@link LabelMatcher} implementation
     * which matches based on given expression.
     *
     * @param expression expression for which the matcher is created
     * @return expression-based label matcher
     * @throws InvalidExpressionException if expression is not valid
     */
    public static LabelMatcher expressionMatcher(String expression) {
        Validator validator = new Validator(expression);
        if (!validator.validate()){
            throw new InvalidExpressionException(expression);
        }

        return new LabelMatcherImpl(expression);
    }

    /**
     * Produces instance of your {@link LabelFilter} implementation
     * which filters based on given expression.
     *
     * @param expression expression for which the filter is created
     * @throws InvalidExpressionException if expression is not valid
     * @return expression-based label filter
     */
    public static LabelFilter expressionFilter(String expression) {
        Validator validator = new Validator(expression);
        if (!validator.validate()){
            throw new InvalidExpressionException(expression);
        }

        return new LabelFilterImpl(expression);
    }
}
