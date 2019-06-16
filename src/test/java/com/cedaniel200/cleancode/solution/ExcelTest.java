package com.cedaniel200.cleancode.solution;

import com.cedaniel200.cleancode.solution.exception.ExcelException;
import com.cedaniel200.cleancode.solution.parse.CellParser;
import com.cedaniel200.cleancode.solution.parse.NumericCellValueParser;
import com.cedaniel200.cleancode.solution.parse.StringCellValueParser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.cedaniel200.cleancode.solution.ExcelFileProcessor.createExcelFileProcessor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ExcelTest {

    private ExcelFileProcessor excelFileProcessor;

    @Before
    public void setup() {
        CellParser cellParser = new CellParser();
        cellParser.registerCellValueParser(new NumericCellValueParser());
        cellParser.registerCellValueParser(new StringCellValueParser());

        excelFileProcessor = createExcelFileProcessor("Clientes.xlsx");
        excelFileProcessor.setCellParser(cellParser);
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
}
