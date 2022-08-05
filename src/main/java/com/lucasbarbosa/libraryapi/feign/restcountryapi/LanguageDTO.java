package com.lucasbarbosa.libraryapi.feign.restcountryapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
public class LanguageDTO {

  @JsonProperty("iso639_1")
  private String iso1;

  @JsonProperty("iso639_2")
  private String iso2;

  private String name;
  private String nativeName;
}
