package de.exxcellent.challenge;

import java.io.IOException;

/**
 * Interface for analyzing data files.
 */
public interface DataAnalyzer {

    /**
     * Analyzes the given data file and returns the label of a specific row, e.g.
     * the row with the smallest spread between two columns.
     * 
     * @param file The data file to analyze.
     * @return A label.
     * @throws IOException If an error occurs while reading the file.
     */
    String findLabelOfRelevantRow(DataFile file) throws IOException;
}
