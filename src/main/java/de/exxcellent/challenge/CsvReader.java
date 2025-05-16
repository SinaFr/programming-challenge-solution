package de.exxcellent.challenge;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvMalformedLineException;
import com.opencsv.exceptions.CsvValidationException;

/**
 * This class is responsible for reading CSV files.
 */
public class CsvReader implements DataReader {
    /**
     * Reads a CSV file and extracts the specified columns.
     *
     * @param file The data file to read.
     * @return A list of lists, where each inner list contains the values of the
     *         specified columns for a single row.
     * @throws IOException If an error occurs while reading the file.
     */
    @Override
    public List<List<String>> extractColumns(DataFile file) throws IOException {
        String path = file.getPath();
        String labelCol = file.getLabelCol();
        List<String> colsToCompare = file.getColsToCompare();

        List<List<String>> columnList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] headers = reader.readNext();

            if (headers == null) {
                throw new IOException("CSV file is empty: " + path);
            }

            /* Map column name to column index */
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i].trim(), i);
            }

            /* Check if required columns exist */
            if (!headerMap.containsKey(labelCol) ||
                    !headerMap.containsKey(colsToCompare.get(0)) ||
                    !headerMap.containsKey(colsToCompare.get(1))) {
                throw new IllegalArgumentException(
                        "Missing required fields in CSV header: " + Arrays.toString(headers));
            }

            /* Set indices for needed columns */
            int labelIdx = headerMap.get(labelCol);
            int col1Idx = headerMap.get(colsToCompare.get(0));
            int col2Idx = headerMap.get(colsToCompare.get(1));

            String[] row;
            while ((row = reader.readNext()) != null) {

                /*
                 * check if array 'row' has enough entries to index labeldIdx, col1Idx and
                 * col2Idx
                 */
                if (row.length > Math.max(labelIdx, Math.max(col1Idx, col2Idx))) {
                    /* add row with entries from relevant columns and remove whitespaces */
                    columnList.add(Arrays.asList(
                            row[labelIdx].trim(),
                            row[col1Idx].trim(),
                            row[col2Idx].trim()));
                } else {
                    System.err.printf("Skipping row due to insufficient columns: %s%n", Arrays.toString(row));
                }
            }
        } catch (CsvValidationException | CsvMalformedLineException e) {
            throw new IOException("Failed to parse CSV file: " + path, e);
        }

        return columnList;
    }
}
