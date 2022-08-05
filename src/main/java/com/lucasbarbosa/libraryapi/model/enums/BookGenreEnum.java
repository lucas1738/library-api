package com.lucasbarbosa.libraryapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparingInt;

/** @author Lucas Barbosa on 27/06/2021 */
@AllArgsConstructor
public enum BookGenreEnum {
  ACTION("A", 25),
  COMEDY("C", 15),
  DRAMA("D", 0),
  FANTASY("F", 65),
  HORROR("H", 75),
  MYSTERY("M", 35),
  ROMANCE("R", 80),
  THRILLER("T", 55);

  @Getter private String initial;
  @Getter private Integer recommendedAge;

  public static BookGenreEnum findByLiteral(String literal) {
    return Arrays.stream(BookGenreEnum.values())
        .filter(bookGenre -> bookGenre.toString().equalsIgnoreCase(literal))
        .findAny()
        .orElse(null);
  }

  public static BookGenreEnum findByInitial(String initial) {
    return Arrays.stream(BookGenreEnum.values())
        .filter(bookGenre -> bookGenre.getInitial().equalsIgnoreCase(initial))
        .findAny()
        .orElse(null);
  }

  public static BookGenreEnum obtainRecommendedAge(Integer age) {
    return Arrays.stream(BookGenreEnum.values())
        .filter(bookGenre -> bookGenre.getRecommendedAge() >= age)
        .min(comparingInt(BookGenreEnum::getRecommendedAge))
        .orElse(BookGenreEnum.ROMANCE);
  }
}
