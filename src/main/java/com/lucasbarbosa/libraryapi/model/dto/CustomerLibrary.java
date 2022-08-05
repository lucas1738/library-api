package com.lucasbarbosa.libraryapi.model.dto;

import com.lucasbarbosa.libraryapi.feign.customerapi.CustomerVO;
import lombok.*;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.Optional;

/** @author Lucas Barbosa on 28/08/2021 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerLibrary {

  private String name;

  private String age;

  private String country;

  public static CustomerLibrary of(
      Optional<CustomerVO> customerVO, Optional<String> country, Optional<Integer> age) {
    return CustomerLibrary.builder()
        .age(age.map(Objects::toString).orElse(StringUtils.EMPTY))
        .country(country.orElse(StringUtils.EMPTY))
        .name(customerVO.map(CustomerVO::buildFullName).orElse(StringUtils.EMPTY))
        .build();
  }
}
