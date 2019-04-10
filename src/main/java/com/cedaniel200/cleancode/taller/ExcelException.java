package com.cedaniel200.cleancode.taller;

import java.io.IOException;

public class ExcelException extends IOException {
    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
