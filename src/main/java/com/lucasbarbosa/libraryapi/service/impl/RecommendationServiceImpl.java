package com.lucasbarbosa.libraryapi.service.impl;

import com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils;
import com.lucasbarbosa.libraryapi.feign.agifyapi.AgifyService;
import com.lucasbarbosa.libraryapi.feign.customerapi.CustomerService;
import com.lucasbarbosa.libraryapi.feign.customerapi.CustomerVO;
import com.lucasbarbosa.libraryapi.feign.nationalizeapi.NationalizeService;
import com.lucasbarbosa.libraryapi.feign.restcountryapi.RestCountryService;
import com.lucasbarbosa.libraryapi.model.dto.CustomerLibrary;
import com.lucasbarbosa.libraryapi.model.dto.RecommendationCustomer;
import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;
import com.lucasbarbosa.libraryapi.service.BookService;
import com.lucasbarbosa.libraryapi.service.RecommendationService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.areAllPresent;
import static com.lucasbarbosa.libraryapi.feign.IntegrationParamEnum.COUNTRY_CODE;
import static com.lucasbarbosa.libraryapi.feign.IntegrationParamEnum.CUSTOMER_NAME;
import static com.lucasbarbosa.libraryapi.model.dto.CustomerLibrary.of;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.CompletableFuture.supplyAsync;

/** @author Lucas Barbosa on 28/08/2021 */
@Service
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {

  private final CustomerService customerService;

  private final AgifyService agifyService;

  private final NationalizeService nationalizeService;

  private final RestCountryService restCountryService;

  private final BookService bookService;

  public RecommendationServiceImpl(
      CustomerService customerService,
      AgifyService agifyService,
      NationalizeService nationalizeService,
      RestCountryService restCountryService,
      BookService bookService) {
    this.customerService = customerService;
    this.agifyService = agifyService;
    this.nationalizeService = nationalizeService;
    this.restCountryService = restCountryService;
    this.bookService = bookService;
  }

  @Override
  @SneakyThrows
  public Optional<CustomerLibrary> fetchCustomerLibrary() {
    CompletableFuture<CustomerLibrary> customerFuture =
        supplyAsync(() -> customerService.retrieveClient(Optional.empty()))
            .thenCompose(this::retrieveCustomerInfo);

    return ofNullable(customerFuture.get()).filter(Predicate.not(ObjectUtils::isEmpty));
  }

  @Override
  public Optional<RecommendationCustomer> getRecommendation() {
    var customer = fetchCustomerLibrary();
    var recommendation =
        RecommendationCustomer.of(
            customer, bookService.fetchBooksByGenre(obtainRecommendedGenreByCustomer(customer)));
    return Optional.ofNullable(recommendation);
  }

  private BookGenreEnum obtainRecommendedGenreByCustomer(
      Optional<CustomerLibrary> customerLibrary) {
    var customerAge = getCustomerAge(customerLibrary);
    if (customerAge < 15) {
      return BookGenreEnum.COMEDY;
    } else if (customerAge < 25) {
      return BookGenreEnum.ACTION;
    } else if (customerAge < 35) {
      return BookGenreEnum.MYSTERY;
    } else if (customerAge < 55) {
      return BookGenreEnum.THRILLER;
    } else if (customerAge < 65) {
      return BookGenreEnum.FANTASY;
    } else if (customerAge < 75) {
      return BookGenreEnum.HORROR;
    } else return BookGenreEnum.ROMANCE;
  }

  private Integer getCustomerAge(Optional<CustomerLibrary> customerLibrary) {
    return customerLibrary
        .map(CustomerLibrary::getAge)
        .map(Integer::parseInt)
        .orElse(LibraryUtils.ONE_THOUSAND);
  }

  private Optional<Integer> buildAgeFuture(Optional<CustomerVO> customerVO) {
    Map<String, Object> agifyParams = new HashMap<>();
    agifyParams.put(
        CUSTOMER_NAME.getValue(),
        customerVO.map(CustomerVO::getFirstName).orElse(StringUtils.EMPTY));
    var ageFuture = supplyAsync(() -> agifyService.retrieveClient(Optional.of(agifyParams)));
    return ageFuture.join();
  }

  private Optional<String> buildCountryFuture(Optional<CustomerVO> customerVO) {
    Map<String, Object> nationalizeParams = new HashMap<>();
    nationalizeParams.put(
        CUSTOMER_NAME.getValue(),
        customerVO.map(CustomerVO::getFirstName).orElse(StringUtils.EMPTY));
    var countryFuture =
        supplyAsync(() -> nationalizeService.retrieveClient(Optional.of(nationalizeParams)))
            .thenCompose(this::translateInitialIntoCountry);
    return countryFuture.join();
  }

  private CompletableFuture<Optional<String>> translateInitialIntoCountry(
      Optional<String> countryInitial) {
    Map<String, Object> restCountryParams = new HashMap<>();
    countryInitial.ifPresent(country -> restCountryParams.put(COUNTRY_CODE.getValue(), country));
    return supplyAsync(() -> restCountryService.retrieveClient(Optional.of(restCountryParams)));
  }

  private CompletableFuture<CustomerLibrary> retrieveCustomerInfo(Optional<CustomerVO> customerVO) {
    return supplyAsync(() -> buildCountryFuture(customerVO))
        .thenCombine(
            supplyAsync(() -> buildAgeFuture(customerVO)),
            (country, age) ->
                areAllPresent(List.of(country, age)) ? of(customerVO, country, age) : null);
  }
}
