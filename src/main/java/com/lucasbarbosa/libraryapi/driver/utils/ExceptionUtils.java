package com.lucasbarbosa.libraryapi.driver.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/** @author Lucas Barbosa on 27/06/2021 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtils {

  private static final String BOOK = "book";
  private static final String SELLER = "seller";
  private static final String TITLE = "title";
  private static final String INFO = "with given value(s)";

  private static final String ATTRIBUTE_IN_USE = "attribute.in.use";
  private static final String MESSAGE_NOT_READABLE = "message.not.readable";
  private static final String FEIGN_INTEGRATION = "service.not.available";

  public static final int BAD_REQUEST = 400;
  public static final int NOT_FOUND = 404;

  public static String getTitleAsConst() {
    return TITLE;
  }

  public static String getBookAsConst() {
    return BOOK;
  }

  public static String getSellerAsConst() {
    return SELLER;
  }

  public static String getInfoAsConst() {
    return INFO;
  }

  public static String getAttributeInUseReference() {
    return ATTRIBUTE_IN_USE;
  }

  public static String getMessageNotReadableReference() {
    return MESSAGE_NOT_READABLE;
  }

  public static String getFeignIntegration() {
    return FEIGN_INTEGRATION;
  }

  public static Object[] buildWithSingleParam(String first) {
    return Collections.singletonList(first).toArray();
  }

  public static Object[] buildWithTwoParam(String first, String second) {
    return Arrays.asList(first, second).toArray();
  }

  public static Object[] buildWithThreeParams(String first, String second, String third) {
    return Arrays.asList(first, second, third).toArray();
  }

  public static String retrieveExceptionClassName(Exception exception) {
    return Optional.ofNullable(exception)
        .map(Exception::getClass)
        .map(Class::getSimpleName)
        .orElse(StringUtils.EMPTY);
  }
}
