package com.lucasbarbosa.libraryapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name="dt_creation")
    private LocalDateTime creationDate;

    @Column(name="dt_update")
    private LocalDateTime updateDate;

    @PrePersist
    private void onCreation(){
        var time = LocalDateTime.now();
        this.creationDate = time;
        this.updateDate = time;
    }

    @PreUpdate
    private void onUpdate(){
        this.updateDate = LocalDateTime.now();
    }


}
