package com.lucasbarbosa.libraryapi.model.enums;

import java.util.Arrays;

/**
 * @author Lucas Barbosa on 18/07/2021
 */
public enum LicenseTypeEnum {

    INDIVIDUAL, COMPANY;

    public static LicenseTypeEnum findByLiteral(String literal) {
        return Arrays.stream(LicenseTypeEnum.values())
                .filter(licenseType -> licenseType.toString().equalsIgnoreCase(literal))
                .findAny()
                .orElse(null);
    }

}
