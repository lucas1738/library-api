package com.lucasbarbosa.libraryapi.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.lucasbarbosa.libraryapi.driver.validation.SellerAssurance;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Lucas Barbosa on 10/07/2021
 */
@Getter
@Builder
@SellerAssurance
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SellerRequestDTO {

    private String cpf;

    private String cnpj;

    private String companyName;

    private String personName;

    private String licenseType;

}
