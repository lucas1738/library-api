package com.lucasbarbosa.libraryapi.exception.custom;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String first;
    private String second;
    private String third;

}
