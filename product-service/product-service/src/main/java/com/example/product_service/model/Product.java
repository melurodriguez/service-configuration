package com.example.product_service.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
@Entity
public class Product {
  private Long id;
  private Long customerId;
  private String type; // or ProductType enum
  private String accountNumber;
  private BigDecimal balance;
}
