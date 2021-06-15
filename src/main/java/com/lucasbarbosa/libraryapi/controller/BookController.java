package com.lucasbarbosa.libraryapi.controller;

import com.lucasbarbosa.libraryapi.entity.dto.BookRequestDTO;
import com.lucasbarbosa.libraryapi.entity.dto.BookResponseDTO;
import com.lucasbarbosa.libraryapi.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lucasbarbosa.libraryapi.utils.LibraryUtils.getBookAsConst;
import static com.lucasbarbosa.libraryapi.utils.LibraryUtils.getTitleAsConst;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<BookResponseDTO> fetchBooks() {
        return this.bookRepository.findAll().stream().map(BookResponseDTO::of).collect(toList());
    }

    @PostMapping("/register")
    public ResponseEntity<BookResponseDTO> createBook(@Validated @RequestBody BookRequestDTO bookRequestDTO) {
        this.bookRepository.findByTitleIgnoreCase(bookRequestDTO.getTitle())
                .ifPresent(item -> {
                    throw new AttributeInUseException(getBookAsConst(), getTitleAsConst(), item.getTitle());
                });
        var book = this.bookRepository.save(BookRequestDTO.toDomain(bookRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(BookResponseDTO.of(book));
    }
}
