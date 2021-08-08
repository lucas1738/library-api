package com.lucasbarbosa.libraryapi.api;

import com.lucasbarbosa.libraryapi.driver.validation.EnumAssurance;
import com.lucasbarbosa.libraryapi.model.dto.SellerInfoResponse;
import com.lucasbarbosa.libraryapi.model.dto.SellerRequest;
import com.lucasbarbosa.libraryapi.model.enums.SellerInformationTypeEnum;
import com.lucasbarbosa.libraryapi.service.SellerService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.lucasbarbosa.libraryapi.model.enums.SellerInformationTypeEnum.findSellerInformationByLiteral;
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
            response = SellerInfoResponse.class),
        @ApiResponse(code = 400, message = "Error due to incorrect request contract")
      })
  public ResponseEntity<SellerInfoResponse> registerSeller(
      @Validated @RequestBody SellerRequest sellerRequestDTO) {

    return ResponseEntity.status(CREATED).body(this.sellerService.signUpSeller(sellerRequestDTO));
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
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Seller info is returned according to data provided and Seller status",
            response = SellerInfoResponse.class),
        @ApiResponse(code = 400, message = "Error due to incorrect request contract")
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
                findSellerInformationByLiteral(infoType), token, documentNumber));
  }
}
