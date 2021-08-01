package com.lucasbarbosa.libraryapi.unit;

import com.lucasbarbosa.libraryapi.model.dto.BookRequest;
import com.lucasbarbosa.libraryapi.model.dto.BookResponse;
import com.lucasbarbosa.libraryapi.model.entity.Book;

import java.util.List;
/** @author Lucas Barbosa on 27/06/2021 */
public interface BookService {

  Book createBook(BookRequest bookRequestDTO);

  List<BookResponse> fetchBooks(
          String isbn,
          String title,
          String author,
          String bookGenre,
          String initialDate,
          String finalDate,
          String bookMaxPages);
}
