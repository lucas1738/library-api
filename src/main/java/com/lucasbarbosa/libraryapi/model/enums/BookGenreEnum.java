package com.lucasbarbosa.libraryapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
/** @author Lucas Barbosa on 27/06/2021 */
@AllArgsConstructor
public enum BookGenreEnum {
  ACTION("A"),
  COMEDY("C"),
  DRAMA("D"),
  FANTASY("F"),
  HORROR("H"),
  MYSTERY("M"),
  ROMANCE("R"),
  THRILLER("T");

  @Getter private String initial;

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
}
