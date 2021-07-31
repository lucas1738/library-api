package com.lucasbarbosa.libraryapi.feign.restcountry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransalationVO {

  private String de;
  private String es;
  private String fr;
  private String ja;
  private String it;
  private String br;
  private String pt;
  private String nl;
  private String hr;
  private String fa;
}
