package com.lucasbarbosa.libraryapi.service.impl;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.model.dto.BookListRequest;
import com.lucasbarbosa.libraryapi.model.dto.BookRequest;
import com.lucasbarbosa.libraryapi.model.dto.BookResponse;
import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;
import com.lucasbarbosa.libraryapi.repository.BookRepository;
import com.lucasbarbosa.libraryapi.service.BookService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.getBookAsConst;
import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.getTitleAsConst;
import static com.lucasbarbosa.libraryapi.model.dto.BookListRequest.toDomain;
import static com.lucasbarbosa.libraryapi.repository.specification.BookSpecification.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
/** @author Lucas Barbosa on 27/06/2021 */
@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  @Transactional
  public Book createBook(BookRequest bookRequestDTO) {
    this.bookRepository
        .findByTitleIgnoreCase(bookRequestDTO.getTitle())
        .ifPresent(
            existingBook -> {
              throw new AttributeInUseException(
                  getBookAsConst(), getTitleAsConst(), existingBook.getTitle());
            });
    return this.bookRepository.save(BookRequest.toDomain(bookRequestDTO));
  }

  @Override
  @Transactional
  public List<Book> registerBookList(BookListRequest bookListRequest) {
    return this.bookRepository.saveAll(toDomain(bookListRequest));
  }

  @Override
  public List<BookResponse> fetchBooks(
      String isbn,
      String title,
      String author,
      String bookGenre,
      String initialDate,
      String finalDate,
      String bookMaxPages) {
    Specification<Book> specificationFilter =
        Specification.where(byAuthor(author))
            .and(byISBN(isbn))
            .and(byTitle(title))
            .and(byBookGenre(bookGenre))
            .and(byCreationDate(initialDate, finalDate))
            .and(byNumberPages(bookMaxPages));

    return this.bookRepository.findAll(specificationFilter).stream()
        .sorted(comparing(Book::getBookGenre).thenComparing(Book::getAuthor))
        .map(BookResponse::of)
        .collect(toList());
  }

  @Override
  public List<BookResponse> fetchBooksByGenre(BookGenreEnum bookGenreEnum) {
    Specification<Book> specificationFilter =
        Specification.where(byBookGenre(bookGenreEnum.toString()));
    return this.bookRepository.findAll(specificationFilter).stream()
        .sorted(comparing(Book::getAuthor).thenComparing(Book::getTitle))
        .map(BookResponse::of)
        .limit(3)
        .collect(toList());
  }
}
