package com.lucasbarbosa.libraryapi.pojo;

import java.math.BigDecimal;

/** @author Lucas Barbosa on 07/11/2021 */
public class CustomerCountryPojo {

  private String country;
  private BigDecimal probability;

  public CustomerCountryPojo() {}

  private CustomerCountryPojo(String country, String probability) {
    this.country = country;
    this.probability = new BigDecimal(probability);
  }

  public static CustomerCountryPojo build(String country, String probability) {
    return new CustomerCountryPojo(country, probability);
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
