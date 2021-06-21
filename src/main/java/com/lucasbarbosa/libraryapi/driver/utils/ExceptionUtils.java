package com.lucasbarbosa.libraryapi.driver.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtils {

    private static final String BOOK = "book";
    private static final String TITLE = "title";

    private static final String ATTRIBUTE_IN_USE = "attribute.in.use";

    public static String getTitleAsConst(){
        return TITLE;
    }

    public static String getBookAsConst(){
        return BOOK;
    }

    public static String getAttributeInUseMessageReference(){
        return ATTRIBUTE_IN_USE;
    }

    public static Object[] buildWithSingleParam(String first){
        return Collections.singletonList(first).toArray();
    }
    public static Object[] buildWithTwoParam(String first, String second){
        return Arrays.asList(first, second).toArray();
    }
    public static Object[] buildWithThreeParams(String first, String second, String third){
        return Arrays.asList(first, second, third).toArray();
    }
}
