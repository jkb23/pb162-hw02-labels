package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.HasLabels;
import cz.muni.fi.pb162.hw02.LabelMatcher;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

@ExtendWith(SoftAssertionsExtension.class)
public abstract class AbstractLabelExpressionMatcherTest {

    protected static final HasLabels article = new Article("Do Ferrets bite?", Set.of("animals", "Nature"));

    protected static final List<HasLabels> articles = List.of(
            new Article("About Bees", Set.of("News", "animals", "bees", "Nature")),
            new Article("Fish Eating Plants", Set.of("News", "Nature", "plants", "fish")),
            new Article("Man's Best Friend", Set.of("News", "animals", "Society", "dogs")),
            new Article("The Great Whites", Set.of("News", "fish", "Nature", "animals"))
    );

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
