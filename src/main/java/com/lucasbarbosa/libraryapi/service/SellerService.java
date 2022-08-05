package com.lucasbarbosa.libraryapi.service;

import com.lucasbarbosa.libraryapi.model.dto.SellerInfoResponse;
import com.lucasbarbosa.libraryapi.model.dto.SellerRequest;
import com.lucasbarbosa.libraryapi.model.enums.SellerInformationTypeEnum;

/** @author Lucas Barbosa on 07/08/2021 */
public interface SellerService {

  SellerInfoResponse fetchSellerInformation(
      SellerInformationTypeEnum sellerInfoType, String sellerToken, String sellerDocument);

  SellerInfoResponse signUpSeller(SellerRequest sellerRequest);
}
