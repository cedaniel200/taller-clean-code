package com.cedaniel200.cleancode.solucion;

import com.cedaniel200.cleancode.solucion.exception.ExcelException;

import java.util.List;
import java.util.Map;

public interface ExcelFileProcessor {
    List<Map<String, String>> getRowsBySheetName(String sheetName) throws ExcelException;
}
