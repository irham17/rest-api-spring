package com.rungroup.api.controller;

import com.rungroup.api.service.AuthApiService;
import com.rungroup.api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService, AuthApiService authApiService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(
            @RequestParam() int page,
            @RequestParam() int limit,
            @RequestParam() String kfa_code
    ) throws Exception {
        return ResponseEntity.ok(productService.getProducts(page, limit, kfa_code));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body("Isilah parameter " + ex.getParameterName() + " dengan benar.");
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleHttpClientError(HttpClientErrorException.BadRequest ex) {
        return ResponseEntity.badRequest().body("Bad request: " + ex.getMessage());
    }
}
