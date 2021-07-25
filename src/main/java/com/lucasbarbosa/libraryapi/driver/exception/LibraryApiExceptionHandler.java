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

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.*;
import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.createEmptyStringArray;

/** @author Lucas Barbosa on 27/06/2021 */
@RestControllerAdvice
public class LibraryApiExceptionHandler {

  @Autowired private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationExceptions(
      HttpServletRequest request, MethodArgumentNotValidException ex) {
    var errorMessage = new ErrorMessage(ex.getBindingResult());
    return ErrorResponse.ofErrorMessage(request, errorMessage, BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationExceptions(
      HttpServletRequest request, ConstraintViolationException ex) {
    var errorMessage = new ErrorMessage(ex.getConstraintViolations());
    return ErrorResponse.ofErrorMessage(request, errorMessage, BAD_REQUEST);
  }

  @ExceptionHandler(AttributeInUseException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleAttributeInUseException(
      HttpServletRequest request, AttributeInUseException ex) {
    var errorMessage =
        new ErrorMessage(
            messageSource.getMessage(
                getAttributeInUseReference(),
                buildWithThreeParams(ex.getFirst(), ex.getSecond(), ex.getThird()),
                LocaleContextHolder.getLocale()));
    return ErrorResponse.ofErrorMessage(request, errorMessage, BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMessageNotReadableException(
      HttpServletRequest request, HttpMessageNotReadableException ex) {
    var errorMessage =
        new ErrorMessage(
            messageSource.getMessage(
                getMessageNotReadableReference(),
                createEmptyStringArray(),
                LocaleContextHolder.getLocale()));
    return ErrorResponse.ofErrorMessage(request, errorMessage, BAD_REQUEST);
  }
}
