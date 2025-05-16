package de.exxcellent.challenge;

import java.io.IOException;
import java.util.List;

/**
 * Interface for reading data files of different formats.
 */
public interface DataReader {
    /**
     * Extracts the specified columns from the given data file.
     *
     * @param file The data file to read.
     * @return A list of lists, where each inner list contains the values of the
     *         specified columns for a single row.
     * @throws IOException If an error occurs while reading the file.
     */

    List<List<String>> extractColumns(DataFile file) throws IOException;
}
