package com.example.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

  private Long customerId;
  private String type;           // e.g., "ACCOUNTS", "CARDS", "LOANS", "INVESTMENTS"
  private String accountNumber;
  private BigDecimal balance;
}
