package com.cedaniel200.cleancode;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetExcel {

    private static final Logger LOGGER = Logger.getLogger(GetExcel.class.getName());
    private static FileInputStream fs; // archivo de excel

    private GetExcel(){}

    // devuelve las filas
    public static List<HashMap<String,String>> data(String filepath, String sheetName) {
        List<HashMap<String, String>> myData = new ArrayList<>();
        try {
            fs = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//" + filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row currentRow = sheet.getRow(i);
                HashMap<String, String> currentHash = new HashMap<>();
                for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                    Cell currentCell = currentRow.getCell(j);
                    switch (currentCell.getCellType()) {
                        case STRING:
                            currentHash.put(headerRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
                            break;
                        case NUMERIC:
                            currentHash.put(headerRow.getCell(j).getStringCellValue(), String.valueOf(currentCell.getNumericCellValue()).replace(".0", ""));
                            break;
                            // faltan mas tipo por implementar
                        default:
                            break;
                    }
                }
                myData.add(currentHash);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "No se pudo leer el archivo " + filepath, e);
        }finally {
            close();
        }

        return myData;
    }

    private static void close(){
        try {
            fs.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se pudo cerrar el archivo", e);
        }
    }
}
