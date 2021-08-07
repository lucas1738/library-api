package com.lucasbarbosa.libraryapi.api;

import com.lucasbarbosa.libraryapi.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.libraryapi.driver.validation.EnumAssurance;
import com.lucasbarbosa.libraryapi.model.dto.SellerInfoResponse;
import com.lucasbarbosa.libraryapi.model.dto.SellerRequest;
import com.lucasbarbosa.libraryapi.model.entity.Seller;
import com.lucasbarbosa.libraryapi.model.enums.SellerInformationTypeEnum;
import com.lucasbarbosa.libraryapi.service.SellerService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.getInfoAsConst;
import static com.lucasbarbosa.libraryapi.driver.utils.ExceptionUtils.getSellerAsConst;
import static com.lucasbarbosa.libraryapi.model.dto.SellerRequest.retriveDocumentNumber;
import static com.lucasbarbosa.libraryapi.model.dto.SellerRequest.retriveSellerDescription;
import static com.lucasbarbosa.libraryapi.model.enums.SellerInformationTypeEnum.findByLiteral;
import static com.lucasbarbosa.libraryapi.model.enums.TokenValidationEnum.TOKEN_KEY_GENERATED;
import static java.lang.String.format;
import static java.util.function.Predicate.not;
import static org.springframework.http.HttpStatus.CREATED;

/** @author Lucas Barbosa on 18/07/2021 */
@RestController
@RequestMapping("/seller")
@Api(tags = "Seller")
@Validated
public class SellerController {

  private final SellerService sellerService;

  public SellerController(SellerService sellerService) {
    this.sellerService = sellerService;
  }

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

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            this.sellerService.fetchSellerInformation(
                findByLiteral(infoType), token, documentNumber));
  }
}
