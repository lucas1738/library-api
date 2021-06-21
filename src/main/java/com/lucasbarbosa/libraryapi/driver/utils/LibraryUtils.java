package com.lucasbarbosa.libraryapi.driver.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LibraryUtils {

    private static final String COMMA = ",";

    public static final String byComma(){
        return COMMA;
    }

    public static String getAllowedValues(Object object) {
        return Stream.of(object.getClass().getEnumConstants())
                .map(Objects::toString)
                .collect(joining(byComma()));
    }
}
