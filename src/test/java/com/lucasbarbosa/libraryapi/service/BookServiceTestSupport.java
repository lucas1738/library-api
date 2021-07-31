package com.lucasbarbosa.libraryapi.service;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;
import com.lucasbarbosa.libraryapi.model.dto.BookResponseDTO;
import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.model.entity.Book_;
import com.lucasbarbosa.libraryapi.repository.BookRepository;
import com.lucasbarbosa.libraryapi.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
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

  protected BookResponseDTO bookResponseDTO;

  @BeforeEach
  void initializeTest() {
    MockitoAnnotations.initMocks(this);
    service = new BookServiceImpl(repository);
  }

  protected void setUpTestProbes(
      Book book, BookRequestDTO bookRequestDTO, BookResponseDTO bookResponseDTO) {
    this.book = book;
    this.bookResponseDTO = bookResponseDTO;
    this.bookRequestDTO = bookRequestDTO;
  }

  protected void assertThatAttributeInUseExceptionIsThrowed() {
    assertThrows(
        AttributeInUseException.class,
        () -> {
          service.createBook(bookRequestDTO);
        });
  }

  private List<BookResponseDTO> retrieveMockedFetchBooks() {
    return service.fetchBooks(null, null, null, null, null, null, null);
  }

  protected void assertThatListOfBookResponseDTOIsReturned() {
    assertThat(retrieveMockedFetchBooks())
        .usingElementComparatorIgnoringFields(Book_.CREATION_DATE, Book_.UPDATE_DATE, Book_.ISBN)
        .isEqualTo(Collections.singletonList(bookResponseDTO));
  }

  protected void mockRepositoryFindAll() {
    when(repository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(book));
  }

  protected void mockRepositoryFindByTitleIgnoreCase() {
    when(repository.findByTitleIgnoreCase(anyString())).thenReturn(Optional.of(book));
  }
}
