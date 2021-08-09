package com.lucasbarbosa.libraryapi.feign.customerapi;

import com.lucasbarbosa.libraryapi.feign.IntegrationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/** @author Lucas Barbosa on 08/08/2021 */
@Service
@Slf4j
public class CustomerService implements IntegrationClient<CustomerVO> {

  private final UserClient userClient;

  public CustomerService(UserClient userClient) {
    this.userClient = userClient;
  }

  @Override
  public Optional<CustomerVO> writeClientIntegration(Optional<Map<String, Object>> params) {
    return Optional.ofNullable(userClient.findCustomer())
        .map(
            customerVO -> {
              log.info("m=retrieveUser customer={}", customerVO);
              return customerVO;
            })
        .or(
            () -> {
              log.warn("m=retrieveUser failed");
              return Optional.empty();
            });
  }

  @Override
  public Class identify() {
    return this.getClass();
  }
}
