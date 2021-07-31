package com.lucasbarbosa.libraryapi.model.dto;

import com.lucasbarbosa.libraryapi.model.entity.Book;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

import static com.lucasbarbosa.libraryapi.driver.utils.DateUtils.formatDateTime;
/** @author Lucas Barbosa on 27/06/2021 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

  private String isbn;

  private String title;

  private String author;

  private int numberPages;

  private String bookGenre;

  private String creationDate;

  private String updateDate;

  public static BookResponse of(Book book) {
    return disassemble.apply(book);
  }

  private static Function<Book, BookResponse> disassemble =
      book ->
          BookResponse.builder()
              .isbn(book.getIsbn())
              .title(book.getTitle())
              .author(book.getAuthor())
              .bookGenre(StringUtils.capitalize(book.getBookGenre().toString().toLowerCase()))
              .numberPages(book.getNumberPages())
              .creationDate(formatDateTime(book.getCreationDate()))
              .updateDate(formatDateTime(book.getUpdateDate()))
              .build();
}
