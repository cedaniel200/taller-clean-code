package com.cedaniel200.cleancode.solution.parse;

import org.apache.poi.ss.usermodel.Cell;

public class StringCellValueParser implements CellValueParser {
    @Override
    public int getCellType() {
        return Cell.CELL_TYPE_STRING;
    }

    @Override
    public String parseString(Cell cell) {
        return cell.getStringCellValue();
    }
}
