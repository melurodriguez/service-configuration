package com.example.customer_service.mapper;

import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.dto.ProductDTO;
import com.example.customer_service.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

  public Customer toEntity(CustomerRequestDTO requestDTO) {
    if (requestDTO == null) {
      return null;
    }

    Customer customer = new Customer();
    customer.setName(requestDTO.getName());
    customer.setDocument(requestDTO.getDocument());
    customer.setEmail(requestDTO.getEmail());
    customer.setBalance(requestDTO.getBalance());

    return customer;
  }

  public CustomerResponseDTO toResponse(Customer customer) {
    if (customer == null) {
      return null;
    }

    CustomerResponseDTO responseDTO = new CustomerResponseDTO();
    responseDTO.setId(customer.getId());
    responseDTO.setName(customer.getName());
    responseDTO.setDocument(customer.getDocument());
    responseDTO.setEmail(customer.getEmail());
    responseDTO.setBalance(customer.getBalance());

    return responseDTO;
  }


  public CustomerResponseDTO toResponseWithProducts(Customer customer, List<ProductDTO> products) {
    CustomerResponseDTO responseDTO = toResponse(customer);
    if (responseDTO != null) {
      responseDTO.setProducts(products);
    }
    return responseDTO;
  }
}