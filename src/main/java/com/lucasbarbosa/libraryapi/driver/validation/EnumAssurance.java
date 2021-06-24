package com.lucasbarbosa.libraryapi.driver.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = EnumAssuranceValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
public @interface EnumAssurance {

  Class<? extends Enum<?>> enumClass();

  String field() default StringUtils.EMPTY;

  String message() default StringUtils.EMPTY;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
