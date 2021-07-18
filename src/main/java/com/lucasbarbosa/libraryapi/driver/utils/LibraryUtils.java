package com.lucasbarbosa.libraryapi.driver.utils;

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
    private static final String SELLER_ASSURANCE_MESSAGE = "%s must be %s";

    public static String byComma() {
        return COMMA;
    }

    public static String getEnumAssuranceMessage() {
        return ENUM_ASSURANCE_MESSAGE;
    }

    public static String getSellerAssuranceMessage() {
        return SELLER_ASSURANCE_MESSAGE;
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
