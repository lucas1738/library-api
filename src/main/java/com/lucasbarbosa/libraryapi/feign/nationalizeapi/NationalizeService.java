package com.lucasbarbosa.libraryapi.feign.nationalizeapi;

import com.lucasbarbosa.libraryapi.feign.IntegrationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.searchMapByParam;
import static com.lucasbarbosa.libraryapi.feign.IntegrationParamEnum.CUSTOMER_NAME;
import static java.util.Comparator.comparing;

/** @author Lucas Barbosa on 08/08/2021 */
@Service
@Slf4j
public class NationalizeService implements IntegrationClient<String, NationalizeService> {

  private final NationalizeClient nationalizeClient;

  public NationalizeService(NationalizeClient nationalizeClient) {
    this.nationalizeClient = nationalizeClient;
  }

  @Override
  public Optional<String> writeClientIntegration(Optional<Map<String, Object>> params) {
    String customerName = searchMapByParam(params, CUSTOMER_NAME);
    return Optional.ofNullable(nationalizeClient.findCustomerCountry(customerName))
        .map(
            nationalizeVO -> {
              log.info("m=retrieveCustomerCountry country={}", nationalizeVO);
              return nationalizeVO.getCountry();
            })
        .flatMap(
            countryDTO -> countryDTO.stream().max(comparing(NationalizeCountryDTO::getProbability)))
        .map(NationalizeCountryDTO::getCountryId)
        .or(
            () -> {
              log.warn("m=retrieveCustomerCountry failed");
              return Optional.of("Germany");
            });
  }

  @Override
  public Class<NationalizeService> identify() {
    return NationalizeService.class;
  }

  @Override
  public Optional<String> value() {
    return Optional.of("Germany");
  }
}
