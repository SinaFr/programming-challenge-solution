package de.exxcellent.challenge;

import java.util.List;

/**
 * This class is responsible for calculating the smallest spread between values.
 */
public class SpreadCalculator {
    /**
     * Calculates the row with the smallest difference between the two given columns
     * and returns the index of that row. If there are multiple minimum spreads, the
     * first one is returned.
     *
     * @param columns The list of columns to compare. Only the first two columns are
     *                considered.
     * @return The index of the row with the smallest difference.
     */

    public static Integer getIdxWithSmallestDiff(List<List<String>> columns) {
        int smallestDiff = Integer.MAX_VALUE;
        int rowIdx = -1;
        for (int i = 0; i < columns.size(); i++) {
            List<String> row = columns.get(i);

            if (row.size() < 2) {
                System.err.println("Skipping row " + i + " due to too few columns: " + row);
                continue;
            }

            try {
                /* Clean data to only compare integer values */
                int a = Integer.parseInt(row.get(0).trim().replace("*", ""));
                int b = Integer.parseInt(row.get(1).trim().replace("*", ""));
                int diff = Math.abs(a - b);

                if (diff < smallestDiff) {
                    smallestDiff = diff;
                    rowIdx = i;
                }
            } catch (NumberFormatException e) {
                System.err.println("Skipping row " + i + " due to invalid number format: " + row);
            }
        }
        return rowIdx;
    }
}
