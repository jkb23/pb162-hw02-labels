package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.LabelFilter;
import org.junit.jupiter.api.Test;

public class LabelExpressionFilterTest extends AbstractLabelExpressionFilterTest {

    @Test
    public void shouldFilterMatching() {
        expectFiltered(LabelFilter::matching, articles, "fish", articles(1, 3, 5));
        expectFiltered(LabelFilter::matching, articles, "bees", articles(0, 4, 6));
        expectFiltered(LabelFilter::matching, articles, "bees & News", articles(0, 4));
        expectFiltered(LabelFilter::matching, articles, "fish | bees", articles(0, 1, 3, 4, 5, 6));
        expectFiltered(LabelFilter::matching, articles, "fish | bees & !Story", articles(0, 1, 4));
    }

    @Test
    public void shouldFilterNotMatching() {
        expectFiltered(LabelFilter::notMatching, articles, "fish", articles(0, 2, 4, 6));
        expectFiltered(LabelFilter::notMatching, articles, "bees", articles(1, 2, 3, 5));
        expectFiltered(LabelFilter::notMatching, articles, "bees & News", articles(1, 2, 3, 5, 6));
        expectFiltered(LabelFilter::notMatching, articles, "fish | bees", articles(2));
        expectFiltered(LabelFilter::notMatching, articles, "fish | bees & !Story", articles(2, 3, 5, 6));
    }

    @Test
    public void shouldFilterUnion() {
        expectFiltered(LabelFilter::joined, news, fishArticles, "fish", articles(1, 3, 5));
        expectFiltered(LabelFilter::joined, news, fishArticles, "fish & News", articles(1));
        expectFiltered(LabelFilter::joined, news, fishArticles, "fish | dogs", articles(1, 2, 3, 5));
    }

    @Test
    public void shouldFilterDistinct() {
        expectFiltered(LabelFilter::distinct, news, fishArticles, "fish & news | !bees | dogs", articles(2, 3, 5));
        expectFiltered(LabelFilter::distinct, fishArticles, articles, "fish & !bees | dogs", articles(2));
        expectFiltered(LabelFilter::distinct, fishArticles, beeArticles, "!fish & !bees", articles());
        expectFiltered(LabelFilter::intersection, articles, beeArticles, "Story", articles(6));
        expectFiltered(LabelFilter::intersection, articles, fishArticles, "Story", articles(3, 5));
        expectFiltered(LabelFilter::intersection, beeArticles, fishArticles, "Story", articles());
    }

    @Test
    public void shouldFilterIntersection() {
        expectFiltered(LabelFilter::intersection, news, fishArticles, "fish & news | !bees | dogs", articles(1));
        expectFiltered(LabelFilter::intersection, fishArticles, beeArticles, "!fish & !bees", articles());
        expectFiltered(LabelFilter::intersection, articles, beeArticles, "Story", articles(6));
        expectFiltered(LabelFilter::intersection, articles, fishArticles, "Story", articles(3, 5));
        expectFiltered(LabelFilter::intersection, beeArticles, fishArticles, "Story", articles());
    }
}
