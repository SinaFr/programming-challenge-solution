package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DataAnalyzerTest {

    private static final String BASE_PATH = "src/test/resources/de/exxcellent/challenge/";
    DataReader reader = new CsvReader();
    SpreadCalculator calculator = new SmallestSpreadCalculator();
    DataAnalyzer analyzer = new DataAnalyzer(reader, calculator);

    @Test
    public void findLabelWithSmallestSpread_whenValidFile_thenReturnsValidLabel() throws Exception {
        DataFile weatherFile = new DataFile(BASE_PATH + "/weatherValid.csv", "Day",
                Arrays.asList("MxT", "MnT"));

        String label = analyzer.findLabelWithSmallestSpread(weatherFile);
        assertEquals("14", label);
    }

    @Test
    public void findLabelWithSmallestSpread_whenEmptyFile_thenPropagatesIOException() throws IOException {
        DataFile file = new DataFile("fake/path.csv", "Day", List.of("MxT", "MnT"));
        DataReader reader = org.mockito.Mockito.mock(DataReader.class);
        SpreadCalculator calculator = org.mockito.Mockito.mock(SpreadCalculator.class);
        DataAnalyzer analyzer = new DataAnalyzer(reader, calculator);

        org.mockito.Mockito.when(reader.extractColumns(file)).thenThrow(new IOException("Simulates IO-Exception"));

        assertThrows(IOException.class,
                () -> analyzer.findLabelWithSmallestSpread(file),
                "Expected IOException to be propagated from extractColumns");
    }

    @Test
    public void findLabelWithSmallestSpread_whenOnlyHeaderRow_thenReturnsNull() throws IOException {
        DataFile weatherFile = new DataFile(BASE_PATH + "/weatherOnlyHeader.csv", "Day",
                Arrays.asList("MxT", "MnT"));

        String label = analyzer.findLabelWithSmallestSpread(weatherFile);
        assertEquals(null, label);
    }
}
