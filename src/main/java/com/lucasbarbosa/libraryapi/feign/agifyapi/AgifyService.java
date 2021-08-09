package com.lucasbarbosa.libraryapi.feign.agifyapi;

import com.lucasbarbosa.libraryapi.feign.IntegrationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.searchMapByParam;
import static com.lucasbarbosa.libraryapi.feign.IntegrationParamEnum.CUSTOMER_NAME;

/** @author Lucas Barbosa on 01/08/2021 */
@Service
@Slf4j
public class AgifyService implements IntegrationClient<Integer> {

  private final AgifyClient agifyClient;

  public AgifyService(AgifyClient agifyClient) {
    this.agifyClient = agifyClient;
  }

  @Override
  public Optional<Integer> writeClientIntegration(Optional<Map<String, Object>> params) {
    String customerName = searchMapByParam(params, CUSTOMER_NAME);

    return Optional.ofNullable(agifyClient.findCustomerAge(customerName))
        .map(
            agifyVO -> {
              log.info("m=retrieveCustomerAge age={}", agifyVO);
              return agifyVO.getAge();
            })
        .or(
            () -> {
              log.warn("m=retrieveCustomerAge failed");
              return Optional.empty();
            });
  }

  @Override
  public Class identify() {
    return this.getClass();
  }
}
