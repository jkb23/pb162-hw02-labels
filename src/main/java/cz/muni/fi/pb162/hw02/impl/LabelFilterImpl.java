package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.HasLabels;
import cz.muni.fi.pb162.hw02.LabelFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Matus Jakab
 */
public class LabelFilterImpl implements LabelFilter {
    private final String expression;

    /**
     *
     * @param expression String
     */
    public LabelFilterImpl(String expression){
        this.expression = expression;
    }

    /**
     *
     * @param labeled iterable of labeled items
     * @return matched items
     */
    public Collection<HasLabels> matching(Iterable<HasLabels> labeled){
        Set<HasLabels> ret = new HashSet<>();
        LabelMatcherImpl matcher = new LabelMatcherImpl(expression);
        for (HasLabels labels : labeled){
            if (matcher.matches(labels)){
                ret.add(labels);
            }
        }
        return ret;
    }

    /**
     *
     * @param labeled iterable of labeled items
     * @return non-matching items
     */
    public Collection<HasLabels> notMatching(Iterable<HasLabels> labeled){
        Set<HasLabels> ret = new HashSet<>();
        LabelMatcherImpl matcher = new LabelMatcherImpl(expression);
        for (HasLabels labels : labeled){
            if (!matcher.matches(labels)){
                ret.add(labels);
            }
        }
        return ret;
    }

    /**
     *
     * @param fst first iterable of labeled items
     * @param snd second iterable of labeled items
     *
     * @return matching items from either iterable
     */
    public Collection<HasLabels> joined(Iterable<HasLabels> fst, Iterable<HasLabels> snd){
        Set<HasLabels> ret = new HashSet<>();
        ret.addAll(matching(fst));
        ret.addAll(matching(snd));
        return ret;
    }

    /**
     *
     * @param fst first iterable of labeled items
     * @param snd second iterable of labeled items
     *
     * @return matching items from both
     */
    public Collection<HasLabels> distinct(Iterable<HasLabels> fst, Iterable<HasLabels> snd){
        Collection<HasLabels> f = matching(fst);
        Collection<HasLabels> s = matching(snd);
        Collection<HasLabels> copy = List.copyOf(f);

        f.removeIf(s::contains);
        s.removeIf(copy::contains);
        f.addAll(s);

        return f;
    }

    /**
     *
     * @param fst first iterable of labeled items
     * @param snd second iterable of labeled items
     *
     * @return matching items from both
     */
    public Collection<HasLabels> intersection(Iterable<HasLabels> fst, Iterable<HasLabels> snd){
        Collection<HasLabels> f = matching(fst);
        Collection<HasLabels> s = matching(snd);
        Collection<HasLabels> copy = List.copyOf(f);

        f.removeIf(n -> (!s.contains(n)));
        s.removeIf(n -> (!copy.contains(n)));
        f.addAll(s);

        return f;
    }
}
