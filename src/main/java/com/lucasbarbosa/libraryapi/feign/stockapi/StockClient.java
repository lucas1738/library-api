package com.lucasbarbosa.libraryapi.feign.stockapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/** @author Lucas Barbosa on 31/07/2021 */
@FeignClient(value = "${feign.client.stock.name}", url = "${feign.client.stock.url}")
public interface StockClient {

  @GetMapping("/random")
  List<Integer> getAvalaibleStock(
      @RequestParam(name = "min") int minStock, @RequestParam(name = "max") int maxStock);
}
