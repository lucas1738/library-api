package com.lucasbarbosa.libraryapi.feign.nationalize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** @author Lucas Barbosa on 31/07/2021 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NationalizeVO {

  private String name;

}
