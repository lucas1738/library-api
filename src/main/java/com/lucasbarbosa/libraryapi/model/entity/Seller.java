package com.lucasbarbosa.libraryapi.model.entity;

import io.swagger.models.auth.In;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;

/** @author Lucas Barbosa on 10/07/2021 */
@Entity
@Table(name = "tb_seller")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

  @Id
  @Column(name = "seller_uuid")
  private String id;

  @Column(name = "seller_key")
  private String key;

  @Column(name = "seller_description")
  private String description;

  @Column(name = "document_number")
  private String documentNumber;

  @Column(name = "dt_creation")
  private LocalDateTime creationDate;

  @PrePersist
  private void onCreation() {
    this.creationDate = LocalDateTime.now();
  }

  private static LocalDate retriveTokenExpirationDate(
      LocalDate tokenCreationDate, int tokenValidity) {
    return tokenCreationDate.plusDays(tokenValidity);
  }

  public static Integer remainingTokenValidity(Seller seller, int tokenValidity) {
    return Optional.ofNullable(seller)
        .map(Seller::getCreationDate)
        .map(LocalDateTime::toLocalDate)
        .filter(
            creationDate -> retriveTokenExpirationDate(creationDate, tokenValidity).isAfter(now()))
        .map(
            tokenCreationDate ->
                DAYS.between(now(), retriveTokenExpirationDate(tokenCreationDate, tokenValidity)))
        .map(Long::intValue)
        .orElse(0);
  }
}
