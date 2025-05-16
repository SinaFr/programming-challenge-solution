package de.exxcellent.challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for analyting data and finding the smallest spread
 * between two columns.
 */
public class SmallestSpreadAnalyzer implements DataAnalyzer {
    private final DataReader reader;
    private final SpreadCalculator calculator;

    public SmallestSpreadAnalyzer(DataReader reader, SpreadCalculator calculator) {
        this.reader = reader;
        this.calculator = calculator;
    }

    /**
     * Finds the label with the smallest spread between two columns in a data file.
     *
     * @param file The data file to analyze.
     * @return The label of the row with the smallest spread.
     * @throws IOException If an error occurs while reading the file.
     */
    @Override
    public String findLabelOfRelevantRow(DataFile file) throws IOException {
        List<List<String>> columnList = reader.extractColumns(file);
        List<List<String>> columnsToCompare = new ArrayList<>();
        for (List<String> row : columnList) {
            columnsToCompare.add(row.subList(1, 3));
        }
        int rowIdx = calculator.findRowWithRelevantSpread(columnsToCompare);
        if (rowIdx == -1) {
            System.out.println("File is empty or contains no valid data.");
            return null;
        }
        return columnList.get(rowIdx).get(0);
    }
}
