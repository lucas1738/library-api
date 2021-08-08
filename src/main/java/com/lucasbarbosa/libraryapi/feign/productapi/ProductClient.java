package com.lucasbarbosa.libraryapi.feign.productapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/** @author Lucas Barbosa on 31/07/2021 */
@FeignClient(name = "product", url = "${feign.client.product.url}")
public interface ProductClient {

  @GetMapping
  ProductVO findProduct();
}
