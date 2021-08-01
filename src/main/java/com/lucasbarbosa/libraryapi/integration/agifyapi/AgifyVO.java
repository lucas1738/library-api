package com.lucasbarbosa.libraryapi.integration.agifyapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/** @author Lucas Barbosa on 31/07/2021 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgifyVO {

  private String name;

  private Integer age;

  private Long count;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("customerAge", age)
        .toString();
  }
}
