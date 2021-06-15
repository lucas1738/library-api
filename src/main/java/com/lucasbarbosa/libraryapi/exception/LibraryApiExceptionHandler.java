package com.lucasbarbosa.libraryapi.exception;

import com.lucasbarbosa.libraryapi.exception.custom.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class LibraryApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleValidationExceptions(MethodArgumentNotValidException ex){
        return new Error(ex.getBindingResult());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleBusinessExceptions(BusinessException ex){
        return new Error(messageSource.getMessage("attribute.in.use", extractExceptionParams(Arrays.asList(ex.getFirst(), ex.getSecond(), ex.getThird())), LocaleContextHolder.getLocale()));
    }

    private Object[] extractExceptionParams(List<String> params) {
        return params.toArray();
    }

}
