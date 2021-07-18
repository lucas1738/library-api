package com.lucasbarbosa.libraryapi.driver.utils;

import com.lucasbarbosa.libraryapi.model.enums.SellerAssuranceMessageType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lucas Barbosa on 27/06/2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LibraryUtils {

    private static final String COMMA = ", ";
    public static final int ONE = 1;
    public static final int ONE_THOUSAND = 1000;
    private static final String ENUM_ASSURANCE_MESSAGE = "field %s must be any of %s";
    private static final String SELLER_ASSURANCE_MESSAGE = "field license_type must be any of INDIVIDUAL, COMPANY";
    private static final String NOT_NULL_MESSAGE = "payload must not be null";
    private static final String MANDATORY_MESSAGE = "%s are mandatory";

    public static String byComma() {
        return COMMA;
    }

    public static String getEnumAssuranceMessage() {
        return ENUM_ASSURANCE_MESSAGE;
    }

    public static String getSellerAssuranceMessage(SellerAssuranceMessageType sellerAssuranceMessageType) {
        switch (sellerAssuranceMessageType) {
            case PAYLOAD:
                return NOT_NULL_MESSAGE;
            case MANDATORY_FIELDS:
                return MANDATORY_MESSAGE;
            case UNSUITABLE_ENUM_VALUES:
                return SELLER_ASSURANCE_MESSAGE;
            default:
                return StringUtils.EMPTY;
        }
    }

    public static String[] createEmptyStringArray() {
        return new String[]{};
    }

    public static String convertObjectToString(Object object) {
        return Optional.ofNullable(object).map(Objects::toString).orElse(StringUtils.EMPTY);
    }

    public static List<String> convertEnumToStringList(Class<? extends Enum<?>> enumeration) {
        return Stream.of(enumeration.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    public static String joinStringListByComma(List<String> stringList) {
        return String.join(COMMA, stringList);
    }

    public static String generateSpecificationQueryPattern(String string) {
        return "%" + string.toUpperCase() + "%";
    }

    public static boolean hasNulls(List<Object> objects) {
        return objects.stream().allMatch((Predicate.not(ObjectUtils::isEmpty)));
    }
}
