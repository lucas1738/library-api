package com.lucasbarbosa.libraryapi.api;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.driver.validation.EnumAssurance;
import com.lucasbarbosa.libraryapi.model.dto.SellerInfoResponse;
import com.lucasbarbosa.libraryapi.model.dto.SellerRequest;
import com.lucasbarbosa.libraryapi.model.entity.Seller;
import com.lucasbarbosa.libraryapi.model.enums.SellerInformationTypeEnum;
import com.lucasbarbosa.libraryapi.model.enums.TokenValidationEnum;
import com.lucasbarbosa.libraryapi.repository.SellerRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.*;
import static com.lucasbarbosa.libraryapi.driver.utils.LibraryUtils.createEmptyStringArray;
import static com.lucasbarbosa.libraryapi.model.dto.SellerRequest.retriveDocumentNumber;
import static com.lucasbarbosa.libraryapi.model.dto.SellerRequest.retriveSellerDescription;
import static com.lucasbarbosa.libraryapi.model.entity.Seller.remainingTokenValidity;
import static com.lucasbarbosa.libraryapi.model.enums.SellerInformationTypeEnum.findByLiteral;
import static com.lucasbarbosa.libraryapi.model.enums.TokenValidationEnum.*;
import static java.lang.String.format;
import static java.util.function.Predicate.not;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/** @author Lucas Barbosa on 18/07/2021 */
@RestController
@RequestMapping("/seller")
@Api(tags = "Seller")
@Validated
public class SellerController {

  private final MessageSource messageSource;

  private final SellerRepository sellerRepository;

  public SellerController(MessageSource messageSource, SellerRepository sellerRepository) {
    this.messageSource = messageSource;
    this.sellerRepository = sellerRepository;
  }

  @Value("${seller.token.validity}")
  private int tokenValidity;

  @PostMapping(
      path = "/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "API responsible for registering sellers")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 201,
            message = "Seller sucessfully registered",
            response = Seller.class),
        @ApiResponse(code = 400, message = "Error due to incorrect request contract")
      })
  public ResponseEntity<SellerInfoResponse> registerSeller(
      @Validated @RequestBody SellerRequest sellerRequestDTO) {
    ExampleMatcher matcher =
        ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(StringMatcher.CONTAINING);
    var sellerParams =
        Seller.builder()
            .description(retriveSellerDescription(sellerRequestDTO))
            .documentNumber(retriveDocumentNumber(sellerRequestDTO))
            .build();
    Example<Seller> sellerCriteria = Example.of(sellerParams, matcher);

    Optional.of(this.sellerRepository.findAll(sellerCriteria))
        .filter(not(CollectionUtils::isEmpty))
        .ifPresent(
            existingSeller -> {
              throw new AttributeInUseException(
                  getSellerAsConst(),
                  getInfoAsConst(),
                  format(
                      "%s or %s",
                      retriveSellerDescription(sellerRequestDTO),
                      retriveDocumentNumber(sellerRequestDTO)));
            });

    var seller = sellerRepository.save(SellerRequest.toDomain(sellerRequestDTO));
    var message = retrieveOneParamMessage(TOKEN_KEY_GENERATED, seller.getKey());
    return buildResponseWithStatusAndBody(CREATED, new SellerInfoResponse(message));
  }

  @GetMapping(value = "/info")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "token",
        value = "Seller token obtained by registering",
        dataType = "string",
        paramType = "query"),
    @ApiImplicitParam(
        name = "infoType",
        value = "Type of seller information required",
        allowableValues = "TOKEN, TOKEN_EXPIRATION",
        required = true,
        dataType = "string",
        paramType = "query"),
    @ApiImplicitParam(
        name = "documentNumber",
        value = "Seller document number",
        dataType = "string",
        paramType = "query")
  })
  public ResponseEntity<SellerInfoResponse> retriveSellerInformation(
      @RequestParam(value = "infoType")
          @EnumAssurance(enumClass = SellerInformationTypeEnum.class, field = "infoType")
          String infoType,
      @RequestParam(value = "token", required = false) String token,
      @RequestParam(value = "documentNumber", required = false) String documentNumber) {

    switch (findByLiteral(infoType)) {
      case TOKEN_EXPIRATION:
        return Optional.ofNullable(token)
            .filter(not(ObjectUtils::isEmpty))
            .flatMap(this.sellerRepository::findByKey)
            .map(seller -> remainingTokenValidity(seller, tokenValidity))
            .map(this::buildTokenValidationMessage)
            .orElse(
                buildResponseWithStatusAndBody(
                    OK, new SellerInfoResponse(retrieveRawMessage(TOKEN_KEY_INCORRECT))));
      case TOKEN:
        return Optional.ofNullable(documentNumber)
            .filter(not(ObjectUtils::isEmpty))
            .flatMap(this.sellerRepository::findByDocumentNumber)
            .map(
                seller ->
                    buildResponseWithStatusAndBody(
                        OK,
                        new SellerInfoResponse(
                            retrieveOneParamMessage(TOKEN_KEY_GENERATED, seller.getKey()))))
            .orElse(
                buildResponseWithStatusAndBody(
                    OK, new SellerInfoResponse(retrieveRawMessage(TOKEN_DOCUMENT_INCORRECT))));
      default:
        return null;
    }
  }

  private ResponseEntity<SellerInfoResponse> buildResponseWithStatusAndBody(
      HttpStatus status, SellerInfoResponse body) {
    return ResponseEntity.status(status).body(body);
  }

  private ResponseEntity<SellerInfoResponse> buildTokenValidationMessage(int daysRemainning) {
    return Optional.of(daysRemainning)
        .filter(days -> days > 0)
        .map(
            validPeriod ->
                buildResponseWithStatusAndBody(
                    OK,
                    new SellerInfoResponse(
                        retrieveOneParamMessage(TOKEN_KEY_VALIDITY, validPeriod.toString()))))
        .orElse(
            buildResponseWithStatusAndBody(
                OK, new SellerInfoResponse(retrieveRawMessage(TOKEN_KEY_EXPIRED))));
  }

  private String retrieveOneParamMessage(TokenValidationEnum tokenValidationEnum, String param) {
    return messageSource.getMessage(
        tokenValidationEnum.getMessage(),
        buildWithSingleParam(param),
        LocaleContextHolder.getLocale());
  }

  private String retrieveRawMessage(TokenValidationEnum tokenValidationEnum) {
    return messageSource.getMessage(
        tokenValidationEnum.getMessage(),
        createEmptyStringArray(),
        LocaleContextHolder.getLocale());
  }
}
