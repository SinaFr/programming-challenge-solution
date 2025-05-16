package de.exxcellent.challenge;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CsvReaderTest {

        private static final String BASE_PATH = "src/test/resources/de/exxcellent/challenge/";

        @Test
        public void extractColumns_whenValidFile_thenReturnsValidColumns() throws Exception {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherValid.csv",
                                "Day",
                                Arrays.asList("MxT", "MnT"));

                List<List<String>> columns = CsvReader.extractColumns(weatherFile);
                List<List<String>> expectedColumns = Arrays.asList(
                                Arrays.asList("1", "88", "59"),
                                Arrays.asList("2", "79", "63"),
                                Arrays.asList("3", "77", "55"),
                                Arrays.asList("4", "77", "59"),
                                Arrays.asList("5", "90", "66"),
                                Arrays.asList("6", "81", "61"),
                                Arrays.asList("7", "73", "57"),
                                Arrays.asList("8", "75", "54"),
                                Arrays.asList("9", "86", "32"),
                                Arrays.asList("10", "84", "64"),
                                Arrays.asList("11", "91", "59"),
                                Arrays.asList("12", "88", "73"),
                                Arrays.asList("13", "70", "59"),
                                Arrays.asList("14", "61", "59"),
                                Arrays.asList("15", "64", "55"),
                                Arrays.asList("16", "79", "59"),
                                Arrays.asList("17", "81", "57"),
                                Arrays.asList("18", "82", "52"),
                                Arrays.asList("19", "81", "61"),
                                Arrays.asList("20", "84", "57"),
                                Arrays.asList("21", "86", "59"),
                                Arrays.asList("22", "90", "64"),
                                Arrays.asList("23", "90", "68"),
                                Arrays.asList("24", "90", "77"),
                                Arrays.asList("25", "90", "72"),
                                Arrays.asList("26", "97", "64"),
                                Arrays.asList("27", "91", "72"),
                                Arrays.asList("28", "84", "68"),
                                Arrays.asList("29", "88", "66"),
                                Arrays.asList("30", "90", "45"));

                assertNotNull(columns);
                assertFalse(columns.isEmpty());
                assertEquals(3, columns.get(0).size());
                assertEquals(expectedColumns, columns);
        }

        @Test
        public void extractColumns_whenEmptyFile_thenPropagatesIOException() {
                DataFile weatherFile = new DataFile(BASE_PATH + "/emptyFile.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                IOException exception = assertThrows(IOException.class, () -> {
                        CsvReader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("CSV file is empty"),
                                "Expected exception message to indicate empty CSV file.");
        }

        @Test
        public void extractColumns_whenMissingLabelCol_thenPropagatesIllegalArgumentException() {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherMissingLabelCol.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                        CsvReader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("Missing required fields in CSV header"),
                                "Expected exception message to indicate the missing label column.");
        }

        @Test
        public void extractColumns_whenMissingColMxT_thenPropagatesIllegalArgumentException() {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherMissingColMxT.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                        CsvReader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("Missing required fields in CSV header"),
                                "Expected exception message to indicate the missing column MxT.");
        }

        @Test
        public void extractColumns_whenMissingColMnT_thenPropagatesIllegalArgumentException() {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherMissingColMnT.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                        CsvReader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("Missing required fields in CSV header"),
                                "Expected exception message to indicate the missing column MnT.");
        }

        @Test
        public void extractColumns_whenMissingValues_thenSkipsRows() throws Exception {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherMissingValues.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                List<List<String>> columns = CsvReader.extractColumns(weatherFile);
                List<List<String>> expectedColumns = Arrays.asList(
                                Arrays.asList("2", "79", "63"),
                                Arrays.asList("4", "77", "59"));

                assertEquals(2, columns.size());
                assertEquals(expectedColumns, columns);
        }

        @Test
        public void extractColumns_whenInvalidCsv_thenPropagatesIOException() {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherInvalidCsv.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                IOException exception = assertThrows(IOException.class, () -> {
                        CsvReader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("Failed to parse CSV file"),
                                "Expected exception message to indicate an invalid csv file.");
        }

}
