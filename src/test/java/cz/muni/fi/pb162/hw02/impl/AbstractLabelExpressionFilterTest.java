package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.HasLabels;
import cz.muni.fi.pb162.hw02.LabelFilter;
import cz.muni.fi.pb162.hw02.impl.data.Article;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.assertj.core.util.TriFunction;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@ExtendWith(SoftAssertionsExtension.class)
public abstract class AbstractLabelExpressionFilterTest {

    protected static final List<HasLabels> articles = List.of(
            new Article("About Bees", Set.of("News", "animals", "bees", "Nature")),// 0
            new Article("Fish eating plants", Set.of("News", "Nature", "plants", "fish")),// 1
            new Article("Man's Best Friend", Set.of("News", "animals", "Society", "dogs")), // 2
            new Article("The Great Whites", Set.of("Story", "fish", "Nature", "animals")), // 3
            new Article("That's Gonna Sting!", Set.of("News", "animals", "bees", "Health")),// 4
            new Article("Moby-Dick Strikes Back", Set.of("Story", "fish", "Nature", "animals", "Society")), //5
            new Article("Blame the Pooh", Set.of("Story", "animals", "honey", "bees", "bears")) // 6
    );

    protected static final List<HasLabels> news = List.of(
            articles.get(0), // "About Bees"
            articles.get(1), // "Fish eating plants"
            articles.get(2), // "Man's Best Friend"
            articles.get(4)  // "That's Gonna Sting!"
    );

    protected static final List<HasLabels> fishArticles = List.of(
            articles.get(1), // "Fish eating plants"
            articles.get(3), // "The Great Whites"
            articles.get(5)  // "Moby-Dick Strikes Back"
    );

    protected static final List<HasLabels> beeArticles = List.of(
            articles.get(0), // "About Bees"
            articles.get(4), // That's Gonna Sting!"
            articles.get(6)  // "Blame the Pooh"
    );
    @InjectSoftAssertions
    SoftAssertions softly;

    protected List<HasLabels> articles(int... indices) {
        return Arrays.stream(indices).mapToObj(articles::get).collect(Collectors.toList());
    }

    protected LabelFilter filter(String expression) {
        return LabeledOperations.expressionFilter(expression);
    }

    protected void expectFiltered(
            BiFunction<LabelFilter, Iterable<HasLabels>, Collection<HasLabels>> method,
            Iterable<HasLabels> labeled,
            String expr,
            Collection<HasLabels> expected
    ) {
        var filter = filter(expr);
        var actual = method.apply(filter, labeled);

        softly.assertThat(actual)
                .describedAs("Expression '" + expr + "' should find articles")
                .hasSameElementsAs(expected);
    }

    protected void expectFiltered(
            TriFunction<LabelFilter, Iterable<HasLabels>, Iterable<HasLabels>, Collection<HasLabels>> method,
            Iterable<HasLabels> fst,
            Iterable<HasLabels> snd,
            String expr,
            Collection<HasLabels> expected
    ) {
        var filter = filter(expr);
        var actual = method.apply(filter, fst, snd);

        softly.assertThat(actual)
                .describedAs("Expression '" + expr + "' should find articles")
                .hasSameElementsAs(expected);
    }

}
