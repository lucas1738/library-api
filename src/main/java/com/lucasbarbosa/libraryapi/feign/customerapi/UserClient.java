package com.lucasbarbosa.libraryapi.feign.customerapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/** @author Lucas Barbosa on 31/07/2021 */
@FeignClient(name = "user", url = "${feign.client.user.url}")
public interface UserClient {

  @GetMapping("users/random_user")
  CustomerVO findCustomer();
}
