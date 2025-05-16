package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class DataAnalyzerTest {

    private static final String BASE_PATH = "src/test/resources/de/exxcellent/challenge/";

    @Test
    public void findLabelWithSmallestSpread_whenValidFile_thenReturnsValidLabel() throws Exception {
        DataFile weatherFile = new DataFile(BASE_PATH + "/weatherValid.csv", "Day",
                Arrays.asList("MxT", "MnT"));

        String label = DataAnalyzer.findLabelWithSmallestSpread(weatherFile);
        assertEquals("14", label);
    }

    @Test
    public void findLabelWithSmallestSpread_whenEmptyFile_thenPropagatesIOException() throws IOException {
        DataFile file = new DataFile("fake/path.csv", "Day", List.of("MxT", "MnT"));

        try (MockedStatic<DataReader> mocked = mockStatic(DataReader.class)) {
            mocked.when(() -> DataReader.extractColumns(file))
                    .thenThrow(new IOException("Simulates IO-Exception"));

            assertThrows(IOException.class,
                    () -> DataAnalyzer.findLabelWithSmallestSpread(file),
                    "Expected IOException to be propagated from extractColumns");
        }
    }

    @Test
    public void findLabelWithSmallestSpread_whenOnlyHeaderRow_thenReturnsNull() throws IOException {
        DataFile weatherFile = new DataFile(BASE_PATH + "/weatherOnlyHeader.csv", "Day",
                Arrays.asList("MxT", "MnT"));

        String label = DataAnalyzer.findLabelWithSmallestSpread(weatherFile);
        assertEquals(null, label);
    }
}
