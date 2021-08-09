package com.lucasbarbosa.libraryapi.feign.productapi;

import com.lucasbarbosa.libraryapi.feign.IntegrationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/** @author Lucas Barbosa on 01/08/2021 */
@Service
@Slf4j
public class ProductService implements IntegrationClient<BigDecimal> {

  private final ProductClient productClient;

  public ProductService(ProductClient productClient) {
    this.productClient = productClient;
  }

  @Override
  public Optional<BigDecimal> writeClientIntegration(Optional<Map<String, Object>> params) {
    return Optional.ofNullable(productClient.findProduct())
        .map(
            product -> {
              log.info("m=fetchProductPrice product={}", product);
              return product.getDecimal();
            })
        .or(
            () -> {
              log.warn("m=fetchProductPrice failed");
              return Optional.empty();
            });
  }

  @Override
  public Class identify() {
    return this.getClass();
  }
}
