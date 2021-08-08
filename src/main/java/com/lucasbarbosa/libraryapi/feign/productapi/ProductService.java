package com.lucasbarbosa.libraryapi.feign.productapi;

import com.lucasbarbosa.libraryapi.driver.exception.custom.FeignIntegrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.retrieveExceptionClassName;

/** @author Lucas Barbosa on 01/08/2021 */
@Service
@Slf4j
public class ProductService {

  private final ProductClient productClient;

  public ProductService(ProductClient productClient) {
    this.productClient = productClient;
  }

  public Optional<BigDecimal> fetchProductPrice() {

    try {
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
    } catch (Exception exception) {
      log.error(
          "m=fetchProductPrice exception={} exceptionName={}",
          exception,
          retrieveExceptionClassName(exception));
      throw new FeignIntegrationException(this.getClass().getSimpleName());
    }
  }
}
