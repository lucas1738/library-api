package com.lucasbarbosa.libraryapi.service;

import com.lucasbarbosa.libraryapi.template.BookRequestDTOTemplate;
import com.lucasbarbosa.libraryapi.template.BookResponseDTOTemplate;
import com.lucasbarbosa.libraryapi.template.BookTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
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
@RunWith(MockitoJUnitRunner.class)
class BookServiceTest extends BookServiceTestSupport {

  @Test
  @DisplayName("Given book with existing title then throw AttributeInUseException")
  void givenBookWithExistingTitleThenThrowAttributeInUseException() {
    setUpTestProbes(
        BookTemplate.buildDefault(),
        BookRequestDTOTemplate.buildDefault(),
        BookResponseDTOTemplate.buildDefault());
    mockRepositoryFindByTitleIgnoreCase();
    assertThatAttributeInUseExceptionIsThrowed();
  }

  @Test
  @DisplayName("Given existing books in repository then fetch and map to BookResponseDTO")
  void givenExistingBooksInRepositoryThenFetchAndMapToBookResponseDTO() {
    setUpTestProbes(
        BookTemplate.buildDefault(),
        BookRequestDTOTemplate.buildDefault(),
        BookResponseDTOTemplate.buildDefault());
    mockRepositoryFindAll();
    assertThatListOfBookResponseDTOIsReturned();
  }
}
