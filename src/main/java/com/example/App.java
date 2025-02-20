package com.example;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.app.CsvToExcelApp;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the UI components
        VBox vbox = new VBox();
        Button btn = new Button("Export CSV to Excel");

        // Set the button action
        btn.setOnAction(e -> {
            // Open file chooser to select CSV file
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                try {
                    // Step 1: Read CSV file
                    CSVReader csvReader = new CSVReader(new FileReader(selectedFile));
                    List<String[]> csvData = csvReader.readAll();
                    csvReader.close();

                    // Step 2: Export to Excel
                    String excelFilePath = selectedFile.getParent() + File.separator + "exported.xlsx";
                    CsvToExcelApp exporter = new CsvToExcelApp();
                    exporter.convertToExcel(csvData, excelFilePath); 

                    // Step 3: Show success alert
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Export Success");
                    alert.setHeaderText("CSV Exported to Excel");
                    alert.setContentText("The CSV file has been successfully exported to: " + excelFilePath);
                    alert.showAndWait();
                } catch (IOException ex) {
                    // Handle any exceptions
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Export Failed");
                    alert.setContentText("An error occurred while exporting: " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        });

        // Add the button to the layout
        vbox.getChildren().add(btn);

        // Set the scene and stage
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CSV to Excel Exporter");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
