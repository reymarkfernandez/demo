package com.example.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SampleDataGenerator {

    public static void generateSampleCsv(String filePath) throws IOException {
        List<String[]> data = Arrays.asList(
                new String[] { "Name", "Age", "City" },
                new String[] { "Alice", "30", "New York" },
                new String[] { "Bob", "25", "Los Angeles" },
                new String[] { "Charlie", "35", "Chicago" });

        try (FileWriter csvWriter = new FileWriter(filePath)) {
            for (String[] rowData : data) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
        }
    }

    public static void main(String[] args) {
        try {
            String filePath = "sample_data.csv";
            generateSampleCsv(filePath);
            System.out.println("Sample CSV file generated at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
