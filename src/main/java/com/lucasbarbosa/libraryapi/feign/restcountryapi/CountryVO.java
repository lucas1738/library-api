package com.lucasbarbosa.libraryapi.feign.restcountryapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
  private List<CurrencyDTO> currencies;
  private List<LanguageDTO> languages;
  private TranslationDTO translations;
  private String flag;
  private List<RegionalBlocDTO> regionalBlocs;
  private String cioc;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("countryName", name)
        .toString();
  }
}
