package com.cedaniel200.cleancode.solucion;

import com.cedaniel200.cleancode.solucion.exception.ExcelException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.cedaniel200.cleancode.solucion.utils.StreamUtils.toStream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class DefaultExcelFileProcessor implements ExcelFileProcessor {

    private static final String TEST_RESOURCES_PATH = System.getProperty("user.dir") + "//src//test//resources//";
    private static final String ERROR_MESSAGE = "Failed get rows by sheet name.";
    private static final int HEADER_POSITION = 0;
    private final String fileName;
    private CellParser cellParser;
    private Row headerRow;

    private DefaultExcelFileProcessor(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Map<String, String>> getRowsBySheetName(String sheetName) throws ExcelException {
        try (FileInputStream excelFile = new FileInputStream(TEST_RESOURCES_PATH + fileName)) {
            return tryGetRowsBySheetName(excelFile, sheetName);
        } catch (Exception e) {
            throw new ExcelException(ERROR_MESSAGE, e);
        }
    }

    private List<Map<String, String>> tryGetRowsBySheetName(FileInputStream excelFile, String sheetName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        return getRowsBySheet(sheet);
    }

    private List<Map<String, String>> getRowsBySheet(XSSFSheet sheet) {
        return toStream(sheet.spliterator())
                .filter(this::isNotHeader)
                .map(this::getCellsByRow)
                .collect(toList());
    }

    private boolean isNotHeader(Row row) {
        return row.getRowNum() != HEADER_POSITION;
    }

    private Map<String, String> getCellsByRow(Row row) {
        if (cellParser == null) throw new IllegalStateException("Debe settear el CellParser");
        return toStream(row.spliterator())
                .collect(toMap(this::getKey, cell -> cellParser.getValue(cell)));
    }

    private String getKey(Cell cell) {
        return getHeaderByCell(cell)
                .getCell(cell.getColumnIndex()).getStringCellValue();
    }

    private Row getHeaderByCell(Cell cell) {
        if (headerRow == null) headerRow = cell.getRow().getSheet().getRow(HEADER_POSITION);
        return headerRow;
    }

    public void setCellParser(CellParser cellParser) {
        this.cellParser = cellParser;
    }

    public static DefaultExcelFileProcessor createExcelFileProcessor(String fileName) {
        return new DefaultExcelFileProcessor(fileName);
    }
}
