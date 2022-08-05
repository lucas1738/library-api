package com.lucasbarbosa.libraryapi.service;

import com.lucasbarbosa.libraryapi.model.dto.CustomerLibrary;
import com.lucasbarbosa.libraryapi.model.dto.CustomerRecommendation;

import java.util.Optional;

/** @author Lucas Barbosa on 28/08/2021 */
public interface RecommendationService {

  Optional<CustomerLibrary> fetchCustomerLibrary();

  Optional<CustomerRecommendation> getRecommendation();
}
