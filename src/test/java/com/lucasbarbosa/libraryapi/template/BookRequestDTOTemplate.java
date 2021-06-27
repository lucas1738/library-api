package com.lucasbarbosa.libraryapi.template;

import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;

public class BookRequestDTOTemplate {

  public static BookRequestDTO buildDefault() {
    return BookRequestDTO.builder()
        .bookGenre("ROMANCE")
        .title("Amazing adventures of Sam")
        .author("HP Lovecraft")
        .numberPages(500)
        .build();
  }
}
