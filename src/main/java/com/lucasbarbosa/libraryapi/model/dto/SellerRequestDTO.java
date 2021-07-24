package com.lucasbarbosa.libraryapi.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.lucasbarbosa.libraryapi.driver.validation.SellerAssurance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Lucas Barbosa on 10/07/2021
 */
@Getter
@Builder
@SellerAssurance
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ApiModel(value = "SellerRequestDTO", description = "Data transfer object for sellers registration and token acquirence")
public class SellerRequestDTO {

    @ApiModelProperty(value = "Seller CPF", example = "021.423.543-65")
    private String cpf;

    @ApiModelProperty(value = "Seller company CNPJ", example = "72.871.272/0001-78")
    private String cnpj;

    @ApiModelProperty(value = "Seller company name", example = "Cyberpunk SA")
    private String companyName;

    @ApiModelProperty(value = "Seller full name", example = "Tim Peterson Morales")
    private String personName;

    @ApiModelProperty(value = "Seller license type", example = "INDIVIDUAL", allowableValues = "INDIVIDUAL, COMPANY")
    private String licenseType;

}
