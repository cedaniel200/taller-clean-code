package com.cedaniel200.cleancode;

import jdk.internal.jline.internal.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetExcel {

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
                HashMap<String, String> currentHash = new HashMap<String, String>();
                for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                    Cell currentCell = currentRow.getCell(j);
                    switch (currentCell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            currentHash.put(headerRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
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
            Log.error(e);
        }finally {
            close();
        }

        return myData;
    }

    private static void close(){
        try {
            fs.close();
        } catch (IOException e) {
            Log.error(e);
        }
    }
}
