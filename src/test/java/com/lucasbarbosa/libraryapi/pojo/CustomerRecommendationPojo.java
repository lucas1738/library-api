package com.lucasbarbosa.libraryapi.pojo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/** @author Lucas Barbosa on 05/11/2021 */
public class CustomerRecommendationPojo {
  private String customerAge;
  private String recommendation;
  private String customerFirstName;
  private String customerCountryOne;
  private String customerCountryProbabilityOne;
  private String customerCountryTwo;
  private String customerCountryProbabilityTwo;
  private String customerCountry;

  public String getCustomerAge() {
    return customerAge;
  }

  public void setCustomerAge(String customerAge) {
    this.customerAge = customerAge;
  }

  public String getRecommendation() {
    return recommendation;
  }

  public void setRecommendation(String recommendation) {
    this.recommendation = recommendation;
  }

  public String getCustomerFirstName() {
    return customerFirstName;
  }

  public void setCustomerFirstName(String customerFirstName) {
    this.customerFirstName = customerFirstName;
  }

  public String getCustomerCountryOne() {
    return customerCountryOne;
  }

  public void setCustomerCountryOne(String customerCountryOne) {
    this.customerCountryOne = customerCountryOne;
  }

  public String getCustomerCountryProbabilityOne() {
    return customerCountryProbabilityOne;
  }

  public void setCustomerCountryProbabilityOne(String customerCountryProbabilityOne) {
    this.customerCountryProbabilityOne = customerCountryProbabilityOne;
  }

  public String getCustomerCountryTwo() {
    return customerCountryTwo;
  }

  public void setCustomerCountryTwo(String customerCountryTwo) {
    this.customerCountryTwo = customerCountryTwo;
  }

  public String getCustomerCountryProbabilityTwo() {
    return customerCountryProbabilityTwo;
  }

  public void setCustomerCountryProbabilityTwo(String customerCountryProbabilityTwo) {
    this.customerCountryProbabilityTwo = customerCountryProbabilityTwo;
  }

  public String getCustomerCountry() {
    return customerCountry;
  }

  public void setCustomerCountry(String customerCountry) {
    this.customerCountry = customerCountry;
  }
}
