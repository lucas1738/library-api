package com.lucasbarbosa.libraryapi.feign.agifyapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** @author Lucas Barbosa on 31/07/2021 */
@FeignClient(name = "agify", url = "${feign.client.agify.url}")
public interface AgifyClient {

  @GetMapping
  AgifyVO findCustomerAge(@RequestParam(name = "name") String name);
}
