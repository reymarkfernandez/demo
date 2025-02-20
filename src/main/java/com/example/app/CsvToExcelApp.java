package com.example.app;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CsvToExcelApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CSV to Excel Exporter");

        // Create a button to trigger the CSV to Excel conversion
        Button btn = new Button("Convert CSV to Excel");
        btn.setOnAction(event -> handleConvertCsvToExcel(primaryStage));

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleConvertCsvToExcel(Stage primaryStage) {
        // Show file chooser to select the CSV file
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File csvFile = fileChooser.showOpenDialog(primaryStage);

        if (csvFile != null) {
            try {
                // Read the CSV file into a list of rows
                CsvReader csvReader = new CsvReader();
                List<String[]> rows = csvReader.readCsv(csvFile);

                // Create an Excel file with the same name as the CSV file
                File excelFile = new File(csvFile.getParentFile(), csvFile.getName().replace(".csv", ".xlsx"));
                convertToExcel(rows, excelFile);

                // Show a success message when the conversion is complete
                showAlert(AlertType.INFORMATION, "Success", "File exported to Excel successfully.");
            } catch (IOException e) {
                // Show an error message if something goes wrong
                showAlert(AlertType.ERROR, "Error", "Error processing the file: " + e.getMessage());
            }
        }
    }

    // Method to read the CSV file into a List of String arrays (rows)
    private List<String[]> readCsv(File csvFile) throws IOException {
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

    // Method to convert the CSV data to an Excel file
    private void convertToExcel(List<String[]> rows, File excelFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(); // Create a new Excel workbook
        Sheet sheet = workbook.createSheet("Sheet1"); // Create a new sheet in the workbook

        // Loop through each row of the CSV data
        int rowIndex = 0;
        for (String[] rowData : rows) {
            Row row = sheet.createRow(rowIndex++); // Create a new row in the Excel sheet
            for (int cellIndex = 0; cellIndex < rowData.length; cellIndex++) {
                Cell cell = row.createCell(cellIndex); // Create a new cell in the row
                cell.setCellValue(rowData[cellIndex]); // Set the cell's value from the CSV
            }
        }

        // Save the Excel file to disk
        try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
            workbook.write(fileOut); // Write the workbook data to the file
        } finally {
            workbook.close(); // Close the workbook
        }
    }

    // Helper method to show alerts in the JavaFX UI
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait(); // Display the alert and wait for the user to close it
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}