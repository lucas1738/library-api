package com.lucasbarbosa.libraryapi.driver.exception;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Error {

    @Getter
    private final List<String> errors;

    public Error(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).forEach(this.errors::add);
    }

    public Error(String message) {
        this.errors = Collections.singletonList(message);
    }
}
