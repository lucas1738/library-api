package com.lucasbarbosa.libraryapi.exception.custom;

public class AttributeInUseException extends BusinessException{

    public AttributeInUseException(String first, String second, String third) {
        super(first, second, third);
    }
}
