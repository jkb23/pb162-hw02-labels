package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.HasLabels;
import cz.muni.fi.pb162.hw02.LabelMatcher;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.function.BiFunction;

import static cz.muni.fi.pb162.hw02.impl.data.Articles.BEES;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.DOGS;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.FERRETS;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.PLANTS;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.SHARKS;

@ExtendWith(SoftAssertionsExtension.class)
public abstract class AbstractLabelExpressionMatcherTest {

    protected static final HasLabels article = FERRETS;

    protected static final List<HasLabels> articles = List.of(BEES, PLANTS, DOGS, SHARKS);

    @InjectSoftAssertions
    SoftAssertions softly;

    protected LabelMatcher matcher(String expression) {
        return LabeledOperations.expressionMatcher(expression);
    }

    protected void expectArticleToMatch(String expr) {
        expectMatch(LabelMatcher::matches, article, true, expr);
    }

    protected void expectArticleNotToMatch(String expr) {
        expectMatch(LabelMatcher::matches, article, false, expr);
    }

    protected void expectArticlesToMatch(BiFunction<LabelMatcher, Iterable<HasLabels>, Boolean> method, String expr) {
        expectArticlesToMatch(method, true, expr);
    }

    protected void expectArticlesToMatch(BiFunction<LabelMatcher, Iterable<HasLabels>, Boolean> method, boolean match, String expr) {
        expectMatch(method, articles, match, expr);
    }

    /**
     * Asserts that expression matches given object by given method
     *
     * @param method matching method
     * @param match should this be a match?
     * @param expr expression
     */
    private <T> void expectMatch(
            BiFunction<LabelMatcher, T, Boolean> method,
            T what,
            boolean match,
            String expr
    ) {
        var matcher = matcher(expr);

        softly.assertThat(method.apply(matcher, what))
                .describedAs("Expression '" + expr + "' should" + (match ? "" : " not") + " match " + what)
                .isEqualTo(match);
    }

}
