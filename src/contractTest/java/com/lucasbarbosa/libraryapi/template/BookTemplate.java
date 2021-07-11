package com.lucasbarbosa.libraryapi.template;

import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;

import java.time.LocalDateTime;
/** @author Lucas Barbosa on 27/06/2021 */
public class BookTemplate {

  public static Book buildDefault() {
    return Book.builder()
        .bookGenre(BookGenreEnum.ACTION)
        .title("Amazing adventures of Sam")
        .author("HP Lovecraft")
        .numberPages(500)
        .build();
  }
}
