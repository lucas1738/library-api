package com.lucasbarbosa.libraryapi.api;

import com.lucasbarbosa.libraryapi.ContractTestConfiguration;
import com.lucasbarbosa.libraryapi.template.BookRequestTemplate;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.lucasbarbosa.libraryapi.template.BookRequestTemplate.*;

/** @author Lucas Barbosa on 03/07/2021 */
@ContextConfiguration(classes = {ContractTestConfiguration.class})
@RunWith(SpringRunner.class)
@WebMvcTest
public class BookContractTest extends BookContractTestSupport {

  @Test
  @DisplayName(
      "Given unsuitable bookGenre value, then return BAD REQUEST with field validation message")
  public void givenBookGenrerUnallowedValueAndExcessiveNumberPagesThenReturnBadRequest()
      throws Exception {

    writeAsJson(buildWithUnsuitableBookGenreAndExcessiveNumberPages());

    onBookRegisterFailValidateMultipleMessages(
        BOOK_GENRE_ENUM_VALIDATION_MESSAGE, NUMBER_PAGES_FIELD_MAX_VALUE_VALIDATION_MESSAGE);
  }

  @Test
  @DisplayName(
      "Given null title and empty author, then return BAD REQUEST with field validation message")
  public void givenNullTitleAndEmptyAuthorThenReturnBadRequest() throws Exception {

    writeAsJson(buildWithNullTitleAndEmptyAuthor());

    onBookRegisterFailValidateMultipleMessages(
        TITLE_FIELD_VALIDATION_MESSAGE, AUTHOR_FIELD_VALIDATION_MESSAGE);
  }

  @Test
  @DisplayName(
      "Given zero number of pages and empty title, then return BAD REQUEST with field validation message")
  public void givenZeroNumberOfPagesAndEmptyTitleThenReturnBadRequest() throws Exception {

    writeAsJson(buildWithZeroNumberPagesAndEmptyTitle());

    onBookRegisterFailValidateMultipleMessages(
        TITLE_FIELD_VALIDATION_MESSAGE, NUMBER_PAGES_FIELD_MIN_VALUE_VALIDATION_MESSAGE);
  }
}
