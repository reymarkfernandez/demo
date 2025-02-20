package com.example.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    // Method to read the CSV file into a List of String arrays (rows)
    public List<String[]> readCsv(File csvFile) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(csvFile.toPath())) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split each line by commas and add it to the rows list
                String[] columns = line.split(",");
                rows.add(columns);
            }
        }
        return rows;
    }
}
