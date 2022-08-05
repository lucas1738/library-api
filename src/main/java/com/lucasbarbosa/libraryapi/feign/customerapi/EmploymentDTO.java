package com.lucasbarbosa.libraryapi.feign.customerapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmploymentDTO {
  private String plan;
  private String status;
  private String paymentMethod;
  private String term;
}
