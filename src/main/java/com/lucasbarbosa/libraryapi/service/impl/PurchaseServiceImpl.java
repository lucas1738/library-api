package com.lucasbarbosa.libraryapi.service.impl;

import com.lucasbarbosa.libraryapi.feign.productapi.ProductService;
import com.lucasbarbosa.libraryapi.feign.stockapi.StockService;
import com.lucasbarbosa.libraryapi.service.PurchaseService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.areAllPresent;
import static java.util.Optional.*;
import static java.util.concurrent.CompletableFuture.supplyAsync;

/** @author Lucas Barbosa on 07/08/2021 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

  private final StockService stockService;

  private final ProductService productService;

  public PurchaseServiceImpl(StockService stockService, ProductService productService) {
    this.stockService = stockService;
    this.productService = productService;
  }

  @SneakyThrows
  @Override
  public Optional<BigDecimal> obtainPurchaseFinalPrice() {
    CompletableFuture<BigDecimal> priceFuture =
        supplyAsync(() -> stockService.retrieveClient(empty()))
            .completeOnTimeout(stockService.value(), stockService.timeout(), stockService.timeUnit())
            .thenCombine(
                supplyAsync(() -> productService.retrieveClient(empty()))
                    .completeOnTimeout(productService.value(), productService.timeout(), productService.timeUnit()),
                (stock, price) ->
                    areAllPresent(List.of(stock, price))
                        ? stock.get().multiply(price.get())
                        : null);

    return ofNullable(priceFuture.get()).filter(Predicate.not(ObjectUtils::isEmpty));
  }
}
