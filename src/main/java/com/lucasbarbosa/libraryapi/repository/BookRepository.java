package com.lucasbarbosa.libraryapi.repository;

import com.lucasbarbosa.libraryapi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/** @author Lucas Barbosa on 27/06/2021 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

  Optional<Book> findByTitleIgnoreCase(String title);
}
