package com.cedaniel200.cleancode.solucion.utils;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {
    private StreamUtils() {
    }

    public static <T> Stream<T> toStream(Spliterator<T> rowSpliterator) {
        return StreamSupport.stream(rowSpliterator, false);
    }
}