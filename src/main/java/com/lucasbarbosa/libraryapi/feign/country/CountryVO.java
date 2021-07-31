package com.lucasbarbosa.libraryapi.feign.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/** @author Lucas Barbosa on 31/07/2021 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryVO {

  private String name;
  private List<String> topLevelDomain;
  private String alpha2Code;
  private String alpha3Code;
  private List<String> callingCodes;
  private String capital;
  private List<String> altSpellings;
  private String region;
  private String subregion;
  private Integer population;
  private List<Double> latlng;
  private String demonym;
  private Double area;
  private Double gini;
  private List<String> timezones;
  private List<String> borders;
  private String nativeName;
  private String numericCode;
  private List<CurrencyVO> currencies;
  private List<LanguageVO> languages;
  private TransalationVO translations;
  private String flag;
  private List<RegionalBlocVO> regionalBlocs;
  private String cioc;
}
