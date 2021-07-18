package com.lucasbarbosa.libraryapi.driver.validation;

import com.lucasbarbosa.libraryapi.model.dto.SellerRequestDTO;
import com.lucasbarbosa.libraryapi.model.enums.LicenseTypeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.*;
import static com.lucasbarbosa.libraryapi.model.enums.LicenseTypeEnum.findByLiteral;

/**
 * @author Lucas Barbosa on 18/07/2021
 */
public class SellerAssuranceValidator implements ConstraintValidator<SellerAssurance, SellerRequestDTO> {

    @Override
    public void initialize(SellerAssurance constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SellerRequestDTO sellerRequestDTO, ConstraintValidatorContext context) {

        if (Optional.ofNullable(sellerRequestDTO).map(SellerRequestDTO::getLicenseType).isEmpty()) {
            handleConstraintViolation(context, "payload", "different than null");
            return false;
        }

        if (!convertEnumToStringList(LicenseTypeEnum.class).contains(sellerRequestDTO.getLicenseType().toUpperCase())) {
            handleConstraintViolation(context, "license_type", "any of INDIVIDUAL, COMPANY");
            return false;
        }

        var licenseType = findByLiteral(sellerRequestDTO.getLicenseType());

        if (LicenseTypeEnum.INDIVIDUAL.equals(licenseType)) {
            handleConstraintViolation(context, "cpf and person_name", "filled");
            return hasNulls(List.of(sellerRequestDTO.getCpf(), sellerRequestDTO.getPersonName()));
        }

        if (LicenseTypeEnum.COMPANY.equals(licenseType)) {
            handleConstraintViolation(context, "cnpj and company_name", "filled");
            return hasNulls(List.of(sellerRequestDTO.getCnpj(), sellerRequestDTO.getCompanyName()));
        }


        return true;
    }

    private void handleConstraintViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();
        context
                .buildConstraintViolationWithTemplate(createConstraintViolationMessage(field, message))
                .addConstraintViolation();
    }

    private String createConstraintViolationMessage(String field, String message) {
        return String.format(getSellerAssuranceMessage(), field, message);
    }


}
