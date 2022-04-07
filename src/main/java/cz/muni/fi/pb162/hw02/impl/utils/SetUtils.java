package cz.muni.fi.pb162.hw02.impl.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Set utility operations
 */
public final class SetUtils {
    private SetUtils() {
        // intentionally private
    }

    /**
     * Crates a union of two sets
     * @param fst first set
     * @param snd second set
     * @param <T> type parameter of all sets
     * @return a set containing elements of both
     */
    public static <T> Set<T> union(Set<T> fst, Set<T> snd) {
        var union = new HashSet<T>();
        union.addAll(fst);
        union.addAll(snd);

        return union;
    }
}
