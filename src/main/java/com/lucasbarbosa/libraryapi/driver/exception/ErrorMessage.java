package com.lucasbarbosa.libraryapi.driver.exception;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/** @author Lucas Barbosa on 27/06/2021 */
public class ErrorMessage {

  @Getter private final List<String> errors;

  public ErrorMessage(BindingResult bindingResult) {
    this.errors = new ArrayList<>();
    bindingResult.getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .forEach(this.errors::add);
  }

  public ErrorMessage(Set<ConstraintViolation<?>> constraintViolations) {
    this.errors = new ArrayList<>();
    constraintViolations.stream().map(ConstraintViolation::getMessage).forEach(this.errors::add);
  }

  public ErrorMessage(String message) {
    this.errors = Collections.singletonList(message);
  }
}
