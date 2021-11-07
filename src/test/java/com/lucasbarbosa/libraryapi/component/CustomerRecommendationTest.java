package com.lucasbarbosa.libraryapi.component;

import com.lucasbarbosa.libraryapi.model.dto.CustomerLibrary;
import com.lucasbarbosa.libraryapi.model.dto.CustomerRecommendation;
import com.lucasbarbosa.libraryapi.pojo.CustomerCountryPojo;
import com.lucasbarbosa.libraryapi.properties.ComponentTestProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

/** @author Lucas Barbosa on 02/11/2021 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class CustomerRecommendationTest extends CustomerRecommendationTestSupport {

  @Autowired private ComponentTestProperties testProperties;

  @Test
  @DisplayName("Given enrichment then return suitable customer recommendation")
  void givenEnrichmentThenReturnSuitableCustomerRecommendation() {
    var scenarios = testProperties.getCustomerRecommendation().values().stream().collect(toList());
    scenarios.forEach(
        scenario -> {
          setUpTestMocks(scenario);
          var countryList =
              List.of(
                  new CustomerCountryPojo(
                      scenario.getCustomerCountryOne(),
                      new BigDecimal(scenario.getCustomerCountryProbabilityOne())),
                  new CustomerCountryPojo(
                      scenario.getCustomerCountryTwo(),
                      new BigDecimal(scenario.getCustomerCountryProbabilityTwo())));
          var countryInitial =
              countryList.stream()
                  .max(Comparator.comparing(CustomerCountryPojo::getProbability))
                  .map(CustomerCountryPojo::getCountry)
                  .orElse(EMPTY);
          assertThat(getFirstChar(countryInitial))
              .isEqualTo(
                  recommendationService
                      .getRecommendation()
                      .map(CustomerRecommendation::getCustomer)
                      .map(CustomerLibrary::getCountry)
                      .map(CustomerRecommendationTestSupport::getFirstChar)
                      .orElse(EMPTY));
          assertThat(scenario.getRecommendation())
              .isEqualTo(
                  recommendationService
                      .getRecommendation()
                      .map(CustomerRecommendation::getRecommendedGenre)
                      .orElse(EMPTY));
        });
  }
}
