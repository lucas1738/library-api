package com.lucasbarbosa.libraryapi.pojo;

import java.math.BigDecimal;

/** @author Lucas Barbosa on 07/11/2021 */
public class CustomerCountryPojo {

  private String country;
  private BigDecimal probability;

  public CustomerCountryPojo() {}

  public CustomerCountryPojo(String country, BigDecimal probability) {
    this.country = country;
    this.probability = probability;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public BigDecimal getProbability() {
    return probability;
  }

  public void setProbability(BigDecimal probability) {
    this.probability = probability;
  }
}
