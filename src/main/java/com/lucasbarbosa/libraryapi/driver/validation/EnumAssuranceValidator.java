package com.lucasbarbosa.libraryapi.driver.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class EnumAssuranceValidator implements ConstraintValidator<EnumAssurance, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumAssurance constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}
