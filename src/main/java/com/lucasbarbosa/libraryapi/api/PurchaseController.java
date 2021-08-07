package com.lucasbarbosa.libraryapi.api;

import com.lucasbarbosa.libraryapi.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

/** @author Lucas Barbosa on 07/08/2021 */
@RestController
@RequestMapping("/purchase")
@Api(tags = "Purchase")
public class PurchaseController {

  private final PurchaseService purchaseService;

  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @SneakyThrows
  @GetMapping("/price")
  @ApiOperation(value = "Resource in charge of retrieving the final purchase price")
  public ResponseEntity<BigDecimal> getFinalPurchasePrice() {
    Optional<BigDecimal> purchasePrice = purchaseService.obtainPurchaseFinalPrice();

    return purchasePrice.map(ResponseEntity::ok).orElse(ResponseEntity.ok(BigDecimal.ZERO));
  }
}
