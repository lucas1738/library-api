package com.lucasbarbosa.libraryapi.properties;

import com.lucasbarbosa.libraryapi.pojo.CustomerRecommendationPojo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/** @author Lucas Barbosa on 02/11/2021 */
@Component
@ConfigurationProperties(prefix = "scenarios")
public class ComponentTestProperties {
  private Map<String, CustomerRecommendationPojo> customerRecommendation;


  public Map<String, CustomerRecommendationPojo> getCustomerRecommendation() {
    return customerRecommendation;
  }

  public void setCustomerRecommendation(
      Map<String, CustomerRecommendationPojo> customerRecommendation) {
    this.customerRecommendation = customerRecommendation;
  }
}
