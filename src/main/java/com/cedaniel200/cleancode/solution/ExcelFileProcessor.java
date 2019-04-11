package com.cedaniel200.cleancode.solution;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.cedaniel200.cleancode.solution.CellType.getStringValueOfCell;

public class ExcelFileProcessor {

    private static final String TEST_RESOURCES_PATH = System.getProperty("user.dir") + "//src//test//resources//";
    private static final String ERROR_MESSAGE = "Failed get rows by sheet name.";
    private static final int HEADER_POSITION = 0;
    private static Row headerRow;
    private String excelFileName;

    private ExcelFileProcessor(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public static ExcelFileProcessor createExcelFileProcessor(String excelFileName){
        return new ExcelFileProcessor(excelFileName);
    }

    public List<HashMap<String, String>> getRowsBySheetName(String sheetName) throws ExcelException {
        try (FileInputStream excelFile = new FileInputStream(TEST_RESOURCES_PATH + excelFileName)) {
            return tryGetRowsBySheetName(excelFile, sheetName);
        } catch (Exception e) {
            throw new ExcelException(ERROR_MESSAGE, e);
        }
    }

    private static List<HashMap<String, String>> tryGetRowsBySheetName(FileInputStream excelFile, String sheetName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        headerRow = sheet.getRow(HEADER_POSITION);
        return getRowsBySheet(sheet);
    }

    private static List<HashMap<String, String>> getRowsBySheet(XSSFSheet sheet) {
        List<HashMap<String, String>> rows = new ArrayList<>();
        sheet.iterator().forEachRemaining(
                row -> {
                    if (isNotHeader(row))
                        rows.add(getCellsByRow(row));
                }
        );
        return rows;
    }

    private static boolean isNotHeader(Row row) {
        return row.getRowNum() != HEADER_POSITION;
    }

    private static HashMap<String, String> getCellsByRow(Row row) {
        HashMap<String, String> cells = new HashMap<>();
        row.cellIterator().forEachRemaining(
                cell -> {
                    String key = getKey(cell);
                    Optional<String> optionalValue = getStringValueOfCell(cell);
                    optionalValue.ifPresent(value -> cells.put(key, value));
                });
        return cells;
    }

    private static String getKey(Cell cell) {
        return headerRow.getCell(cell.getColumnIndex()).getStringCellValue();
    }

}