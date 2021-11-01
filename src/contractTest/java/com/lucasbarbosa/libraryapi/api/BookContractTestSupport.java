package com.lucasbarbosa.libraryapi.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** @author Lucas Barbosa on 03/07/2021 */
public class BookContractTestSupport {

  @Autowired private MockMvc mockMvc;

  protected String json;
  protected String BOOK_API = "/books";
  protected String CREATE_BOOK = "/register/";
  protected String BOOK_GENRE_ENUM_VALIDATION_MESSAGE =
      "field bookGenre must be any of ACTION, COMEDY, DRAMA, FANTASY, HORROR, MYSTERY, ROMANCE, THRILLER";
  protected String AUTHOR_FIELD_VALIDATION_MESSAGE = "author must not be empty or null";
  protected String TITLE_FIELD_VALIDATION_MESSAGE = "title must not be empty or null";
  protected String NUMBER_PAGES_FIELD_MIN_VALUE_VALIDATION_MESSAGE = "numberPages must not be 0";
  protected String NUMBER_PAGES_FIELD_MAX_VALUE_VALIDATION_MESSAGE =
      "numberPages must not be greater than 1000";
  protected String CONTENT_TYPE = "application/json;charset=UTF-8";

  protected void writeAsJson(Object object) throws JsonProcessingException {
    this.json = new ObjectMapper().writeValueAsString(object);
  }

  protected void onBookRegisterFailValidateMultipleMessages(
      String firstValidationMessage, String secondValidationMessage) throws Exception {
    ResultActions result =
        mockMvc.perform(
            post(BOOK_API.concat(CREATE_BOOK))
                .contentType(CONTENT_TYPE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON));

    result.andDo(print()).andExpect(status().isBadRequest());
    String exceptionMessage =
        Optional.of(result.andReturn())
            .map(MvcResult::getResolvedException)
            .map(Exception::getMessage)
            .orElse(EMPTY);
    assertTrue(
        Stream.of(firstValidationMessage, secondValidationMessage)
            .allMatch(exceptionMessage::contains));
  }
}
