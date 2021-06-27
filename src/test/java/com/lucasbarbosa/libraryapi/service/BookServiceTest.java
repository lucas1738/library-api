package com.lucasbarbosa.libraryapi.service;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;
import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.repository.BookRepository;
import com.lucasbarbosa.libraryapi.service.impl.BookServiceImpl;
import com.lucasbarbosa.libraryapi.template.BookRequestDTOTemplate;
import com.lucasbarbosa.libraryapi.template.BookTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookServiceTest {

  @Mock private BookRepository repository;

  @InjectMocks private final BookService service = new BookServiceImpl();

  private Book book;

  private BookRequestDTO bookRequestDTO;

  @BeforeEach
  public void setUp() {
    this.book = BookTemplate.buildDefault();
    this.bookRequestDTO = BookRequestDTOTemplate.buildDefault();
  }

  @Test
  void givenBookWithExistingTitleThenThrowAttributeInUseException() {
    when(repository.findByTitleIgnoreCase(Mockito.anyString())).thenReturn(Optional.of(book));

    assertThrows(
        AttributeInUseException.class,
        () -> {
          service.createBook(bookRequestDTO);
        });
  }
}
