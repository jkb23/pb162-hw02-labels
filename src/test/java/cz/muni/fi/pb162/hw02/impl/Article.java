package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.HasLabels;

import java.util.Objects;
import java.util.Set;

public class Article implements HasLabels {
    private final Set<String> labels;
    private final String title;

    public Article(String title, Set<String> labels) {
        Objects.requireNonNull(title, "Title should not be null");
        Objects.requireNonNull(labels, "Labels should not be null");

        this.title = title;
        this.labels = Set.copyOf(labels);
    }

    @Override
    public Set<String> getLabels() {
        return labels;
    }

    @Override
    public String toString() {
        return "Article{title='" + title + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Article article = (Article) o;
        return labels.equals(article.labels) && title.equals(article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labels, title);
    }
}
