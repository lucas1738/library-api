package com.lucasbarbosa.libraryapi.integration.customerapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
public class CreditCardVO {

  @JsonProperty("cc_number")
  private String creditCardNumber;
}
