package com.lucasbarbosa.libraryapi.api;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;
import com.lucasbarbosa.libraryapi.model.dto.BookResponseDTO;
import com.lucasbarbosa.libraryapi.model.entity.Book;
import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;
import com.lucasbarbosa.libraryapi.repository.BookRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.getBookAsConst;
import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.getTitleAsConst;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/books")
@Api(tags = "Book")
@RequiredArgsConstructor
public class BookController {

  private final BookRepository bookRepository;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "API responsible for fetching all registered books")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Fetched all books sucessfully",
            response = BookResponseDTO.class)
      })
  public List<BookResponseDTO> fetchBooks() {
    return this.bookRepository.findAll().stream()
        .sorted(comparing(Book::getAuthor).thenComparing(Book::getTitle))
        .map(BookResponseDTO::of)
        .collect(toList());
  }

  @PostMapping(
      path = "/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "API responsible for registering books")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "bookGenre",
        value = "Book Genre",
        required = true,
        dataType = "String",
        allowableValues = "first, second, third")
  })
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 201,
            message = "Book sucessfully registered",
            response = BookResponseDTO.class),
        @ApiResponse(code = 400, message = "Error due to incorrect request contract")
      })
  public ResponseEntity<BookResponseDTO> createBook(
      @Validated @RequestBody BookRequestDTO bookRequestDTO) {
    this.bookRepository
        .findByTitleIgnoreCase(bookRequestDTO.getTitle())
        .ifPresent(
            item -> {
              throw new AttributeInUseException(
                  getBookAsConst(), getTitleAsConst(), item.getTitle());
            });
    var book = this.bookRepository.save(BookRequestDTO.toDomain(bookRequestDTO));
    return ResponseEntity.status(HttpStatus.CREATED).body(BookResponseDTO.of(book));
  }
}
