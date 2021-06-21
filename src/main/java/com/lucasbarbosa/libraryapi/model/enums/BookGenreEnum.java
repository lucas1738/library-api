package com.lucasbarbosa.libraryapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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

  public static BookGenreEnum findByLiteral(String initial) {
    return Arrays.stream(BookGenreEnum.values())
        .filter(bookGenre -> initial.equalsIgnoreCase(bookGenre.toString()))
        .findAny()
        .orElse(null);
  }

  public static BookGenreEnum findByInitial(String initial) {
    return Arrays.stream(BookGenreEnum.values())
        .filter(bookGenre -> initial.equalsIgnoreCase(bookGenre.getInitial()))
        .findAny()
        .orElse(null);
  }
}
