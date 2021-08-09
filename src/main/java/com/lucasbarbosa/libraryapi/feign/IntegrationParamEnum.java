package com.lucasbarbosa.libraryapi.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** @author Lucas Barbosa on 08/08/2021 */
@AllArgsConstructor
@Getter
public enum IntegrationParamEnum {
  COUNTRY_CODE("countryCode"),
  CUSTOMER_NAME("customerName");

  private final String value;
}
