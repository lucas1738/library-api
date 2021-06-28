package com.lucasbarbosa.libraryapi.template;

import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;
/** @author Lucas Barbosa on 27/06/2021 */
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
