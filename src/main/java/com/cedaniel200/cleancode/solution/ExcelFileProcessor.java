package com.cedaniel200.cleancode.solution;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.cedaniel200.cleancode.solution.CellType.getStringValueOfCell;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class ExcelFileProcessor {

    private static final String TEST_RESOURCES_PATH = System.getProperty("user.dir") + "//src//test//resources//";
    private static final String ERROR_MESSAGE = "Failed get rows by sheet name.";
    private static final int HEADER_POSITION = 0;
    private static Row headerRow;
    private String excelFileName;

    private ExcelFileProcessor(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public static ExcelFileProcessor createExcelFileProcessor(String excelFileName) {
        return new ExcelFileProcessor(excelFileName);
    }

    public List<Map<String, String>> getRowsBySheetName(String sheetName) throws ExcelException {
        try (FileInputStream excelFile = new FileInputStream(TEST_RESOURCES_PATH + excelFileName)) {
            return tryGetRowsBySheetName(excelFile, sheetName);
        } catch (Exception e) {
            throw new ExcelException(ERROR_MESSAGE, e);
        }
    }

    private List<Map<String, String>> tryGetRowsBySheetName(FileInputStream excelFile, String sheetName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        headerRow = sheet.getRow(HEADER_POSITION);
        return getRowsBySheet(sheet);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, String>> getRowsBySheet(XSSFSheet sheet) {
        Stream<Row> rows = toStream(sheet.iterator());
        return rows.filter(this::isNotHeader).map(this::getCellsByRow).collect(toList());
    }

    private boolean isNotHeader(Row row) {
        return row.getRowNum() != HEADER_POSITION;
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getCellsByRow(Row row) {
        Stream<Cell> cells = toStream(row.cellIterator());
        return cells.collect(toMap(this::getKey, cell -> getStringValueOfCell(cell).orElse("")));
    }

    private String getKey(Cell cell) {
        return headerRow.getCell(cell.getColumnIndex()).getStringCellValue();
    }

    private <T> Stream toStream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }
}