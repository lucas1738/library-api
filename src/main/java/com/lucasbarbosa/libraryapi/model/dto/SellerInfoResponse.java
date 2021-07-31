package com.lucasbarbosa.libraryapi.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/** @author Lucas Barbosa on 25/07/2021 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Data transfer object for returning seller token information")
public class SellerInfoResponse {

  @ApiModelProperty(
      value = "Information either about token key or its validity",
      example = "The token is gdhd4321, The token is valid for 42 days")
  private String info;
}
