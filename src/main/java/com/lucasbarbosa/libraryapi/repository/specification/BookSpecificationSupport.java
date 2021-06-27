package com.lucasbarbosa.libraryapi.repository.specification;

import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class BookSpecificationSupport {

  protected static Specification<Book> setUpTitle(String title) {
    return (root, query, cb) ->
        cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
  }

  protected static Specification<Book> setUpAuthor(String author) {
    return (root, query, cb) ->
        cb.like(cb.upper(root.get("author")), "%" + author.toUpperCase() + "%");
  }

  protected static Specification<Book> setUpBookGenre(BookGenreEnum bookGenreEnum) {
    return (root, query, cb) -> (cb.equal(root.get("bookGenre"), bookGenreEnum));
  }

  public static Specification<Book> setUpDateCreation(
      LocalDateTime initialDate, LocalDateTime finalDate) {
    return (root, query, cb) -> cb.between(root.get("creationDate"), initialDate, finalDate  );
  }
}
