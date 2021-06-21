package com.lucasbarbosa.libraryapi.driver.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.stream.Collectors.joining;

@Constraint(validatedBy = EnumAssuranceValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
public @interface EnumAssurance {
  Class<? extends Enum<?>> enumClass();

  String field();

  String[] allowedValues();

  String message() default "{field} must be any of {allowedValues}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
