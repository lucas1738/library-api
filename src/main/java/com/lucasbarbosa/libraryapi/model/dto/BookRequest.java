package com.lucasbarbosa.libraryapi.model.dto;

import com.lucasbarbosa.libraryapi.driver.validation.EnumAssurance;
import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.Function;

import static com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum.findByLiteral;
/** @author Lucas Barbosa on 27/06/2021 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "BookRequestDTO", description = "Data transfer object for book registration")
public class BookRequest {

  @ApiModelProperty(value = "Book title", example = "The Adventures of Joe Doe", required = true)
  @NotEmpty(message = "title must not be empty or null")
  private String title;

  @ApiModelProperty(value = "Book author", example = "Joe Doe", required = true)
  @NotEmpty(message = "author must not be empty or null")
  private String author;

  @ApiModelProperty(value = "Number of pages in the book", example = "200", required = true)
  @NotNull(message = "numberPages must not be null")
  @Range(min = 1, message = "numberPages must not be 0")
  @Range(max = 1000, message = "numberPages must not be greater than 1000")
  private int numberPages;

  @ApiModelProperty(value = "Book genre", example = "THRILLER", required = true)
  @EnumAssurance(enumClass = BookGenreEnum.class, field = "bookGenre")
  @NotEmpty(message = "bookGenre must not be empty or null")
  private String bookGenre;

  public static Book toDomain(BookRequestDTO bookRequestDTO) {
    return assemble.apply(bookRequestDTO);
  }

  private static Function<BookRequestDTO, Book> assemble =
      dto ->
          Book.builder()
              .isbn(UUID.randomUUID().toString())
              .title(dto.getTitle())
              .author(dto.getAuthor())
              .numberPages(dto.getNumberPages())
              .bookGenre(findByLiteral(dto.getBookGenre()))
              .build();
}
