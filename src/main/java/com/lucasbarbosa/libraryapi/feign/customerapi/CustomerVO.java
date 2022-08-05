package com.lucasbarbosa.libraryapi.feign.customerapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.byBlankSeparator;

/** @author Lucas Barbosa on 31/07/2021 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerVO {

  private Integer id;
  private String uid;
  private String password;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String avatar;
  private String gender;
  private String phoneNumber;
  private String socialInsuranceNumber;
  private String dateOfBirth;
  private EmploymentDTO employment;
  private AddressDTO address;
  private CreditCardDTO creditCardDTO;
  private SubscriptionDTO subscription;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .toString();
  }

  public static String buildFullName(CustomerVO customerVO) {
    return new StringBuilder()
        .append(customerVO.getFirstName())
        .append(byBlankSeparator())
        .append(customerVO.getLastName())
        .toString();
  }
}
