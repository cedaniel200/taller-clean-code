package com.cedaniel200.cleancode.taller20190409;

import java.io.IOException;

public class ExcelException extends IOException {
    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
