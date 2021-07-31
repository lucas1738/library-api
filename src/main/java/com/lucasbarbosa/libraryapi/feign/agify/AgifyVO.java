package com.lucasbarbosa.libraryapi.feign.agify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** @author Lucas Barbosa on 31/07/2021 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgifyVO {

  private String name;

  private Integer age;

  private Long count;
}
