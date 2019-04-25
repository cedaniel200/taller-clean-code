package com.cedaniel200.cleancode;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LectorExcel {

    private static final String RUTA_RECURSOS_TEST = System.getProperty("user.dir") + "//src//test//resources//";
    private String nombreArchivo;
    private Row filaDelEncabezado;

    private LectorExcel(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public static LectorExcel crearLectorExcel(String nombreArchivo) {
        return new LectorExcel(nombreArchivo);
    }

    public List<HashMap<String, String>> getFilasPorHoja(String nombreHoja) throws ExcelException {
        try (FileInputStream archivoExcel = new FileInputStream(RUTA_RECURSOS_TEST + nombreArchivo)) {
            return intentarObtenerFilasPorHoja(archivoExcel, nombreHoja);
        } catch (IOException e) {
            throw new ExcelException("Ocurrio un error al leer el archivo de excel con nombre " + nombreArchivo, e);
        }
    }

    private List<HashMap<String, String>> intentarObtenerFilasPorHoja(FileInputStream archivoExcel, String nombreHoja) throws IOException {
        XSSFWorkbook libroTrabajo = new XSSFWorkbook(archivoExcel);
        XSSFSheet hoja = libroTrabajo.getSheet(nombreHoja);
        filaDelEncabezado = hoja.getRow(0);
        return obtenerFilasProcesadasPorHoja(hoja);
    }

    private List<HashMap<String, String>> obtenerFilasProcesadasPorHoja(XSSFSheet hoja) {
        List<HashMap<String, String>> filas = new ArrayList<>();
        for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i ++) {
            Row fila = hoja.getRow(i);
            filas.add(obtenerCeldasProcesadasPorFila(fila));
        }
        return filas;
    }

    private HashMap<String, String> obtenerCeldasProcesadasPorFila(Row fila) {
        HashMap<String, String> celdas = new HashMap<>();
        for (int j = 0; j < fila.getPhysicalNumberOfCells(); j ++) {
            Cell celda = fila.getCell(j);


            switch (celda.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    celdas.put(obtenerClave(j), fila.getCell(j).getStringCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    celdas.put(obtenerClave(j), String.valueOf(celda.getNumericCellValue()).replace(".0", ""));
                    break;
                // faltan mas tipo por implementar
                default:
                    break;
            }
        }
        return celdas;
    }

    private String obtenerClave(int posicionCelda) {
        return filaDelEncabezado.getCell(posicionCelda).getStringCellValue();
    }
}