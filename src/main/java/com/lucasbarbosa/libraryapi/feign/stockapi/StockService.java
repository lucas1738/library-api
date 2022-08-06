package com.lucasbarbosa.libraryapi.feign.stockapi;

import com.lucasbarbosa.libraryapi.feign.IntegrationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/** @author Lucas Barbosa on 01/08/2021 */
@Service
@Slf4j
public class StockService implements IntegrationClient<BigDecimal, StockService> {

  private final StockClient stockClient;

  @Value("${stock.minStock}")
  private Integer minStock;

  @Value("${stock.maxStock}")
  private Integer maxStock;

  public StockService(StockClient stockClient) {
    this.stockClient = stockClient;
  }

  @Override
  public Optional<BigDecimal> writeClientIntegration(Optional<Map<String, Object>> params) {
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
              return Optional.of(BigDecimal.ONE);
            });
  }

  @Override
  public Class<StockService> identify() {
    return StockService.class;
  }

  @Override
  public Optional<BigDecimal> value() {
    return Optional.of(BigDecimal.ONE);
  }
}
