package com.lucasbarbosa.libraryapi.driver.validation;

import com.lucasbarbosa.libraryapi.model.dto.SellerRequest;
import com.lucasbarbosa.libraryapi.model.enums.LicenseTypeEnum;
import com.lucasbarbosa.libraryapi.model.enums.SellerAssuranceMessageType;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.*;
import static com.lucasbarbosa.libraryapi.model.enums.LicenseTypeEnum.findByLiteral;

/** @author Lucas Barbosa on 18/07/2021 */
public class SellerAssuranceValidator
    implements ConstraintValidator<SellerAssurance, SellerRequest> {

  private static final String INDIVIDUAL_MANDATORY_FIELDS = "cpf and/or person_name";
  private static final String COMPANY_MANDATORY_FIELDS = "cnpj and/or company_name";

  @Override
  public void initialize(SellerAssurance constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(SellerRequest sellerRequestDTO, ConstraintValidatorContext context) {

    if (Optional.ofNullable(sellerRequestDTO).map(SellerRequest::getLicenseType).isEmpty()) {
      handleConstraintViolation(context, SellerAssuranceMessageType.PAYLOAD, StringUtils.EMPTY);
      return false;
    }

    if (!convertEnumToStringList(LicenseTypeEnum.class)
        .contains(sellerRequestDTO.getLicenseType().toUpperCase())) {
      handleConstraintViolation(
          context, SellerAssuranceMessageType.UNSUITABLE_ENUM_VALUES, StringUtils.EMPTY);
      return false;
    }

    var licenseType = findByLiteral(sellerRequestDTO.getLicenseType());

    if (LicenseTypeEnum.INDIVIDUAL.equals(licenseType)) {
      handleConstraintViolation(
          context, SellerAssuranceMessageType.MANDATORY_FIELDS, INDIVIDUAL_MANDATORY_FIELDS);
      return hasNulls(List.of(sellerRequestDTO.getCpf(), sellerRequestDTO.getPersonName()));
    }

    if (LicenseTypeEnum.COMPANY.equals(licenseType)) {
      handleConstraintViolation(
          context, SellerAssuranceMessageType.MANDATORY_FIELDS, COMPANY_MANDATORY_FIELDS);
      return hasNulls(List.of(sellerRequestDTO.getCnpj(), sellerRequestDTO.getCompanyName()));
    }

    return true;
  }

  private void handleConstraintViolation(
      ConstraintValidatorContext context,
      SellerAssuranceMessageType sellerAssuranceMessageType,
      String message) {
    context.disableDefaultConstraintViolation();
    context
        .buildConstraintViolationWithTemplate(
            createConstraintViolationMessage(sellerAssuranceMessageType, message))
        .addConstraintViolation();
  }

  private String createConstraintViolationMessage(
      SellerAssuranceMessageType sellerAssuranceMessageType, String message) {
    return String.format(
        getSellerAssuranceMessage(sellerAssuranceMessageType),
        Optional.ofNullable(message)
            .filter(StringUtils::isNotBlank)
            .map(String::valueOf)
            .orElse(StringUtils.EMPTY));
  }
}
