package com.lucasbarbosa.libraryapi.driver.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LibraryUtils {

  private static final String COMMA = ", ";
  private static final String ENUM_ASSURANCE_MESSAGE = "field %s must be any of %s";

  public static String byComma() {
    return COMMA;
  }

  public static String getEnumAssuranceMessage() {
    return ENUM_ASSURANCE_MESSAGE;
  }

  public static String[] createEmptyStringArray() {
    return new String[] {};
  }

  public static String convertObjectToString(Object object) {
    return Optional.ofNullable(object).map(Objects::toString).orElse(StringUtils.EMPTY);
  }

  public static Stream<String> convertEnumToStringStream(Class<? extends Enum<?>> enumeration) {
    return Stream.of(enumeration.getEnumConstants()).map(Enum::name);
  }

  public static String joinStringStreamByComma(Stream<String> stringStream) {
    return stringStream.collect(joining(byComma()));
  }
}
