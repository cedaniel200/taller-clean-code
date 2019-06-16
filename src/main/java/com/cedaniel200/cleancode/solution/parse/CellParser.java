package com.cedaniel200.cleancode.solution.parse;

import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CellParser {

    private List<CellValueParser> parsers;

    public CellParser() {
        this.parsers = new ArrayList<>();
    }

    public void registerCellValueParser(CellValueParser cellValueParser){
        parsers.add(cellValueParser);
    }

    public Optional<String> parseString(Cell cell){
        Optional<String> cellValue = Optional.empty();
        Optional<CellValueParser> cellValueParser = getCellValueParserByCellType(cell);
        if(cellValueParser.isPresent()){
            cellValue = Optional.of(cellValueParser.get().parseString(cell));
        }
        return cellValue;
    }

    private Optional<CellValueParser> getCellValueParserByCellType(Cell cell) {
        return parsers.stream()
                .filter(parser -> parser.getCellType() == cell.getCellType())
                .findFirst();
    }

}
