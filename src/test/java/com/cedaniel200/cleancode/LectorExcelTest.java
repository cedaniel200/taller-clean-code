package com.cedaniel200.cleancode;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static com.cedaniel200.cleancode.LectorExcel.crearLectorExcel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class LectorExcelTest {

    @Test
    public void testMustSucceedWhenDataMethodReturnsTwoRows() throws ExcelException {
        List<HashMap<String,String>> data = crearLectorExcel("Clientes.xlsx").getFilasPorHoja( "Hoja1");

        assertThat(data.size(), is(equalTo(2)));
    }

    @Test
    public void testMustSucceedWhenDataMethodReturnsFourCells() throws ExcelException {
        List<HashMap<String,String>> data = crearLectorExcel("Clientes.xlsx").getFilasPorHoja( "Hoja1");
        HashMap<String,String> cells = data.get(0);

        assertThat(cells.size(), is(equalTo(4)));
    }

    @Test
    public void testMustSucceedWhenDataMethodReturnsCorrectRowInformation() throws ExcelException {
        List<HashMap<String,String>> data = crearLectorExcel("Clientes.xlsx").getFilasPorHoja( "Hoja1");

        assertThat(data.toString(),
                is(equalTo("[{clientType=N, idType=1, apellido=5, idNumber=1234}, {clientType=J, idType=3, apellido=perez, idNumber=5678}]")));
    }
}
