package com.lucasbarbosa.libraryapi.driver.exception.custom;

/** @author Lucas Barbosa on 27/06/2021 */
public class AttributeInUseException extends BusinessException {

  public AttributeInUseException(String first, String second, String third) {
    super(first, second, third);
  }
}
