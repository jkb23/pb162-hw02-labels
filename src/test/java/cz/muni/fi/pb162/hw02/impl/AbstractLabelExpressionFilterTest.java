package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.HasLabels;
import cz.muni.fi.pb162.hw02.LabelFilter;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.assertj.core.util.TriFunction;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static cz.muni.fi.pb162.hw02.impl.data.Articles.BEES;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.DOGS;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.MOBY;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.PLANTS;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.POOH;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.SHARKS;
import static cz.muni.fi.pb162.hw02.impl.data.Articles.STING;

@ExtendWith(SoftAssertionsExtension.class)
public abstract class AbstractLabelExpressionFilterTest {

    protected static final List<HasLabels> articles = List.of(BEES, PLANTS, DOGS, SHARKS, STING, MOBY, POOH);
    protected static final List<HasLabels> news = List.of(BEES, PLANTS, DOGS, STING);
    protected static final List<HasLabels> fishArticles = List.of(PLANTS, SHARKS, MOBY);
    protected static final List<HasLabels> beeArticles = List.of(BEES, STING, POOH);

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
