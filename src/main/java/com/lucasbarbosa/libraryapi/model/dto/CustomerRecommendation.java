package com.lucasbarbosa.libraryapi.model.dto;

import lombok.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/** @author Lucas Barbosa on 28/08/2021 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRecommendation {

  private CustomerLibrary customer;

  private List<String> recommendation;

  public static CustomerRecommendation of(
      Optional<CustomerLibrary> customerLibrary, List<BookResponse> bookResponse) {
    return CustomerRecommendation.builder()
        .customer(customerLibrary.orElse(null))
        .recommendation(bookResponse.stream().map(BookResponse::getTitle).collect(toList()))
        .build();
  }
}
