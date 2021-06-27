package com.lucasbarbosa.libraryapi.driver.exception;

import lombok.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
  private int status;
  private List<String> message;
  private String path;

  public static ErrorResponse ofErrorMessage(
      HttpServletRequest request, ErrorMessage errorMessage, int status) {
    return ErrorResponse.builder()
        .message(errorMessage.getErrors())
        .path(request.getRequestURI())
        .status(status)
        .build();
  }
}
