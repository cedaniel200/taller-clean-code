package com.cedaniel200.cleancode.solucion;

import org.apache.poi.ss.usermodel.Cell;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CellParser {

    private static Map<Integer, Function<Cell, String>> cellParser;

    public CellParser() {
        cellParser = new HashMap<>();
    }

    public void registerCellValueParser(int type, Function<Cell, String> function){
        cellParser.put(type, function);
    }

    public String getValue(Cell c) {
        if (!cellParser.containsKey(c.getCellType())) throw new IllegalStateException();
        return cellParser.get(c.getCellType())
                .apply(c);
    }
}