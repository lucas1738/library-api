package com.lucasbarbosa.libraryapi.unit;

import com.lucasbarbosa.libraryapi.template.BookRequestTemplate;
import com.lucasbarbosa.libraryapi.template.BookResponseTemplate;
import com.lucasbarbosa.libraryapi.template.BookTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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
        BookRequestTemplate.buildDefault(),
        BookResponseTemplate.buildDefault());
    mockRepositoryFindByTitleIgnoreCase();
    assertThatAttributeInUseExceptionIsThrowed();
  }

  @Test
  @DisplayName("Given existing books in repository then fetch and map to BookResponse")
  void givenExistingBooksInRepositoryThenFetchAndMapToBookResponse() {
    setUpTestProbes(
        BookTemplate.buildDefault(),
        BookRequestTemplate.buildDefault(),
        BookResponseTemplate.buildDefault());
    mockRepositoryFindAll();
    assertThatListOfBookResponseIsReturned();
  }
}
