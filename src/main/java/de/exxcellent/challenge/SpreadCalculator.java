package de.exxcellent.challenge;

import java.util.List;

/**
 * Interface for calculating the spread between columns in a dataset.
 */
public interface SpreadCalculator {
    /**
     * Calculates the difference between the given columns and returns the
     * index of the relevant row, e.g. the row with the smallest difference.
     *
     * @param columns The list of columns to compare.
     * @return The index of the row with a specific difference.
     */
    Integer findRowWithRelevantSpread(List<List<String>> columns);
}
