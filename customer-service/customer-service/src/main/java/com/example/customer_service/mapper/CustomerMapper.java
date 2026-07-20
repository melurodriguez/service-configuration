package com.example.customer_service.mapper;

import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public CustomerResponseDTO toResponse(Customer c){
    return new CustomerResponseDTO();
  }

  public Customer toEntity(CustomerRequestDTO c){
    return new Customer();
  }
}
