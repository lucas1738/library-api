package com.lucasbarbosa.libraryapi.controller;

import com.lucasbarbosa.libraryapi.template.BookRequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** @author Lucas Barbosa on 03/07/2021 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookContractTest extends BookContractTestSupport {

  @Test
  @DisplayName(
      "Given unsuitable bookGenre value, then return BAD REQUEST with field validation message")
  public void givenBookGenrerUnallowedValueAndExcessiveNumberPagesThenReturnBadRequest() throws Exception {

    writeAsJson(BookRequestTemplate.buildWithUnsuitableBookGenreAndExcessiveNumberPages());

    onBookRegisterFailValidateMultipleMessages(
        BOOK_GENRE_ENUM_VALIDATION_MESSAGE, NUMBER_PAGES_FIELD_MAX_VALUE_VALIDATION_MESSAGE);
  }

  @Test
  @DisplayName(
      "Given null title and empty author, then return BAD REQUEST with field validation message")
  public void givenNullTitleAndEmptyAuthorThenReturnBadRequest() throws Exception {

    writeAsJson(BookRequestTemplate.buildWithNullTitleAndEmptyAuthor());

    onBookRegisterFailValidateMultipleMessages(
        TITLE_FIELD_VALIDATION_MESSAGE, AUTHOR_FIELD_VALIDATION_MESSAGE);
  }

  @Test
  @DisplayName(
      "Given zero number of pages and empty title, then return BAD REQUEST with field validation message")
  void givenZeroNumberOfPagesAndEmptyTitleThenReturnBadRequest() throws Exception {

    writeAsJson(BookRequestTemplate.buildWithZeroNumberPagesAndEmptyTitle());

    onBookRegisterFailValidateMultipleMessages(
        TITLE_FIELD_VALIDATION_MESSAGE, NUMBER_PAGES_FIELD_MIN_VALUE_VALIDATION_MESSAGE);
  }
}
