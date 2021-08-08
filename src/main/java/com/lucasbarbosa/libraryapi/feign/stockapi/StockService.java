package com.lucasbarbosa.libraryapi.feign.stockapi;

import com.lucasbarbosa.libraryapi.driver.exception.custom.FeignIntegrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.retrieveExceptionClassName;

/** @author Lucas Barbosa on 01/08/2021 */
@Service
@Slf4j
public class StockService {

  private final StockClient stockClient;

  @Value("${stock.minStock}")
  private Integer minStock;

  @Value("${stock.maxStock}")
  private Integer maxStock;

  public StockService(StockClient stockClient) {
    this.stockClient = stockClient;
  }

  public Optional<BigDecimal> fetchAvailableStock() {
    try {
      return Optional.ofNullable(stockClient.getAvalaibleStock(minStock, maxStock))
          .flatMap(stockList -> stockList.stream().filter(Objects::nonNull).findFirst())
          .map(
              stock -> {
                log.info("m=fetchAvailableStock stock={}", stock);
                return new BigDecimal(stock);
              })
          .or(
              () -> {
                log.warn("m=fetchAvailableStock failed");
                return Optional.empty();
              });

    } catch (Exception exception) {
      log.error("m=fetchAvailableStock exceptionName={}", retrieveExceptionClassName(exception));
      throw new FeignIntegrationException(this.getClass().getSimpleName());
    }
  }
}
