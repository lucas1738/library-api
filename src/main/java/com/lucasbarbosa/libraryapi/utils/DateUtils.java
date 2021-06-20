package com.lucasbarbosa.libraryapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    private static final String DATETIME_BR_PATTERN = "dd/MM/yyyy HH:mm";

    public static String formatDateTime(LocalDateTime time){
        return time.format(DateTimeFormatter.ofPattern(DATETIME_BR_PATTERN));
    }
}
