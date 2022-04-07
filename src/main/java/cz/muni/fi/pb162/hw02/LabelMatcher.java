package cz.muni.fi.pb162.hw02;

public interface LabelMatcher {

    /**
     * Checks if item matches
     *
     * @param labeled item
     * @return true if item matches, false otherwise
     */
    boolean matches(HasLabels labeled);

    /**
     * Check if all items match
     *
     * @param labeled iterable of labeled items
     * @return true if all items match, false otherwise
     */
    boolean all(Iterable<HasLabels> labeled);

    /**
     * Check if any item matches
     *
     * @param labeled iterable of labeled items
     * @return true if any item matches, false otherwise
     */
    boolean any(Iterable<HasLabels> labeled);

    /**
     * Check if no item matches
     *
     * @param labeled iterable of labeled items
     * @return true if no item matches, false otherwise
     */
    boolean none(Iterable<HasLabels> labeled);
}
