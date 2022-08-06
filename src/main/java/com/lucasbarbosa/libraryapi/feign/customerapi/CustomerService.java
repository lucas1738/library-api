package com.lucasbarbosa.libraryapi.feign.customerapi;

import com.lucasbarbosa.libraryapi.feign.IntegrationClient;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Barbosa on 08/08/2021
 */
@Service
@Slf4j
public class CustomerService implements IntegrationClient<CustomerVO, CustomerService> {

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
              return Optional.of(
                  CustomerVO.builder().firstName("Max").lastName("Kaufmann").build());
            });
  }

  @Override
  public Class<CustomerService> identify() {
    return CustomerService.class;
  }

  @Override
  public Optional<CustomerVO> value() {
    return Optional.of(
        CustomerVO.builder().firstName("Max").lastName("Kaufmann").build());
  }
}
