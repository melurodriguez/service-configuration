package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponseDTO> createProduct(
          @RequestBody ProductRequestDTO requestDTO) {

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productService.createProduct(requestDTO));
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.getAllProducts());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDTO> getProductById(
          @PathVariable Long productId) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.getProductById(productId));
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<ProductResponseDTO>> getProductsByCustomerId(
          @PathVariable Long customerId) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.getProductsByCustomerId(customerId));
  }

  @PutMapping("/{productId}")
  public ResponseEntity<ProductResponseDTO> updateProduct(
          @PathVariable Long productId,
          @RequestBody ProductRequestDTO requestDTO) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.updateProduct(productId, requestDTO));
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<String> deleteProduct(
          @PathVariable Long productId) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.deleteProduct(productId));
  }
}