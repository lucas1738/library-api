package com.lucasbarbosa.libraryapi.feign.restcountryapi;

import java.util.Map;
import java.util.Optional;

import com.lucasbarbosa.libraryapi.feign.IntegrationClient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.searchMapByParam;
import static com.lucasbarbosa.libraryapi.feign.IntegrationParamEnum.COUNTRY_CODE;

/**
 * @author Lucas Barbosa on 08/08/2021
 */
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

    return Optional.ofNullable(countryCode)
        .filter(StringUtils::isNotEmpty)
        .map(countryClient::findCountryByInitial)
        .flatMap(list -> list.stream().findFirst())
        .map(
            countryVO -> {
              log.info("m=retrieveCountryById countryName={}", countryVO);
              return countryVO.getName();
            })
        .or(
            () -> {
              log.warn("m=retrieveCountryById failed");
              return Optional.of("Germany");
            });
  }

  @Override
  public Class<RestCountryService> identify() {
    return RestCountryService.class;
  }

  @Override
  public Optional<String> value() {
    return Optional.of("Germany");
  }
}
