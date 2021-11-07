package com.lucasbarbosa.libraryapi.component;

import com.lucasbarbosa.libraryapi.feign.agifyapi.AgifyClient;
import com.lucasbarbosa.libraryapi.feign.agifyapi.AgifyService;
import com.lucasbarbosa.libraryapi.feign.agifyapi.AgifyVO;
import com.lucasbarbosa.libraryapi.feign.customerapi.CustomerService;
import com.lucasbarbosa.libraryapi.feign.customerapi.CustomerVO;
import com.lucasbarbosa.libraryapi.feign.nationalizeapi.NationalizeClient;
import com.lucasbarbosa.libraryapi.feign.nationalizeapi.NationalizeCountryDTO;
import com.lucasbarbosa.libraryapi.feign.nationalizeapi.NationalizeService;
import com.lucasbarbosa.libraryapi.feign.nationalizeapi.NationalizeVO;
import com.lucasbarbosa.libraryapi.feign.restcountryapi.CountryClient;
import com.lucasbarbosa.libraryapi.feign.restcountryapi.CountryVO;
import com.lucasbarbosa.libraryapi.feign.restcountryapi.RestCountryService;
import com.lucasbarbosa.libraryapi.model.dto.CustomerLibrary;
import com.lucasbarbosa.libraryapi.model.dto.CustomerRecommendation;
import com.lucasbarbosa.libraryapi.pojo.CustomerCountryPojo;
import com.lucasbarbosa.libraryapi.pojo.CustomerRecommendationPojo;
import com.lucasbarbosa.libraryapi.service.BookService;
import com.lucasbarbosa.libraryapi.service.RecommendationService;
import com.lucasbarbosa.libraryapi.service.impl.RecommendationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/** @author Lucas Barbosa on 05/11/2021 */
public class CustomerRecommendationTestSupport {

  @Mock protected RecommendationService recommendationService;

  @Mock protected CustomerService customerService;

  @Mock protected AgifyService agifyService;

  @Mock protected AgifyClient agifyClient;

  @Mock protected NationalizeService nationalizeService;

  @Mock protected NationalizeClient nationalizeClient;

  @Mock protected RestCountryService restCountryService;

  @Mock protected CountryClient countryClient;

  @Mock protected BookService bookService;

  protected CustomerVO customerVO;

  protected NationalizeVO nationalizeVO;

  protected CountryVO countryVO;

  protected AgifyVO agifyVO;

  @BeforeEach
  void initializeTest() {
    MockitoAnnotations.initMocks(this);
    nationalizeService = new NationalizeService(nationalizeClient);
    restCountryService = new RestCountryService(countryClient);
    agifyService = new AgifyService(agifyClient);
    recommendationService =
        new RecommendationServiceImpl(
            customerService, agifyService, nationalizeService, restCountryService, bookService);
    customerVO = new CustomerVO();
    nationalizeVO = new NationalizeVO();
    countryVO = new CountryVO();
  }

  protected void buildCustomerVO(String firstName) {
    customerVO = CustomerVO.builder().firstName(firstName).build();
  }

  protected void buildCountryVO(String country) {
    countryVO = CountryVO.builder().name(country).build();
  }

  protected void buildAgifyVO(String age) {
    agifyVO = AgifyVO.builder().age(Integer.parseInt(age)).build();
  }

  protected void buildNationalizeVO(CustomerRecommendationPojo customerRecommendationPojo) {
    var countryOne =
        new NationalizeCountryDTO(
            customerRecommendationPojo.getCustomerCountryOne(),
            new BigDecimal(customerRecommendationPojo.getCustomerCountryProbabilityOne()));
    var countryTwo =
        new NationalizeCountryDTO(
            customerRecommendationPojo.getCustomerCountryTwo(),
            new BigDecimal(customerRecommendationPojo.getCustomerCountryProbabilityTwo()));
    var countryList = List.of(countryOne, countryTwo);
    nationalizeVO = NationalizeVO.builder().country(countryList).build();
  }

  protected void setUpTestMocks(CustomerRecommendationPojo pojo) {
    buildCustomerVO(pojo.getCustomerFirstName());
    when(customerService.retrieveClient(any())).thenReturn(Optional.of(customerVO));
    buildNationalizeVO(pojo);
    when(nationalizeClient.findCustomerCountry(any())).thenReturn(nationalizeVO);
    buildCountryVO(pojo.getCustomerCountry());
    when(countryClient.findCountryByInitial(any())).thenReturn(countryVO);
    buildAgifyVO(pojo.getCustomerAge());
    when(agifyClient.findCustomerAge(any())).thenReturn(agifyVO);
    when(bookService.fetchBooksByGenre(any())).thenReturn(Collections.emptyList());
  }

  protected String getRecommendation() {
    return recommendationService
        .getRecommendation()
        .map(CustomerRecommendation::getRecommendedGenre)
        .orElse(EMPTY);
  }

  protected String getCountryRecommendation() {
    return recommendationService
        .getRecommendation()
        .map(CustomerRecommendation::getCustomer)
        .map(CustomerLibrary::getCountry)
        .map(CustomerRecommendationTestSupport::getFirstChar)
        .orElse(EMPTY);
  }

  protected String extractScenarioCountry(List<CustomerCountryPojo> countryList) {
    return countryList.stream()
        .max(Comparator.comparing(CustomerCountryPojo::getProbability))
        .map(CustomerCountryPojo::getCountry)
        .orElse(EMPTY);
  }

  protected static String getFirstChar(String string) {
    return String.valueOf(string.charAt(0));
  }
}
