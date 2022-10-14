package com.cedaniel200.cleancode.solucion;

import com.cedaniel200.cleancode.solucion.exception.ExcelException;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.cedaniel200.cleancode.solucion.DefaultExcelFileProcessor.createExcelFileProcessor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class DefaultExcelFileProcessorTest {

    private ExcelFileProcessor excelFileProcessor;

    @Before
    public void setup(){
        CellParser cellParser = new CellParser();
        cellParser.registerCellValueParser(Cell.CELL_TYPE_STRING, Cell::getStringCellValue);
        cellParser.registerCellValueParser(Cell.CELL_TYPE_NUMERIC,
                cell -> String.valueOf(cell.getNumericCellValue()).replace(".0", ""));

        excelFileProcessor = createExcelFileProcessor("Clientes.xlsx");
        ((DefaultExcelFileProcessor) excelFileProcessor).setCellParser(cellParser);
    }

    @Test
    public void testMustSucceedWhenDataMethodReturnsTwoRows() throws ExcelException {
        List<Map<String,String>> data = excelFileProcessor.getRowsBySheetName("Hoja1");

        assertThat(data.size(), is(equalTo(2)));
    }

    @Test
    public void testMustSucceedWhenDataMethodReturnsFourCells() throws ExcelException {
        List<Map<String,String>> data = excelFileProcessor.getRowsBySheetName("Hoja1");
        Map<String,String> cells = data.get(0);

        assertThat(cells.size(), is(equalTo(4)));
    }

    @Test
    public void testMustSucceedWhenDataMethodReturnsCorrectRowInformation() throws ExcelException {
        List<Map<String,String>> data = excelFileProcessor.getRowsBySheetName("Hoja1");

        assertThat(data.toString(),
                is(equalTo("[{idType=1, clientType=N, apellido=5, idNumber=1234}," +
                        " {idType=3, clientType=J, apellido=perez, idNumber=5678}]")));
    }
}
