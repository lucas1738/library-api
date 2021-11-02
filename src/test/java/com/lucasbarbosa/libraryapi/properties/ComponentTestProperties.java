package com.lucasbarbosa.libraryapi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/** @author Lucas Barbosa on 02/11/2021 */
@Component
@ConfigurationProperties(prefix = "scenarios")
public class ComponentTestProperties {
  private Map<String, CustomerRecommendationProbe> customerRecommendation;

  public Map<String, CustomerRecommendationProbe> getCustomerRecommendation() {
    return customerRecommendation;
  }

  public void setCustomerRecommendation(Map<String, CustomerRecommendationProbe> customerRecommendation) {
    this.customerRecommendation = customerRecommendation;
  }
// getters and setters

  public static class CustomerRecommendationProbe {

    private String customerAge;
    private String recommendation;

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
  }
}
