package com.lucasbarbosa.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/** @author Lucas Barbosa on 27/06/2021 */
@SpringBootApplication
@EnableSwagger2
public class LibraryApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryApiApplication.class, args);
  }
}
