package com.lucasbarbosa.libraryapi.component;

import com.lucasbarbosa.libraryapi.properties.ComponentTestProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

/** @author Lucas Barbosa on 02/11/2021 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class CustomerRecommendationTest {

  @Autowired private ComponentTestProperties testProperties;

  @Test
  @DisplayName("whenYamlFileProvidedThenInjectSimpleMap")
  void whenYamlFileProvidedThenInjectSimpleMap() {
    var list =
        testProperties.getCustomerRecommendation().values().stream().collect(Collectors.toList());
    var novo = testProperties.getCustomerRecommendation();
  }
}
