package com.lucasbarbosa.libraryapi.feign.agifyapi;

import com.lucasbarbosa.libraryapi.driver.exception.custom.FeignIntegrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.retrieveExceptionClassName;

/** @author Lucas Barbosa on 01/08/2021 */
@Service
@Slf4j
public class AgifyService {

  private final AgifyClient agifyClient;

  public AgifyService(AgifyClient agifyClient) {
    this.agifyClient = agifyClient;
  }

  public Optional<Integer> retrieveCustomerAge(String customerName) {
    try {
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
    } catch (Exception exception) {
      log.error(
          "m=fetchProductPrice exception={} exceptionName={}",
          exception,
          retrieveExceptionClassName(exception));
      throw new FeignIntegrationException(this.getClass().getSimpleName());
    }
  }
}
