package com.lucasbarbosa.libraryapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

/** @author Lucas Barbosa on 03/07/2021 */
public class BookContractTestSupport {

  @LocalServerPort protected int port;

  protected String json;
  protected String BOOK_API = "/library-api/books";
  protected String CREATE_BOOK = "/register";
  protected String MESSAGE_FIELD = "message";
  protected String BOOK_GENRE_ENUM_VALIDATION_MESSAGE =
      "field bookGenre must be any of ACTION, COMEDY, DRAMA, FANTASY, HORROR, MYSTERY, ROMANCE, THRILLER";
  protected String AUTHOR_FIELD_VALIDATION_MESSAGE = "author must not be empty or null";
  protected String TITLE_FIELD_VALIDATION_MESSAGE = "title must not be empty or null";
  protected String NUMBER_PAGES_FIELD_MIN_VALUE_VALIDATION_MESSAGE = "numberPages must not be 0";
  protected String NUMBER_PAGES_FIELD_MAX_VALUE_VALIDATION_MESSAGE =
      "numberPages must not be greater than 1000";

  protected void writeAsJson(Object object) throws JsonProcessingException {
    this.json = new ObjectMapper().writeValueAsString(object);
  }

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.basePath = BOOK_API;
  }

  protected void onBookRegisterFailValidateMultipleMessages(
      String firstValidationMessage, String secondValidationMessage) {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(json)
        .when()
        .request(HttpMethod.POST.name(), CREATE_BOOK)
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body(MESSAGE_FIELD, hasItems(firstValidationMessage, secondValidationMessage));
  }
}
