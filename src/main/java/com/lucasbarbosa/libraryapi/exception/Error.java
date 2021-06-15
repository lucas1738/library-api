package com.lucasbarbosa.libraryapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public Error(String message) {
        this.errors = Collections.singletonList(message);
    }
}
