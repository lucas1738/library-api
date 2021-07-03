package com.lucasbarbosa.libraryapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** @author Lucas Barbosa on 03/07/2021 */
public class BookControllerTestSupport {

  protected String json;
  protected String BOOK_API = "/library-api/books";
  protected String CREATE_BOOK = "/register";
  protected String MESSAGE_FIELD = "message";
  protected String ENUM_ASSURANCE_VALIDATION_MESSAGE =
      "field bookGenre must be any of ACTION, COMEDY, DRAMA, FANTASY, HORROR, MYSTERY, ROMANCE, THRILLER";
  protected String AUTHOR_FIELD_VALIDATION_MESSAGE = "author must not be empty or null";
  protected String TITLE_FIELD_VALIDATION_MESSAGE = "title must not be empty or null";

  protected void writeAsJson(Object object) throws JsonProcessingException {
    this.json = new ObjectMapper().writeValueAsString(object);
  }
}
