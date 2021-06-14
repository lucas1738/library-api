package com.lucasbarbosa.libraryapi.exception;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

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


    public Error(ResponseStatusException ex) {
        this.errors = Collections.singletonList(ex.getReason());
    }

}
