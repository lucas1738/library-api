package com.lucasbarbosa.libraryapi.api;

import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;
import com.lucasbarbosa.libraryapi.model.dto.BookResponseDTO;
import com.lucasbarbosa.libraryapi.model.enums.BookGenreEnum;
import com.lucasbarbosa.libraryapi.service.BookService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/** @author Lucas Barbosa on 27/06/2021 */
@RestController
@RequestMapping("/books")
@Api(tags = "Book")
@RequiredArgsConstructor
public class BookController {

  //  @Autowired
  private final BookService bookService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "API responsible for fetching all registered books")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Fetched all books sucessfully",
            response = BookResponseDTO.class)
      })
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "isbn",
        value = "International Standard Book Number",
        required = false,
        dataType = "string",
        paramType = "query"),
    @ApiImplicitParam(
        name = "title",
        value = "Book Title",
        required = false,
        dataType = "string",
        paramType = "query"),
    @ApiImplicitParam(
        name = "author",
        value = "Book Author",
        required = false,
        dataType = "string",
        paramType = "query"),
    @ApiImplicitParam(
        name = "bookGenre",
        value = "Book Genre",
        allowableValues =
            "ACTION,"
                + "COMEDY,"
                + "DRAMA,"
                + "FANTASY,"
                + "HORROR,"
                + "MYSTERY,"
                + "ROMANCE,"
                + "THRILLER",
        required = false,
        dataTypeClass = BookGenreEnum.class,
        paramType = "query"),
    @ApiImplicitParam(
        name = "initialDate",
        value = "Initial Creation Date",
        required = false,
        dataType = "string",
        paramType = "query"),
    @ApiImplicitParam(
        name = "finalDate",
        value = "Final Creation Date",
        required = false,
        dataType = "string",
        paramType = "query"),
    @ApiImplicitParam(
        name = "bookMaxPages",
        value = "Max Book Number of Pages",
        required = false,
        dataType = "string",
        paramType = "query")
  })
  public ResponseEntity<List<BookResponseDTO>> fetchBooks(
      @RequestParam(name = "isbn", required = false) String isbn,
      @RequestParam(name = "title", required = false) String title,
      @RequestParam(name = "author", required = false) String author,
      @RequestParam(name = "bookGenre", required = false) String bookGenre,
      @RequestParam(name = "initialDate", required = false) String initialDate,
      @RequestParam(name = "finalDate", required = false) String finalDate,
      @RequestParam(name = "bookMaxPages", required = false) String bookMaxPages) {

    var books =
        bookService.fetchBooks(
            isbn, title, author, bookGenre, initialDate, finalDate, bookMaxPages);

    return ResponseEntity.ok(books);
  }

  @PostMapping(
      path = "/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "API responsible for registering books")
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
    var book = bookService.createBook(bookRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(BookResponseDTO.of(book));
  }
}
