package com.lucasbarbosa.libraryapi;

import com.lucasbarbosa.libraryapi.api.BookController;
import com.lucasbarbosa.libraryapi.api.PurchaseController;
import com.lucasbarbosa.libraryapi.api.SellerController;
import com.lucasbarbosa.libraryapi.service.BookService;
import com.lucasbarbosa.libraryapi.service.PurchaseService;
import com.lucasbarbosa.libraryapi.service.SellerService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/** @author Lucas Barbosa on 31/10/2021 */
@Configuration
@ComponentScan(basePackages = {"com.lucasbarbosa.libraryapi.api"})
public class ContractTestConfiguration {

  @MockBean private BookController bookController;

  @MockBean private BookService bookService;

  @MockBean private PurchaseService purchaseService;

  @MockBean private PurchaseController purchaseController;

  @MockBean private SellerController sellerController;

  @MockBean private SellerService sellerService;
}
