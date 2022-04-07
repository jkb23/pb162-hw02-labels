package cz.muni.fi.pb162.hw02;

import java.util.Collection;

public interface LabelFilter {

    /**
     * Filters matching items
     *
     * @param labeled iterable of labeled items
     * @return matched items
     */
    Collection<HasLabels> matching(Iterable<HasLabels> labeled);

    /**
     * Filters non-matching items
     *
     * @param labeled iterable of labeled items
     * @return non-matching items
     */
    Collection<HasLabels> notMatching(Iterable<HasLabels> labeled);


    /**
     * All items matching in either iterable
     *
     * @param fst first iterable of labeled items
     * @param snd second iterable of labeled items
     *
     * @return matching items from either iterable
     */
    Collection<HasLabels> union(Iterable<HasLabels> fst, Iterable<HasLabels> snd);


    /**
     * All items matching in exactly one iterable
     *
     * @param fst first iterable of labeled items
     * @param snd second iterable of labeled items
     *
     * @return matching items from both
     */
    Collection<HasLabels> distinct(Iterable<HasLabels> fst, Iterable<HasLabels> snd);

    /**
     * All items matching in both iterables
     *
     * @param fst first iterable of labeled items
     * @param snd second iterable of labeled items
     *
     * @return matching items from both
     */
    Collection<HasLabels> intersection(Iterable<HasLabels> fst, Iterable<HasLabels> snd);
}
