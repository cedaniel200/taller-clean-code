package com.cedaniel200.cleancode.taller;

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

public class ExcelReader {

    private static final String TEST_RESOURCES_PATH = System.getProperty("user.dir") + "//src//test//resources//";
    private static final int HEADER_POSITION = 0;
    private static final String ERROR_MESSAGE = "Failed get rows by sheet name";
    private String fileName;
    private Row headerRow;

    private ExcelReader(String fileName) {
        this.fileName = fileName;
    }

    public static ExcelReader of(String fileName) {
        return new ExcelReader(fileName);
    }

    public List<HashMap<String, String>> getRowsBySheetName(String sheetName) throws ExcelException {
        try (FileInputStream excelFile = new FileInputStream(TEST_RESOURCES_PATH + fileName)) {
            return tryGetRowsBySheetName(sheetName, excelFile);
        } catch (IOException e) {
            throw new ExcelException(ERROR_MESSAGE, e);
        }
    }

    private List<HashMap<String, String>> tryGetRowsBySheetName(String sheetName, FileInputStream excelFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        headerRow = sheet.getRow(HEADER_POSITION);
        return getRows(sheet);
    }

    private List<HashMap<String, String>> getRows(XSSFSheet sheet) {
        List<HashMap<String, String>> rows = new ArrayList<>();
        sheet.iterator().forEachRemaining(
                row -> {
                    if (isNotHeader(row))
                        rows.add(getCellsByRow(row));
                });
        return rows;
    }

    private boolean isNotHeader(Row row) {
        return row.getRowNum() != HEADER_POSITION;
    }

    private HashMap<String, String> getCellsByRow(Row row) {
        HashMap<String, String> currentHash = new HashMap<>();
        row.cellIterator().forEachRemaining(
                cell -> {
                    String key = getKey(cell);
                    Optional<String> optionalValue = getValue(cell);
                    optionalValue.ifPresent(value -> currentHash.put(key, value));
                }
        );
        return currentHash;
    }

    private String getKey(Cell cell) {
        return headerRow.getCell(cell.getColumnIndex()).getStringCellValue();
    }

    private Optional<String> getValue(Cell currentCell) {
        switch (currentCell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return Optional.of(currentCell.getStringCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                return Optional.of(String.valueOf(currentCell.getNumericCellValue()).replace(".0", ""));
            default:
                return Optional.empty();
        }
    }
}