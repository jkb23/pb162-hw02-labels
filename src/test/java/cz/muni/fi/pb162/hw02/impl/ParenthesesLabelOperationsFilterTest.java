package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.error.InvalidExpressionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

@EnabledIfSystemProperty(named = "impl.bonus", matches = "true")
public class ParenthesesLabelOperationsFilterTest extends AbstractLabelOperationsTest {

    @Override
    protected void shouldThrowOnExpr(String expr) {
        var exception = catchException(
                () -> LabeledOperations.expressionFilter(expr)
        );
        assertThat(exception).isInstanceOf(InvalidExpressionException.class);
        assertThat(exception).hasMessage(expr);
    }

    @Test
    public void shouldThrowOnInvalidExprWithEmptyParentheses() {
        shouldThrowOnExpr("()");
    }

    @Test
    public void shouldThrowOnInvalidExprWithEmptyOpenParentheses() {
        shouldThrowOnExpr("(label");
    }

    @Test
    public void shouldThrowOnInvalidExprWithMismatchedParentheses() {
        shouldThrowOnExpr("(label | (foo)");
    }
}
