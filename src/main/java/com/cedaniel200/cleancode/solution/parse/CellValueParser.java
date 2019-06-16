package com.cedaniel200.cleancode.solution.parse;

import org.apache.poi.ss.usermodel.Cell;

public interface CellValueParser {
    int getCellType();
    String parseString(Cell cell);
}
