package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SmallestSpreadCalculatorTest {

    SpreadCalculator calculator = new SmallestSpreadCalculator();

    @Test
    public void findRowWithRelevantSpread_whenValidColumns_thenReturnsValidIdx() {

        List<List<String>> columns = Arrays.asList(
                Arrays.asList("88", "59"),
                Arrays.asList("79", "63"),
                Arrays.asList("77", "55"),
                Arrays.asList("77", "59"),
                Arrays.asList("90", "66"),
                Arrays.asList("81", "61"),
                Arrays.asList("73", "57"),
                Arrays.asList("75", "54"),
                Arrays.asList("86", "32"),
                Arrays.asList("84", "64"),
                Arrays.asList("91", "59"),
                Arrays.asList("88", "73"),
                Arrays.asList("70", "59"),
                Arrays.asList("61", "59"),
                Arrays.asList("64", "55"),
                Arrays.asList("79", "59"),
                Arrays.asList("81", "57"),
                Arrays.asList("82", "52"),
                Arrays.asList("81", "61"),
                Arrays.asList("84", "57"),
                Arrays.asList("86", "59"),
                Arrays.asList("90", "64"),
                Arrays.asList("90", "68"),
                Arrays.asList("90", "77"),
                Arrays.asList("90", "72"),
                Arrays.asList("97", "64"),
                Arrays.asList("91", "72"),
                Arrays.asList("84", "68"),
                Arrays.asList("88", "66"),
                Arrays.asList("90", "45"));

        Integer rowIdx = calculator.findRowWithRelevantSpread(columns);
        assertEquals(13, rowIdx);
    }

    @Test
    public void findRowWithRelevantSpread_whenOnlyOneRow_thenReturnsIdxZero() {

        List<List<String>> columns = Arrays.asList(
                Arrays.asList("88", "89"));

        Integer rowIdx = calculator.findRowWithRelevantSpread(columns);
        assertEquals(0, rowIdx);
    }

    @Test
    public void findRowWithRelevantSpread_whenMultipleMinSpreads_thenReturnsFirstOccurrence() {
        List<List<String>> columns = Arrays.asList(
                Arrays.asList("80", "70"),
                Arrays.asList("81", "71"),
                Arrays.asList("90", "75"));

        Integer rowIdx = calculator.findRowWithRelevantSpread(columns);
        assertEquals(0, rowIdx);
    }

    @Test
    public void findRowWithRelevantSpread_whenEmptyList_thenReturnsIdxMinusOne() {

        List<List<String>> columns = Arrays.asList();

        Integer rowIdx = calculator.findRowWithRelevantSpread(columns);
        assertEquals(-1, rowIdx);
    }

    @Test
    public void findRowWithRelevantSpread_whenRowSizeTooSmall_thenSkipRow() {

        List<List<String>> columns = Arrays.asList(
                Arrays.asList("88", "86"),
                Arrays.asList("79"),
                Arrays.asList("77", "55"),
                Arrays.asList("77", "76"));

        Integer rowIdx = calculator.findRowWithRelevantSpread(columns);
        assertEquals(3, rowIdx);
    }

    @Test
    public void findRowWithRelevantSpread_whenInvalidValues_thenSkipRows() {

        List<List<String>> columns = Arrays.asList(
                Arrays.asList(".", "86"),
                Arrays.asList("79", "a"),
                Arrays.asList("77", "55"),
                Arrays.asList("77", ""));

        Integer rowIdx = calculator.findRowWithRelevantSpread(columns);
        assertEquals(2, rowIdx);
    }
}
