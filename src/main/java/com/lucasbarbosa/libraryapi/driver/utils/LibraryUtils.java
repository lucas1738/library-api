package com.lucasbarbosa.libraryapi.driver.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LibraryUtils {

  private static final String COMMA = ", ";
  private static final String ENUM_ASSURANCE_MESSAGE = "field %s must be any of %s";
  private static final int ONE_THOUSAND = 1000;

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

  public static List<String> convertEnumToStringList(Class<? extends Enum<?>> enumeration) {
    return Stream.of(enumeration.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
  }

  public static String joinStringListByComma(List<String> stringList) {
    return String.join(COMMA, stringList);
  }

  public static LocalDateTime buildMaxLocalDateTime() {
    return LocalDateTime.now().plusYears(ONE_THOUSAND);
  }

  public static LocalDateTime buildMinLocalDateTime() {
    return LocalDateTime.now().minusYears(ONE_THOUSAND);
  }
}
