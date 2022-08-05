package com.lucasbarbosa.libraryapi.feign.nationalizeapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** @author Lucas Barbosa on 31/07/2021 */
@FeignClient(name = "nationalize", url = "${feign.client.nationalize.url}")
public interface NationalizeClient {

  @GetMapping
  NationalizeVO findCustomerCountry(@RequestParam(name = "name") String name);
}
