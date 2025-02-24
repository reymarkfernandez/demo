package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import com.example.app.ObservableCsvExporter;
import com.example.app.CsvToExcelApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RootController {

    private ObservableList<String> observableList = FXCollections.observableArrayList("Alice", "Bob", "Charlie");

    @FXML
    private Button exportButton;

    @FXML
    private void handleExportToCsv() {
        Stage stage = (Stage) exportButton.getScene().getWindow();
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

    @FXML
    private void handleCsvToExcel() {
        Stage stage = (Stage) exportButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File csvFile = fileChooser.showOpenDialog(stage);

        if (csvFile != null) {
            try {
                CsvToExcelApp csvToExcelApp = new CsvToExcelApp();
                List<String[]> rows = csvToExcelApp.readCsv(csvFile);
                File excelFile = new File(csvFile.getParentFile(), csvFile.getName().replace(".csv", ".xlsx"));
                csvToExcelApp.convertToExcel(rows, excelFile);
                System.out.println("CSV file converted to Excel at: " + excelFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleExcelExporter() {
        // Implement the logic for Excel Exporter button
        System.out.println("Excel Exporter button clicked");
    }
}
