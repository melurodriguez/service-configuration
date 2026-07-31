package com.example.product_service.exception;

import com.example.product_service.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<String> handleProductNotFound(
          ProductNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
  }

  @ExceptionHandler(com.example.customer_service.exception.ProductOwnershipException.class)
  public ResponseEntity<String> handleOwnership(
          com.example.customer_service.exception.ProductOwnershipException ex) {

    return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneral(
          Exception ex) {

    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.getMessage());
  }
}
