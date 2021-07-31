package com.lucasbarbosa.libraryapi.feign.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** @author Lucas Barbosa on 31/07/2021 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LanguageVO {

  private String iso639_1;
  private String iso639_2;
  private String name;
  private String nativeName;
}
