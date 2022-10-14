package com.cedaniel200.cleancode.solucion;

public class Main {

    public static void main(String[] args) {
        print(new XYZExcelFileProcessor());
        print(DefaultExcelFileProcessor
                .createExcelFileProcessor("Clientes.xlsx"));
    }

    private static void print(ExcelFileProcessor excelFileProcessor){
        if(excelFileProcessor instanceof DefaultExcelFileProcessor) System.out.println("Default");
        if(excelFileProcessor instanceof XYZExcelFileProcessor) System.out.println("XYZ");
    }
}
