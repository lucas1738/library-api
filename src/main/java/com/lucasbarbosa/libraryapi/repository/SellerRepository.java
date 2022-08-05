package com.lucasbarbosa.libraryapi.repository;

import com.lucasbarbosa.libraryapi.model.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** @author Lucas Barbosa on 24/07/2021 */
@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {
  Optional<Seller> findByKey(String key);

  Optional<Seller> findByDocumentNumber(String documentNumber);
}
