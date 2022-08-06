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
public class ProductService implements IntegrationClient<BigDecimal, ProductService> {

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
              return Optional.of(BigDecimal.TEN);
            });
  }

  @Override
  public Class<ProductService> identify() {
    return ProductService.class;
  }

  @Override
  public Optional<BigDecimal> value() {
    return Optional.of(BigDecimal.TEN);
  }
}
