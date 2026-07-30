package com.example.customer_service.controller;

import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.dto.ProductRequestDTO;
import com.example.customer_service.dto.ProductResponseDTO;
import com.example.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.getAllCustomers());
  }

  @GetMapping("/{customer_id}")
  public ResponseEntity<CustomerResponseDTO> getCustomerById(
          @PathVariable Long customer_id) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.getCustomerById(customer_id));
  }

  @PostMapping
  public ResponseEntity<CustomerResponseDTO> createCustomer(
          @RequestBody CustomerRequestDTO customerRequestDTO) {

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(customerService.createCustomer(customerRequestDTO));
  }

  @PutMapping("/{customer_id}")
  public ResponseEntity<CustomerResponseDTO> updateCustomer(
          @PathVariable Long customer_id,
          @RequestBody CustomerRequestDTO customerRequestDTO) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.updateCustomer(customer_id, customerRequestDTO));
  }

  @DeleteMapping("/{customer_id}")
  public ResponseEntity<String> deleteCustomer(
          @PathVariable Long customer_id) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.deleteCustomer(customer_id));
  }

  @PostMapping("/{customerId}/products")
  public ResponseEntity<ProductResponseDTO> createProductByCustomer(
          @PathVariable Long customerId,
          @RequestBody ProductRequestDTO requestDTO) {

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(customerService.createProductByCustomer(customerId, requestDTO));
  }

  @GetMapping("/{customerId}/products/{productId}")
  public ResponseEntity<ProductResponseDTO> getProductById(
          @PathVariable Long customerId,
          @PathVariable Long productId) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.getProductById(customerId, productId));
  }

  @PutMapping("/{customerId}/products/{productId}")
  public ResponseEntity<ProductResponseDTO> updateProductByCustomer(
          @PathVariable Long customerId,
          @PathVariable Long productId,
          @RequestBody ProductRequestDTO requestDTO) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.updateProductByCustomer(
                    customerId,
                    productId,
                    requestDTO));
  }

  @DeleteMapping("/{customerId}/products/{productId}")
  public ResponseEntity<String> deleteProductByCustomer(
          @PathVariable Long customerId,
          @PathVariable Long productId) {

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.deleteProductByCustomer(customerId, productId));
  }
}
