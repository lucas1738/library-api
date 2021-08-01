package com.lucasbarbosa.libraryapi.integration.customerapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

/** @author Lucas Barbosa on 31/07/2021 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class AddressDTO {

  private String city;
  private String streetName;
  private String streetAddress;
  private String zipCode;
  private String state;
  private String country;
  private CoordinateDTO coordinates;
}
