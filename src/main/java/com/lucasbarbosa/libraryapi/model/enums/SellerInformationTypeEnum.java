package com.lucasbarbosa.libraryapi.model.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/** @author Lucas Barbosa on 24/07/2021 */
@NoArgsConstructor
@Getter
public enum SellerInformationTypeEnum {
  TOKEN,
  TOKEN_EXPIRATION;

  public static SellerInformationTypeEnum findSellerInformationByLiteral(String literal) {
    return Arrays.stream(SellerInformationTypeEnum.values())
        .filter(infoType -> infoType.toString().equalsIgnoreCase(literal))
        .findFirst()
        .orElse(null);
  }
}
