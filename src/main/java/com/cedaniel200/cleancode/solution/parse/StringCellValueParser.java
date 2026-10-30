package com.cedaniel200.cleancode.solution.parse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class StringCellValueParser implements CellValueParser {
    @Override
    public CellType getCellType() {
        return CellType.STRING;
    }

    @Override
    public String parseString(Cell cell) {
        return cell.getStringCellValue();
    }
}
