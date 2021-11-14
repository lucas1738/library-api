package com.lucasbarbosa.libraryapi.feign.restcountryapi;

import com.lucasbarbosa.libraryapi.feign.IntegrationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.searchMapByParam;
import static com.lucasbarbosa.libraryapi.feign.IntegrationParamEnum.COUNTRY_CODE;

/** @author Lucas Barbosa on 08/08/2021 */
@Service
@Slf4j
public class RestCountryService implements IntegrationClient<String, RestCountryService> {

  private final CountryClient countryClient;

  public RestCountryService(CountryClient countryClient) {
    this.countryClient = countryClient;
  }

  @Override
  public Optional<String> writeClientIntegration(Optional<Map<String, Object>> params) {
    String countryCode = searchMapByParam(params, COUNTRY_CODE);

    return Optional.ofNullable(countryClient.findCountryByInitial(countryCode))
        .map(
            countryVO -> {
              log.info("m=retrieveCountryById countryName={}", countryVO);
              return countryVO.getName();
            })
        .or(
            () -> {
              log.warn("m=retrieveCountryById failed");
              return Optional.empty();
            });
  }

  @Override
  public Class<RestCountryService> identify() {
    return RestCountryService.class;
  }
}
