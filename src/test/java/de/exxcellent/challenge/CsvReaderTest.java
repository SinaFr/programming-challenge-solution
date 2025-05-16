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
        DataReader reader = new CsvReader();

        @Test
        public void extractColumns_whenValidWeatherFile_thenReturnsValidColumns() throws Exception {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherValid.csv",
                                "Day",
                                Arrays.asList("MxT", "MnT"));

                List<List<String>> columns = reader.extractColumns(weatherFile);
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
        public void extractColumns_whenValidFootballFile_thenReturnsValidColumns() throws Exception {
                DataFile weatherFile = new DataFile(BASE_PATH + "/footballValid.csv",
                                "Team",
                                Arrays.asList("Goals", "Goals Allowed"));

                List<List<String>> columns = reader.extractColumns(weatherFile);
                List<List<String>> expectedColumns = Arrays.asList(
                                Arrays.asList("Arsenal", "79", "36"),
                                Arrays.asList("Liverpool", "67", "30"),
                                Arrays.asList("Manchester United", "87", "45"),
                                Arrays.asList("Newcastle", "74", "52"),
                                Arrays.asList("Leeds", "53", "37"),
                                Arrays.asList("Chelsea", "66", "38"),
                                Arrays.asList("West_Ham", "48", "57"),
                                Arrays.asList("Aston_Villa", "46", "47"),
                                Arrays.asList("Tottenham", "49", "53"),
                                Arrays.asList("Blackburn", "55", "51"),
                                Arrays.asList("Southampton", "46", "54"),
                                Arrays.asList("Middlesbrough", "35", "47"),
                                Arrays.asList("Fulham", "36", "44"),
                                Arrays.asList("Charlton", "38", "49"),
                                Arrays.asList("Everton", "45", "57"),
                                Arrays.asList("Bolton", "44", "62"),
                                Arrays.asList("Sunderland", "29", "51"),
                                Arrays.asList("Ipswich", "41", "64"),
                                Arrays.asList("Derby", "33", "63"),
                                Arrays.asList("Leicester", "30", "64"));

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
                        reader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("CSV file is empty"),
                                "Expected exception message to indicate empty CSV file.");
        }

        @Test
        public void extractColumns_whenMissingLabelCol_thenPropagatesIllegalArgumentException() {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherMissingLabelCol.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                        reader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("Missing required fields in CSV header"),
                                "Expected exception message to indicate the missing label column.");
        }

        @Test
        public void extractColumns_whenMissingColMxT_thenPropagatesIllegalArgumentException() {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherMissingColMxT.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                        reader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("Missing required fields in CSV header"),
                                "Expected exception message to indicate the missing column MxT.");
        }

        @Test
        public void extractColumns_whenMissingColMnT_thenPropagatesIllegalArgumentException() {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherMissingColMnT.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                        reader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("Missing required fields in CSV header"),
                                "Expected exception message to indicate the missing column MnT.");
        }

        @Test
        public void extractColumns_whenMissingValues_thenSkipsRows() throws Exception {
                DataFile weatherFile = new DataFile(BASE_PATH + "/weatherMissingValues.csv", "Day",
                                Arrays.asList("MxT", "MnT"));

                List<List<String>> columns = reader.extractColumns(weatherFile);
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
                        reader.extractColumns(weatherFile);
                });

                assertTrue(exception.getMessage().contains("Failed to parse CSV file"),
                                "Expected exception message to indicate an invalid csv file.");
        }

}
