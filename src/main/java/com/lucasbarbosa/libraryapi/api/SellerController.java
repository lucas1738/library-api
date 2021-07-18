package com.lucasbarbosa.libraryapi.api;

import com.lucasbarbosa.libraryapi.model.dto.SellerRequestDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Barbosa on 18/07/2021
 */

@RestController
@RequestMapping("/seller")
@Api(tags = "Seller")
@RequiredArgsConstructor
public class SellerController {

    @PostMapping
    public ResponseEntity<?> registerSeller(
            @Validated @RequestBody SellerRequestDTO sellerRequestDTO) {
        return ResponseEntity.ok().build();
    }
}
