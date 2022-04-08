package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.LabelMatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

@EnabledIfSystemProperty(named = "impl.bonus", matches = "true")
public class ParenthesesLabelExpressionMatcherTest extends AbstractLabelExpressionMatcherTest {

    @Test
    public void shouldMatch() {
        expectArticleToMatch("!(Nature & pandas) | plants");
        expectArticleToMatch("!(Nature & (panda | fish)) & !plants");
        expectArticleToMatch("!(Nature & bees) | animals");
    }

    @Test
    public void shouldMatchAll() {
        expectArticlesToMatch(LabelMatcher::all, "!plants | (Nature & fish)");
        expectArticlesToMatch(LabelMatcher::all, "(News | Story) & (!plants | fish)");
        expectArticlesToMatch(LabelMatcher::any, "(animals & fish) | (News & (fish | !plants)) | Society");
    }

    @Test
    public void shouldMatchNone() {
        expectArticlesToMatch(LabelMatcher::none, "!(animals | Nature)");
    }

    @Test
    public void shouldMatchAny() {
        shouldMatchAll();
        expectArticlesToMatch(LabelMatcher::any, "bees | (Society & !fish)");
    }
}
