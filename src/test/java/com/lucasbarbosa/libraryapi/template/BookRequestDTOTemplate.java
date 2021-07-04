package com.lucasbarbosa.libraryapi.template;

import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/** @author Lucas Barbosa on 27/06/2021 */
public class BookRequestDTOTemplate {

  public static BookRequestDTO buildDefault() {
    return BookRequestDTO.builder()
        .bookGenre("ACTION")
        .title("Amazing adventures of Sam")
        .author("HP Lovecraft")
        .numberPages(500)
        .build();
  }

  public static BookRequestDTO buildWithUnsuitableBookGenreAndExcessiveNumberPages() {
    return BookRequestDTO.builder()
        .bookGenre(UUID.randomUUID().toString())
        .title("Amazing adventures of Sam")
        .author("HP Lovecraft")
        .numberPages(1500)
        .build();
  }

  public static BookRequestDTO buildWithNullTitleAndEmptyAuthor() {
    return BookRequestDTO.builder()
        .bookGenre(UUID.randomUUID().toString())
        .title(null)
        .author(StringUtils.EMPTY)
        .numberPages(500)
        .build();
  }

  public static BookRequestDTO buildWithZeroNumberPagesAndEmptyTitle() {
    return BookRequestDTO.builder()
        .bookGenre("action")
        .title(StringUtils.EMPTY)
        .author("HP Lovecraft")
        .numberPages(0)
        .build();
  }
}
