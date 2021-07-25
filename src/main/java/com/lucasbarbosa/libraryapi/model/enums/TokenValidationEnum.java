package com.lucasbarbosa.libraryapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** @author Lucas Barbosa on 25/07/2021 */
@AllArgsConstructor
@Getter
public enum TokenValidationEnum {
  TOKEN_KEY_GENERATED("token.key.generated"),
  TOKEN_KEY_INCORRECT("token.key.incorrect"),
  TOKEN_DOCUMENT_INCORRECT("token.document.incorrect"),
  TOKEN_KEY_VALIDITY("token.key.validity"),
  TOKEN_KEY_EXPIRED("token.key.expired");

  private String message;
}
