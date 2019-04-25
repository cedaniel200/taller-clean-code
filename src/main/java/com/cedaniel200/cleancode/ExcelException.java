package com.cedaniel200.cleancode;

import java.io.IOException;

public class ExcelException extends IOException {
    public ExcelException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
