package com.lucasbarbosa.libraryapi.service;

import java.math.BigDecimal;
import java.util.Optional;

/** @author Lucas Barbosa on 07/08/2021 */
public interface PurchaseService {

  Optional<BigDecimal> obtainPurchaseFinalPrice();

}
