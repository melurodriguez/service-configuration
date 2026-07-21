package com.example.customer_service.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private Long id;
  private Long customerId;
  private String type;           // e.g., "ACCOUNTS", "CARDS", "LOANS", "INVESTMENTS"
  private String accountNumber;  // Account, card, or reference identifier
  private BigDecimal balance;    // Balance or current amount
}