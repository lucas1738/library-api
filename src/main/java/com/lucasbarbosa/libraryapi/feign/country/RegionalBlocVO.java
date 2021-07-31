package com.lucasbarbosa.libraryapi.feign.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionalBlocVO {

  private String acronym;
  private String name;
  private List<String> otherAcronyms;
  private List<String> otherNames;
}
