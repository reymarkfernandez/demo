package com.example.app;

import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;

public class ObservableCsvExporter {

    public static <T> void exportToCsv(ObservableList<T> observableList, String filePath) throws IOException {
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            for (T item : observableList) {
                csvWriter.append(item.toString());
                csvWriter.append("\n");
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        // ObservableList<String> list = FXCollections.observableArrayList("Alice",
        // "Bob", "Charlie");
        // try {
        // exportToCsv(list, "observable_data.csv");
        // System.out.println("Observable list exported to CSV at:
        // observable_data.csv");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }
}
