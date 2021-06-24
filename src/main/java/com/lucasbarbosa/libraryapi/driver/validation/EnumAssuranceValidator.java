package com.lucasbarbosa.libraryapi.driver.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.*;
import static java.util.stream.Collectors.toList;

public class EnumAssuranceValidator implements ConstraintValidator<EnumAssurance, CharSequence> {
  private List<String> allowedValues;
  private String field;

  @Override
  public void initialize(EnumAssurance constraintAnnotation) {
    allowedValues = convertEnumToStringStream(constraintAnnotation.enumClass()).collect(toList());
    field = constraintAnnotation.field();
  }

  private String createConstraintViolationMessage() {
    return String.format(
        getEnumAssuranceMessage(), field, joinStringStreamByComma(allowedValues.stream()));
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
    boolean isValid = allowedValues.contains(convertObjectToString(value).toUpperCase());

    if (!isValid) {
      handleConstraintViolation(context);
    }

    return isValid;
  }

  private void handleConstraintViolation(ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();
    context
        .buildConstraintViolationWithTemplate(createConstraintViolationMessage())
        .addConstraintViolation();
  }
}
