package com.cedaniel200.cleancode.solution.parse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public interface CellValueParser {
    CellType getCellType();
    String parseString(Cell cell);
}
