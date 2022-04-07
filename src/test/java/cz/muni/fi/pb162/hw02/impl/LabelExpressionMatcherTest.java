package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.LabelMatcher;
import org.junit.jupiter.api.Test;

public class LabelExpressionMatcherTest extends AbstractLabelExpressionMatcherTest {

    @Test
    public void shouldMatch() {
        expectArticleToMatch("animals");
        expectArticleToMatch("!!animals");
        expectArticleToMatch("!pandas");
        expectArticleToMatch("bees | animals");
        expectArticleToMatch("Nature & animals");
        expectArticleToMatch("Nature & pandas | animals");
        expectArticleToMatch("Nature | pandas & animals");
        expectArticleToMatch("Nature|pandas&animals");
        expectArticleToMatch("Nature & pandas | !missing");
    }

    @Test
    public void shouldNotMatch() {
        expectArticleNotToMatch("pandas");
        expectArticleNotToMatch("!!pandas");
        expectArticleNotToMatch("!animals");
        expectArticleNotToMatch("pandas | bees");
        expectArticleNotToMatch("Nature & bees");
        expectArticleNotToMatch("Nature & pandas | bees");
        expectArticleNotToMatch("bees | pandas & animals");
        expectArticleNotToMatch("bees|pandas&animals");
        expectArticleNotToMatch("Nature & pandas | missing");
    }

    @Test
    public void shouldMatchAll() {
        expectArticlesToMatch(LabelMatcher::all, true, "!plants | fish & News");
        expectArticlesToMatch(LabelMatcher::all, true, "News & !plants | fish");
        expectArticlesToMatch(LabelMatcher::all, true, "animals | plants | society | Nature");
        expectArticlesToMatch(LabelMatcher::any, true, "animals & fish | News & fish | !plants | Society");
    }

    @Test
    public void shouldNotMatchAll() {
        expectArticlesToMatch(LabelMatcher::all, false, "bees | !fish");
        expectArticlesToMatch(LabelMatcher::all, false, "bees & Nature");
        expectArticlesToMatch(LabelMatcher::all, false, "bees | Society & !fish");
        expectArticlesToMatch(LabelMatcher::all, false, "!animals & !Nature");
        expectArticlesToMatch(LabelMatcher::all, false, "News & bikes");
    }

    @Test
    public void shouldMatchAny() {
        expectArticlesToMatch(LabelMatcher::any, true, "!plants | fish & News");
        expectArticlesToMatch(LabelMatcher::any, true, "News & !plants | fish");
        expectArticlesToMatch(LabelMatcher::any, true, "animals | plants | society | Nature");
        expectArticlesToMatch(LabelMatcher::any, true, "animals & fish | News & fish | !plants | Society");
        expectArticlesToMatch(LabelMatcher::any, true, "bees | !fish");
        expectArticlesToMatch(LabelMatcher::any, true, "News");
        expectArticlesToMatch(LabelMatcher::any, true, "bees & Nature");
        expectArticlesToMatch(LabelMatcher::any, true, "bees | Society & !fish");
    }

    @Test
    public void shouldNotMatchAny() {
        expectArticlesToMatch(LabelMatcher::any, false, "!animals & !Nature");
        expectArticlesToMatch(LabelMatcher::any, false, "News & bikes");
    }

    @Test
    public void shouldMatchNone() {
        expectArticlesToMatch(LabelMatcher::none, true, "!animals & !Nature");
        expectArticlesToMatch(LabelMatcher::none, true, "News & bikes");
    }

    @Test
    public void shouldNotMatchNone() {
        expectArticlesToMatch(LabelMatcher::none, false, "!plants | fish & News");
        expectArticlesToMatch(LabelMatcher::none, false, "News & !plants | fish");
        expectArticlesToMatch(LabelMatcher::none, false, "animals | plants | society | Nature");
        expectArticlesToMatch(LabelMatcher::none, false, "animals & fish | News & fish | !plants | Society");
        expectArticlesToMatch(LabelMatcher::none, false, "bees | !fish");
        expectArticlesToMatch(LabelMatcher::none, false, "News");
        expectArticlesToMatch(LabelMatcher::none, false, "bees & Nature");
        expectArticlesToMatch(LabelMatcher::none, false, "bees | Society & !fish");
    }
}
