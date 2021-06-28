package com.lucasbarbosa.libraryapi.service;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;
import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.repository.BookRepository;
import com.lucasbarbosa.libraryapi.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/*
If this project adopted the use of autowired annotation instead of constructors,
it would be necessary to do the following changes refering to dependency injection:

@ExtendWith(SpringExtension.class)
class BookServiceTest {
  @Mock private BookRepository repository;
  @InjectMocks private final BookService service = new BookServiceImpl();
  */

/** @author Lucas Barbosa on 27/06/2021 */
class BookServiceTestSupport {

  @Mock protected BookRepository repository;

  @Mock protected BookService service;

  protected Book book;

  protected BookRequestDTO bookRequestDTO;

  @BeforeEach
  void initializeTest() {
    MockitoAnnotations.openMocks(this);
    service = new BookServiceImpl(repository);
  }

  protected void setUpScenario(Book book, BookRequestDTO bookRequestDTO) {
    this.book = book;
    this.bookRequestDTO = bookRequestDTO;
  }

  protected void assertThatAttributeInUseExceptionIsThrowed() {
    assertThrows(
        AttributeInUseException.class,
        () -> {
          service.createBook(bookRequestDTO);
        });
  }

  protected void mockTestSubject() {
    when(repository.findByTitleIgnoreCase(Mockito.anyString())).thenReturn(Optional.of(book));
  }
}
