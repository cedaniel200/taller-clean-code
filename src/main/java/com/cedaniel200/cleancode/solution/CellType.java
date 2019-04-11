package com.cedaniel200.cleancode.solution;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum CellType {

    STRING(Cell.CELL_TYPE_STRING, cell -> Optional.of(cell.getStringCellValue())),
    NUMERIC(Cell.CELL_TYPE_NUMERIC, cell -> Optional.of(String.valueOf(cell.getNumericCellValue()).replace(".0", ""))),
    NONE(-1, cell -> Optional.empty());

    private int cellType;
    private Function<Cell, Optional<String>> function;

    CellType(int cellType, Function<Cell, Optional<String>> function) {
        this.cellType = cellType;
        this.function = function;
    }

    public static Optional<String> getStringValueOfCell(Cell cell) {
        CellType cellTypeSelected = Arrays.stream(values())
                .filter(cellType1 -> cellType1.cellType == cell.getCellType())
                .findFirst().orElse(NONE);
        return cellTypeSelected.function.apply(cell);
    }
}
