package com.lucasbarbosa.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/** @author Lucas Barbosa on 27/06/2021 */
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class LibraryApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryApiApplication.class, args);
  }
}
