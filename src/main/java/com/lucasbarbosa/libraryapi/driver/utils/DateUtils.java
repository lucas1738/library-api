package com.lucasbarbosa.libraryapi.driver.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.ONE_THOUSAND;
/** @author Lucas Barbosa on 27/06/2021 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

  private static final String DATETIME_BR_PATTERN = "dd/MM/yyyy HH:mm";

  public static String formatDateTime(LocalDateTime time) {
    return time.format(DateTimeFormatter.ofPattern(DATETIME_BR_PATTERN));
  }

  public static LocalDateTime buildMaxLocalDateTime() {
    return LocalDateTime.now().plusYears(ONE_THOUSAND);
  }

  public static LocalDateTime buildMinLocalDateTime() {
    return LocalDateTime.now().minusYears(ONE_THOUSAND);
  }
}
