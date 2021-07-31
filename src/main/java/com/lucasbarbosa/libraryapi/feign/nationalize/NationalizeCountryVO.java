package com.lucasbarbosa.libraryapi.feign.nationalize;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Lucas Barbosa on 31/07/2021
 */
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NationalizeCountryVO {

    private String countryId;

    private BigDecimal probability;
}
