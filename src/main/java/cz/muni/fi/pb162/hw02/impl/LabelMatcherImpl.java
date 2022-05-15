package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.HasLabels;
import cz.muni.fi.pb162.hw02.LabelMatcher;

/**
 * @author Matus Jakab
 */
public class LabelMatcherImpl implements LabelMatcher {
    private final String expression;

    /**
     *
     * @param expression String
     */
    public LabelMatcherImpl(String expression){
        this.expression = expression;
    }

    /**
     *
     * @param labeled item
     * @return true if item matches, false otherwise
     */
    public boolean matches(HasLabels labeled) {
        Evaluator evaluator = new Evaluator(expression);
        return evaluator.evaluate(labeled);
    }

    /**
     *
     * @param labeled iterable of labeled items
     * @return true if all items match, false otherwise
     */
    public boolean all(Iterable<HasLabels> labeled){
        for (HasLabels labels : labeled){
            if (!matches(labels)){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param labeled iterable of labeled items
     * @return true if any item matches, false otherwise
     */
    public boolean any(Iterable<HasLabels> labeled){
        for (HasLabels labels : labeled){
            if (matches(labels)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param labeled iterable of labeled items
     * @return true if no item matches, false otherwise
     */
    public boolean none(Iterable<HasLabels> labeled){
        for (HasLabels labels : labeled){
            if (matches(labels)){
                return false;
            }
        }
        return true;
    }
}
