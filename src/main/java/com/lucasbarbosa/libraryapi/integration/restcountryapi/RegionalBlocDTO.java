package com.lucasbarbosa.libraryapi.integration.restcountryapi;

import lombok.Getter;

import java.util.List;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
public class RegionalBlocDTO {

  private String acronym;
  private String name;
  private List<String> otherAcronyms;
  private List<String> otherNames;
}
