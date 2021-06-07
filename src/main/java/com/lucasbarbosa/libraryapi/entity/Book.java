package com.lucasbarbosa.libraryapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    @Column(name="nu_isbn")
    private String isbn;

    @Column(name="ds_title")
    private String title;

    @Column(name="ds_author")
    private String author;

    @Column(name="nu_pages")
    private int numberPages;


}
