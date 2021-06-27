package com.lucasbarbosa.libraryapi.repository.specification;

import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.buildMaxLocalDateTime;
import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.buildMinLocalDateTime;

public class BookSpecification extends BookSpecificationSupport {

  public static Specification<Book> byTitle(String title) {
    return Optional.ofNullable(title).map(BookSpecificationSupport::setUpTitle).orElse(null);
  }

  public static Specification<Book> byAuthor(String author) {
    return Optional.ofNullable(author).map(BookSpecificationSupport::setUpAuthor).orElse(null);
  }

  public static Specification<Book> byBookGenre(String bookGenre) {
    return Optional.ofNullable(BookGenreEnum.findByLiteral(bookGenre))
        .map(BookSpecificationSupport::setUpBookGenre)
        .orElse(null);
  }

  public static Specification<Book> byCreationDate(String initialDate, String finalDate) {
    LocalDate start = Optional.ofNullable(initialDate).map(LocalDate::parse).orElse(null);
    LocalDate end = Optional.ofNullable(finalDate).map(LocalDate::parse).orElse(null);

    LocalDateTime startTime =
        Optional.ofNullable(start)
            .map(item -> LocalDateTime.of(item, LocalTime.MIN))
            .orElse(buildMinLocalDateTime());
    LocalDateTime endTime =
        Optional.ofNullable(end)
            .map(item -> LocalDateTime.of(item, LocalTime.MAX))
            .orElse(buildMaxLocalDateTime());

    return BookSpecificationSupport.setUpDateCreation(startTime, endTime);
  }
}
