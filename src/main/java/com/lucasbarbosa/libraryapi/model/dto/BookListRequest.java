package com.lucasbarbosa.libraryapi.model.dto;

import com.lucasbarbosa.libraryapi.model.entity.Book;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum.findByLiteral;
import static java.util.stream.Collectors.toList;

/** @author Lucas Barbosa on 28/08/2021 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookListRequest {

  private List<String> books;

  private String author;

  private String bookGenre;

  public static List<Book> toDomain(BookListRequest bookListRequest) {
    return assemble.apply(bookListRequest);
  }

  public static Book fromTitleAndGenre(String title, String author, String genre) {
    return Book.builder()
        .isbn(UUID.randomUUID().toString())
        .title(title)
        .author(author)
        .numberPages(500)
        .bookGenre(findByLiteral(genre))
        .build();
  }

  private static Function<BookListRequest, List<Book>> assemble =
      bookList ->
          bookList.getBooks().stream()
              .map(
                  book ->
                      BookListRequest.fromTitleAndGenre(
                          book, bookList.getAuthor(), bookList.getBookGenre()))
              .collect(toList());
}
