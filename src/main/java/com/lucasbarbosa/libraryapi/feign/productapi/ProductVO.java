package com.lucasbarbosa.libraryapi.feign.productapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;

/** @author Lucas Barbosa on 31/07/2021 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductVO {

  private String id;
  private String uid;
  private Long number;
  private String leadingZeroNumber;
  private BigDecimal decimal;
  private Double normal;
  private String hexadecimal;
  private Double positive;
  private Double negative;
  private Integer nonZeroNumber;
  private Integer digit;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("price", decimal)
        .toString();
  }
}
