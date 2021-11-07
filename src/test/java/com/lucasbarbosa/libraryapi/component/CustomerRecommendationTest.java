package com.lucasbarbosa.libraryapi.component;

import com.lucasbarbosa.libraryapi.properties.ComponentTestProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.lucasbarbosa.libraryapi.pojo.CustomerCountryPojo.build;
import static java.util.stream.Collectors.toList;
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
                  build(
                      scenario.getCustomerCountryOne(),
                      scenario.getCustomerCountryProbabilityOne()),
                  build(
                      scenario.getCustomerCountryTwo(),
                      scenario.getCustomerCountryProbabilityTwo()));
          var scenarioCountry = extractScenarioCountry(countryList);
          assertThat(getFirstChar(scenarioCountry)).isEqualTo(getCountryRecommendation());
          assertThat(scenario.getRecommendation()).isEqualTo(getRecommendation());
        });
  }
}
