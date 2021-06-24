package com.lucasbarbosa.libraryapi.driver.exception;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.*;
import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.createEmptyStringArray;

@RestControllerAdvice
public class LibraryApiExceptionHandler {

  @Autowired private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Error handleValidationExceptions(MethodArgumentNotValidException ex) {
    return new Error(ex.getBindingResult());
  }

  @ExceptionHandler(AttributeInUseException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Error handleAttributeInUseException(AttributeInUseException ex) {
    return new Error(
        messageSource.getMessage(
            getAttributeInUseReference(),
            buildWithThreeParams(ex.getFirst(), ex.getSecond(), ex.getThird()),
            LocaleContextHolder.getLocale()));
  }
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Error handleMessageNotReadableException(HttpMessageNotReadableException ex) {
    return new Error(
        messageSource.getMessage(
            getMessageNotReadableReference(),
                createEmptyStringArray(),
            LocaleContextHolder.getLocale()));
  }
}
