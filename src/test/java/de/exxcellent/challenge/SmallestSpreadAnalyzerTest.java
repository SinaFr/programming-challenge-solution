package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SmallestSpreadAnalyzerTest {

    private static final String BASE_PATH = "src/test/resources/de/exxcellent/challenge/";
    DataReader reader = new CsvReader();
    SpreadCalculator calculator = new SmallestSpreadCalculator();
    DataAnalyzer analyzer = new SmallestSpreadAnalyzer(reader, calculator);

    @Test
    public void findLabelOfRelevantRow_whenValidWeatherFile_thenReturnsValidLabel() throws Exception {
        DataFile weatherFile = new DataFile(BASE_PATH + "/weatherValid.csv", "Day",
                Arrays.asList("MxT", "MnT"));

        String label = analyzer.findLabelOfRelevantRow(weatherFile);
        assertEquals("14", label);
    }

    @Test
    public void findLabelOfRelevantRow_whenValidFootballFile_thenReturnsValidLabel() throws Exception {
        DataFile weatherFile = new DataFile(BASE_PATH + "/footballValid.csv", "Team",
                Arrays.asList("Goals", "Goals Allowed"));

        String label = analyzer.findLabelOfRelevantRow(weatherFile);
        assertEquals("Aston_Villa", label);
    }

    @Test
    public void findLabelOfRelevantRow_whenEmptyFile_thenPropagatesIOException() throws IOException {
        /*
         * Create file as placeholder for mocking, path and columns are not relevant
         * here
         */
        DataFile file = new DataFile(BASE_PATH + "/weatherValid.csv", "Day", List.of("MxT", "MnT"));

        /*
         * Mocking the DataReader and SpreadCalculator to simulate an IOException in
         * function extractColumns
         */
        DataReader reader = org.mockito.Mockito.mock(DataReader.class);
        SpreadCalculator calculator = org.mockito.Mockito.mock(SpreadCalculator.class);
        DataAnalyzer analyzer = new SmallestSpreadAnalyzer(reader, calculator);

        org.mockito.Mockito.when(reader.extractColumns(file)).thenThrow(new IOException("Simulates IO-Exception"));

        /* IOException should be propagated to findLabelOfRelevantRow */
        assertThrows(IOException.class,
                () -> analyzer.findLabelOfRelevantRow(file),
                "Expected IOException to be propagated from extractColumns");
    }

    @Test
    public void findLabelOfRelevantRow_whenOnlyHeaderRow_thenReturnsNull() throws IOException {
        DataFile weatherFile = new DataFile(BASE_PATH + "/weatherOnlyHeader.csv", "Day",
                Arrays.asList("MxT", "MnT"));

        String label = analyzer.findLabelOfRelevantRow(weatherFile);
        assertEquals(null, label);
    }
}
