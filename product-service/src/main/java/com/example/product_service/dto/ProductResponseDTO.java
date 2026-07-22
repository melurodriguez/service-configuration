package com.example.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

  private Long id;
  private Long customerId;
  private String type;
  private String accountNumber;
  private BigDecimal balance;
}