package com.lucasbarbosa.libraryapi.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

/** @author Lucas Barbosa on 10/07/2021 */
@Getter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SellerRequestDTO {

  private String cpf;

  private String cnpj;

  private String companyName;

  private String personName;

  private String licenseType;
}
