package de.exxcellent.challenge;

import java.util.Arrays;

public final class App {

    /**
     * The main method to run the application.
     *
     * @param args Command line arguments.
     * @throws Exception If an error occurs during execution.
     */
    public static void main(String... args) throws Exception {

        DataFile weatherFile = new DataFile("src/main/resources/de/exxcellent/challenge/weather.csv", "Day",
                Arrays.asList("MxT", "MnT"));
        String day = DataAnalyzer.findLabelWithSmallestSpread(weatherFile);
        System.out.println("Day with smallest temperature spread: " + day);
    }
}
