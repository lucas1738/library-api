package com.lucasbarbosa.libraryapi.controller;

import com.lucasbarbosa.libraryapi.template.BookRequestDTOTemplate;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

/** @author Lucas Barbosa on 03/07/2021 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest extends BookControllerTestSupport {

  @LocalServerPort private int port;

  @Test
  @DisplayName(
      "Given unsuitable bookGenre value, then return BAD REQUEST with field validation message")
  void givenBookGenrerUnallowedValuesThenReturnBadRequest() throws Exception {

    writeAsJson(BookRequestDTOTemplate.buildWithUnsuitableBookGenre());

    given()
        .port(port)
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(json)
        .when()
        .request(HttpMethod.POST.name(), BOOK_API.concat(CREATE_BOOK))
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body(MESSAGE_FIELD, hasItem(ENUM_ASSURANCE_VALIDATION_MESSAGE));
  }

  @Test
  @DisplayName(
      "Given null title and empty author, then return BAD REQUEST with field validation message")
  void givenNullTitleAndEmptyAuthorThenReturnBadRequest() throws Exception {

    writeAsJson(BookRequestDTOTemplate.buildWithNullTitleAndEmptyAuthor());

    given()
        .port(port)
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(json)
        .when()
        .request(HttpMethod.POST.name(), BOOK_API.concat(CREATE_BOOK))
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body(
            MESSAGE_FIELD,
            hasItems(AUTHOR_FIELD_VALIDATION_MESSAGE, TITLE_FIELD_VALIDATION_MESSAGE));
  }
}
