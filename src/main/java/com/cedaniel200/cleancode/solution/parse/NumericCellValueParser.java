package com.cedaniel200.cleancode.solution.parse;

import org.apache.poi.ss.usermodel.Cell;

public class NumericCellValueParser implements CellValueParser {

    @Override
    public int getCellType() {
        return Cell.CELL_TYPE_NUMERIC;
    }

    @Override
    public String parseString(Cell cell) {
        return String.valueOf(cell.getNumericCellValue()).replace(".0", "");
    }
}
