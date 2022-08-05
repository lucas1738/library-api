package com.lucasbarbosa.libraryapi.service.impl;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.model.dto.SellerInfoResponse;
import com.lucasbarbosa.libraryapi.model.dto.SellerRequest;
import com.lucasbarbosa.libraryapi.model.entity.Seller;
import com.lucasbarbosa.libraryapi.model.enums.SellerInformationTypeEnum;
import com.lucasbarbosa.libraryapi.repository.SellerRepository;
import com.lucasbarbosa.libraryapi.service.SellerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.getInfoAsConst;
import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.getSellerAsConst;
import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.retrieveOneParamMessage;
import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.retrieveRawMessage;
import static com.lucasbarbosa.libraryapi.model.dto.SellerRequest.retriveDocumentNumber;
import static com.lucasbarbosa.libraryapi.model.dto.SellerRequest.retriveSellerDescription;
import static com.lucasbarbosa.libraryapi.model.entity.Seller.remainingTokenValidity;
import static com.lucasbarbosa.libraryapi.model.enums.TokenValidationEnum.*;
import static java.lang.String.format;
import static java.util.function.Predicate.not;

/** @author Lucas Barbosa on 07/08/2021 */
@Service
public class SellerServiceImpl implements SellerService {

  @Value("${seller.token.validity}")
  private int tokenValidity;

  private final SellerRepository sellerRepository;

  private final MessageSource messageSource;

  public SellerServiceImpl(SellerRepository sellerRepository, MessageSource messageSource) {
    this.sellerRepository = sellerRepository;
    this.messageSource = messageSource;
  }

  @Override
  public SellerInfoResponse fetchSellerInformation(
      SellerInformationTypeEnum sellerInfoType, String sellerToken, String sellerDocument) {
    switch (sellerInfoType) {
      case TOKEN_EXPIRATION:
        return handleTokenExpirationInfo(sellerToken);
      case TOKEN:
        return handleTokenKeyInfo(sellerDocument);
      default:
        return null;
    }
  }

  @Override
  @Transactional
  public SellerInfoResponse signUpSeller(SellerRequest sellerRequest) {
    ExampleMatcher matcher = generateSellerExampleMatcher();
    Seller sellerParams = generateSellerParams(sellerRequest);
    Example<Seller> sellerCriteria = Example.of(sellerParams, matcher);

    validateIfSellerAlreadyExists(sellerRequest, sellerCriteria);

    Seller seller = sellerRepository.save(SellerRequest.toDomain(sellerRequest));
    String message =
        retrieveOneParamMessage(TOKEN_KEY_GENERATED, seller.getKey(), this.messageSource);
    return new SellerInfoResponse(message);
  }

  private void validateIfSellerAlreadyExists(SellerRequest sellerRequest, Example<Seller> sellerCriteria) {
    Optional.of(this.sellerRepository.findAll(sellerCriteria))
        .filter(not(CollectionUtils::isEmpty))
        .ifPresent(
            existingSeller -> {
              throw new AttributeInUseException(
                  getSellerAsConst(),
                  getInfoAsConst(),
                  format(
                      "%s or %s",
                      retriveSellerDescription(sellerRequest),
                      retriveDocumentNumber(sellerRequest)));
            });
  }

  private Seller generateSellerParams(SellerRequest sellerRequest) {
    return Seller.builder()
        .description(retriveSellerDescription(sellerRequest))
        .documentNumber(retriveDocumentNumber(sellerRequest))
        .build();
  }

  private ExampleMatcher generateSellerExampleMatcher() {
    return ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
  }

  private SellerInfoResponse handleTokenKeyInfo(String sellerInfo) {
    return Optional.ofNullable(sellerInfo)
        .filter(not(ObjectUtils::isEmpty))
        .flatMap(this.sellerRepository::findByDocumentNumber)
        .map(
            seller ->
                new SellerInfoResponse(
                    retrieveOneParamMessage(
                        TOKEN_KEY_GENERATED, seller.getKey(), this.messageSource)))
        .orElse(
            new SellerInfoResponse(
                retrieveRawMessage(TOKEN_DOCUMENT_INCORRECT, this.messageSource)));
  }

  private SellerInfoResponse handleTokenExpirationInfo(String sellerInfo) {
    return Optional.ofNullable(sellerInfo)
        .filter(not(ObjectUtils::isEmpty))
        .flatMap(this.sellerRepository::findByKey)
        .map(seller -> remainingTokenValidity(seller, tokenValidity))
        .map(this::buildTokenValidationMessage)
        .orElse(
            new SellerInfoResponse(retrieveRawMessage(TOKEN_KEY_INCORRECT, this.messageSource)));
  }

  private SellerInfoResponse buildTokenValidationMessage(int daysRemainning) {
    return Optional.of(daysRemainning)
        .filter(days -> days > 0)
        .map(
            validPeriod ->
                new SellerInfoResponse(
                    retrieveOneParamMessage(
                        TOKEN_KEY_VALIDITY, validPeriod.toString(), this.messageSource)))
        .orElse(new SellerInfoResponse(retrieveRawMessage(TOKEN_KEY_EXPIRED, this.messageSource)));
  }
}
