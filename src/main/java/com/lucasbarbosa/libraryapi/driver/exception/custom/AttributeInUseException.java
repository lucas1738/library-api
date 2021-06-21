package com.lucasbarbosa.libraryapi.driver.exception.custom;

public class AttributeInUseException extends BusinessException{

    public AttributeInUseException(String first, String second, String third) {
        super(first, second, third);
    }
}
