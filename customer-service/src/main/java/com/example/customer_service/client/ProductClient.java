package com.example.customer_service.client;

import com.example.customer_service.dto.ProductRequestDTO;
import com.example.customer_service.dto.ProductResponseDTO;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

  @GetMapping("/products/customer/{customerId}")
  List<ProductResponseDTO> getProductsByCustomerId(
          @PathVariable("customerId") Long customerId);

  @PostMapping("/products")
  ProductResponseDTO createProduct(
          @RequestBody ProductRequestDTO requestDTO);

  @GetMapping("/products/{productId}")
  ProductResponseDTO getProductById(
          @PathVariable("productId") Long productId);

  @PutMapping("/products/{productId}")
  ProductResponseDTO updateProduct(
          @PathVariable("productId") Long productId,
          @RequestBody ProductRequestDTO requestDTO);

  @DeleteMapping("/products/{productId}")
  String deleteProduct(
          @PathVariable("productId") Long productId);

}

