package com.lucasbarbosa.libraryapi.entity.dto;

import com.lucasbarbosa.libraryapi.entity.Book;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    @NotEmpty(message = "title must not be empty or null")
    private String title;

    @NotEmpty(message = "author must not be empty or null")
    private String author;

    @NotNull(message = "numberPages must not be null")
    private int numberPages;


    public static Book toDomain(BookRequestDTO bookRequestDTO) {
        return assemble.apply(bookRequestDTO);
    }

    private static Function<BookRequestDTO, Book> assemble = dto ->
            Book.builder()
                    .isbn(UUID.randomUUID().toString())
                    .title(dto.getTitle())
                    .author(dto.getAuthor())
                    .numberPages(dto.getNumberPages())
            .build();

}
