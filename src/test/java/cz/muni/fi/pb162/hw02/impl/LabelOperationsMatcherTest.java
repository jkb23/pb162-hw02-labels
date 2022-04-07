package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.LabelMatcher;
import cz.muni.fi.pb162.hw02.error.InvalidExpressionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class LabelOperationsMatcherTest extends AbstractLabelOperationsTest {

    @Override
    protected void shouldThrowOnExpr(String expr) {
        var exception = catchException(
                () -> LabeledOperations.expressionMatcher(expr)
        );
        assertThat(exception).isInstanceOf(InvalidExpressionException.class);
        assertThat(exception).hasMessage(expr);
    }

    @Test
    void shouldProduceMatcherForValidExpression() {
        var matcher = LabeledOperations.expressionMatcher("foo");
        assertThat(matcher).isInstanceOf(LabelMatcher.class);
    }
}
