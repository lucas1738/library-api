package com.lucasbarbosa.libraryapi.template;

import com.lucasbarbosa.libraryapi.model.dto.BookResponseDTO;

/** @author Lucas Barbosa on 27/06/2021 */
public class BookResponseDTOTemplate {

  public static BookResponseDTO buildDefault() {
    return BookResponseDTO.builder()
        .bookGenre("Action")
        .title("Amazing adventures of Sam")
        .author("HP Lovecraft")
        .numberPages(500)
        .build();
  }
}
