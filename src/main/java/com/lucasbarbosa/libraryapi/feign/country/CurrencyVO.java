package com.lucasbarbosa.libraryapi.feign.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** @author Lucas Barbosa on 31/07/2021 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CurrencyVO {

  private String code;
  private String name;
  private String symbol;
}
