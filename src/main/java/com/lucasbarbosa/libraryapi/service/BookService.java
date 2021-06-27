package com.lucasbarbosa.libraryapi.service;

import com.lucasbarbosa.libraryapi.model.dto.BookRequestDTO;
import com.lucasbarbosa.libraryapi.model.dto.BookResponseDTO;
import com.lucasbarbosa.libraryapi.model.entity.Book;

import java.util.List;

public interface BookService {

  public Book createBook(BookRequestDTO bookRequestDTO);

  public List<BookResponseDTO> fetchBooks(
      String isbn,
      String title,
      String author,
      String bookGenre,
      String initialDate,
      String finalDate,
      String bookMinPages,
      String bookMaxPages);
}
