package com.lucasbarbosa.libraryapi.template;

import com.lucasbarbosa.libraryapi.model.dto.BookRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/** @author Lucas Barbosa on 27/06/2021 */
public class BookRequestTemplate {

  public static BookRequest buildDefault() {
    return BookRequest.builder()
        .bookGenre("ACTION")
        .title("Amazing adventures of Sam")
        .author("HP Lovecraft")
        .numberPages(500)
        .build();
  }

  public static BookRequest buildWithUnsuitableBookGenreAndExcessiveNumberPages() {
    return BookRequest.builder()
        .bookGenre(UUID.randomUUID().toString())
        .title("Amazing adventures of Sam")
        .author("HP Lovecraft")
        .numberPages(1500)
        .build();
  }

  public static BookRequest buildWithNullTitleAndEmptyAuthor() {
    return BookRequest.builder()
        .bookGenre(UUID.randomUUID().toString())
        .title(null)
        .author(StringUtils.EMPTY)
        .numberPages(500)
        .build();
  }

  public static BookRequest buildWithZeroNumberPagesAndEmptyTitle() {
    return BookRequest.builder()
        .bookGenre("action")
        .title(StringUtils.EMPTY)
        .author("HP Lovecraft")
        .numberPages(0)
        .build();
  }
}
