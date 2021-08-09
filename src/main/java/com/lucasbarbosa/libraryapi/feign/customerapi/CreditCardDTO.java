package com.lucasbarbosa.libraryapi.feign.customerapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
public class CreditCardDTO {

  @JsonProperty("cc_number")
  private String creditCardNumber;
}
