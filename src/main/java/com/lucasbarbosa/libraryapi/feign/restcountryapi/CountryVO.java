package com.lucasbarbosa.libraryapi.feign.restcountryapi;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Lucas Barbosa on 31/07/2021
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryVO {

  @JsonProperty("name")
  private CountryName countryName;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("countryName", getName())
        .toString();
  }

  public static CountryVO build(String country) {

    CountryName countryName = CountryName.builder()
        .common(country)
        .build();
    return CountryVO.builder().countryName(countryName).build();

  }

  public String getName() {
    return Optional.ofNullable(this.countryName).map(CountryName::getCommon).orElse(StringUtils.EMPTY);
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  private static class CountryName {

    private String common;

  }

}
