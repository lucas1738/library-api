package com.lucasbarbosa.libraryapi.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** @author Lucas Barbosa on 10/07/2021 */
@Entity
@Table(name = "tb_seller")
@Getter
@Setter
@Builder
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
}
