package com.cedaniel200.cleancode.solution.parse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class NumericCellValueParser implements CellValueParser {

    @Override
    public CellType getCellType() {
        return CellType.NUMERIC;
    }

    @Override
    public String parseString(Cell cell) {
        return String.valueOf(cell.getNumericCellValue()).replace(".0", "");
    }
}
