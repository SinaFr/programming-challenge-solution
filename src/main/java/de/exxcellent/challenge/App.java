package de.exxcellent.challenge;

import java.util.Arrays;

public final class App {

    private static final String BASE_PATH = "src/main/resources/de/exxcellent/challenge";

    /**
     * The main method to run the application.
     *
     * @param args Command line arguments.
     * @throws Exception If an error occurs during execution.
     */
    public static void main(String... args) throws Exception {
        DataReader reader = new CsvReader();
        SpreadCalculator calculator = new SmallestSpreadCalculator();
        DataAnalyzer analyzer = new SmallestSpreadAnalyzer(reader, calculator);

        DataFile weatherFile = new DataFile(BASE_PATH + "/weather.csv", "Day",
                Arrays.asList("MxT", "MnT"));
        String day = analyzer.findLabelOfRelevantRow(weatherFile);
        System.out.println("Day with smallest temperature spread: " + day);

        DataFile footballFile = new DataFile(BASE_PATH + "/football.csv", "Team",
                Arrays.asList("Goals", "Goals Allowed"));
        String team = analyzer.findLabelOfRelevantRow(footballFile);
        System.out.println("Team with smallest goal spread: " + team);
    }
}
