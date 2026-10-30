package com.cedaniel200.cleancode.solution;

import com.cedaniel200.cleancode.solution.exception.ExcelException;
import com.cedaniel200.cleancode.solution.parse.CellParser;
import com.cedaniel200.cleancode.solution.parse.NumericCellValueParser;
import com.cedaniel200.cleancode.solution.parse.StringCellValueParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.cedaniel200.cleancode.solution.ExcelFileProcessor.createExcelFileProcessor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExcelTest {

    private CellParser cellParser;
    private ExcelFileProcessor excelFileProcessor;

    @BeforeEach
    public void setup() {
        cellParser = new CellParser();
        cellParser.registerCellValueParser(new NumericCellValueParser());
        cellParser.registerCellValueParser(new StringCellValueParser());

        excelFileProcessor = excelFileProcessorFor("Clientes.xlsx");
    }

    @Test
    public void testMustSucceedWhenDataMethodReturnsTwoRows() throws ExcelException {
        List<Map<String, String>> data = excelFileProcessor.getRowsBySheetName("Hoja1");

        assertThat(data.size(), is(equalTo(2)));
    }

    @Test
    public void testMustSucceedWhenDataMethodReturnsFourCells() throws ExcelException {
        List<Map<String, String>> data = excelFileProcessor.getRowsBySheetName("Hoja1");
        Map<String, String> cells = data.get(0);

        assertThat(cells.size(), is(equalTo(4)));
    }

    @Test
    public void testMustSucceedWhenDataMethodReturnsCorrectRowInformation() throws ExcelException {
        List<Map<String, String>> data = excelFileProcessor.getRowsBySheetName("Hoja1");

        assertThat(data.toString(),
                is(equalTo("[{clientType=N, idType=1, apellido=5, idNumber=1234}, {clientType=J, idType=3, apellido=perez, idNumber=5678}]")));
    }

    @Test
    public void testMustFailWhenSheetDoesNotExist() {
        assertThrows(ExcelException.class, () -> excelFileProcessor.getRowsBySheetName("HojaQueNoExiste"));
    }

    @Test
    public void testMustFailWhenFileDoesNotExist() {
        ExcelFileProcessor missingFileProcessor = excelFileProcessorFor("NoExiste.xlsx");

        assertThrows(ExcelException.class, () -> missingFileProcessor.getRowsBySheetName("Hoja1"));
    }

    @Test
    public void testMustOmitTheCellWhenTheRowHasAnEmptyCell() throws ExcelException {
        ExcelFileProcessor processorWithEmptyCell = excelFileProcessorFor("ClientesConCeldaVacia.xlsx");

        Map<String, String> cells = processorWithEmptyCell.getRowsBySheetName("Hoja1").get(0);

        // Comportamiento actual: para POI la celda vacía no existe, así que la clave no llega al mapa.
        assertThat(cells.size(), is(equalTo(3)));
        assertFalse(cells.containsKey("idType"));
    }

    private ExcelFileProcessor excelFileProcessorFor(String fileName) {
        ExcelFileProcessor processor = createExcelFileProcessor(fileName);
        processor.setCellParser(cellParser);
        return processor;
    }
}
