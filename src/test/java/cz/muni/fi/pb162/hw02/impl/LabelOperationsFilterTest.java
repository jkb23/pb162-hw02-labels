package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.LabelFilter;
import cz.muni.fi.pb162.hw02.error.InvalidExpressionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class LabelOperationsFilterTest extends AbstractLabelOperationsTest {

    @Override
    protected void shouldThrowOnExpr(String expr) {
        var exception = catchException(
                () -> LabeledOperations.expressionMatcher(expr)
        );
        assertThat(exception).isInstanceOf(InvalidExpressionException.class);
        assertThat(exception).hasMessage(expr);
    }

    @Test
    void shouldProduceFilterForValidExpression() {
        var filter = LabeledOperations.expressionFilter("foo");
        assertThat(filter).isInstanceOf(LabelFilter.class);
    }
}
