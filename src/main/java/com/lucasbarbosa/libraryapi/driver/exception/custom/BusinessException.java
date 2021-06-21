package com.lucasbarbosa.libraryapi.driver.exception.custom;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private String first;
    private String second;
    private String third;

    public BusinessException(String first) {
        this.first = first;
    }

    public BusinessException(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public BusinessException(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
