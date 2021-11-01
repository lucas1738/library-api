package com.lucasbarbosa.libraryapi.template;

import com.lucasbarbosa.libraryapi.model.dto.BookResponse;

/** @author Lucas Barbosa on 27/06/2021 */
public class BookResponseTemplate {

  public static BookResponse buildDefault() {
    return BookResponse.builder()
        .bookGenre("Action")
        .title("Amazing adventures of Sam")
        .author("HP Lovecraft")
        .numberPages(500)
        .build();
  }
}
