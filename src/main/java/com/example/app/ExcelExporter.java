package com.example.app;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    // Method to convert the CSV data to an Excel file
    public void convertToExcel(List<String[]> rows, File excelFile) throws IOException {
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
}
