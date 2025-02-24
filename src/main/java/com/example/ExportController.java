package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.app.ObservableCsvExporter;

import java.io.File;
import java.io.IOException;

public class ExportController {

    private ObservableList<String> observableList = FXCollections.observableArrayList("Alice", "Bob", "Charlie");

    @FXML
    private void handleExportToCsv() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                ObservableCsvExporter.exportToCsv(observableList, file.getAbsolutePath());
                System.out.println("Observable list exported to CSV at: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
