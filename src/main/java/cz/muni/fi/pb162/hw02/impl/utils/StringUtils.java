package cz.muni.fi.pb162.hw02.impl.utils;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Provides useful string operations
 */
public final class StringUtils {

    private StringUtils() {
        // intentionally private
    }

    /**
     * Counts subsequent occurrence of given character
     *
     * @param input input string
     * @param c character to count
     * @param beginIndex the beginning index, inclusive.
     * @return number of subsequent occurrences
     */
    public static int countSubsequentChars(String input, char c, int beginIndex) {
        return (int) input.chars()
                .skip(beginIndex)
                .takeWhile(x -> c == (char) x)
                .count();
    }

    /**
     * Same as {@link String#indexOf(String)} for each item in items
     *
     * @param input input string
     * @param items substrings to search for
     * @return map with item as key and occurrence index as value
     */
    public static Map<String, Integer> indexesOf(String input, Set<String> items) {
        return items.stream().collect(toMap(identity(), input::indexOf));
    }

    /**
     * Determines which item occurs first in given string
     *
     * @param input input string
     * @param items substrings to search for
     * @return optional containing whichever items occurs first in input or empty optional
     */
    public static Optional<String> firstOf(String input, Set<String> items) {
        return indexesOf(input, items)
                .entrySet()
                .stream()
                .filter( e -> e.getValue() > -1)
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }


    /**
     * Prefix based on predicate applied to each character
     *
     * @param input input string
     * @param predicate character predicate
     * @return prefix where character match given predicate
     */
    public static String takeWhile(String input, Predicate<Character> predicate) {
        return input
                .chars()
                .takeWhile(c -> predicate.test((char) c))
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }
}
