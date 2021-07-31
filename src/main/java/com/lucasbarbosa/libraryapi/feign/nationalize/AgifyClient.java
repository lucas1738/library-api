package com.lucasbarbosa.libraryapi.feign.nationalize;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** @author Lucas Barbosa on 31/07/2021 */
@FeignClient(value = "${feign.client.agify.name}", url = "${feign.client.agify.url}")
public interface AgifyClient {

  @GetMapping
  NationalizeVO findCustomerAge(@RequestParam(name = "name") String name);
}
